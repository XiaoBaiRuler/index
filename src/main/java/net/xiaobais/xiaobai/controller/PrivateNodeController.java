package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/30 23:46
 * @Version 1.0
 */
@Api(tags = "PrivateNodeController")
@Controller
@RequestMapping("/private")
public class PrivateNodeController {

    @Resource
    private PreviousService previousService;
    @Resource
    private NextService nextService;
    @Resource
    private UserService userService;
    @Resource
    private BlogService blogService;
    @Resource
    private MapService mapService;
    @Resource
    private PrivateNodeService privateNodeService;
    @Resource
    private PublicNodeService publicNodeService;
    @Resource
    private CommentService commentService;
    @Resource
    private CacheService cacheService;

    private static final String NODE_CACHE = "/public/node/";
    private static final String BLOG_CACHE = "/public/blog/";

    @ApiOperation("获取节点内容(获取缓存)")
    @GetMapping("/getNode")
    @ResponseBody
    public PrivateNodeVo getNode(@RequestParam Integer nodeId, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        Integer userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        // 缓存信息
        Node node = cacheService.getNodeByKey(NODE_CACHE + nodeId);
        Blog blog;
        if (node != null){
            blog = cacheService.getBlogByKey(BLOG_CACHE + node.getBlogId());
            if (blog == null){
                blog = blogService.findBlogById(node.getBlogId());
            }
            if ( userId == 1 || !node.getIsPrivate() || node.getUserId().equals(userId)){
                return nodeToPublicNodeVo(node, blog, userId);
            }
        }
        else {
            // 所属用户ID
            node = privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(nodeId, userId);
            // 管理员管理所有节点
            if (node == null && userId == 1){
                node = privateNodeService.findNodeByNodeId(nodeId);
            }
            if (node == null){
                node = publicNodeService.findNodeByNodeIdAndNotIsPrivate(nodeId);
            }
            if (node != null){
                blog = blogService.findBlogById(node.getBlogId());
                // 添加缓存
                cacheService.setNodeByKey(NODE_CACHE + node.getNodeId(), node);
                cacheService.setBlogByKey(BLOG_CACHE + node.getBlogId(), blog);
                return nodeToPublicNodeVo(node, blog, userId);
            }
        }
        return null;
    }

    @ApiOperation("获取私有前置节点")
    @GetMapping("/getPreNode")
    @ResponseBody
    public List<NodeVo> getPreNode(@RequestParam Integer nodeId,
                                   @RequestParam Integer pageNumber,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String title, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        Integer userId = -1;
        if (cookies != null){
            userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        }

        List<Node> previousNodes = null;
        if (title == null || "".equals(title)){
            previousNodes = previousService.findPreviousByNodeId(
                    nodeId, pageNumber, pageSize, userId != 1 ? 1 : 0);
        }
        else{
            previousNodes = previousService.findPreviousByNodeIdAndTitle(
                    nodeId, pageNumber, pageSize, title, userId != 1 ? 1 : 0);
        }
        List<NodeVo> previousNodesVo = new ArrayList<>();
        Integer finalUserId = userId;
        previousNodes.forEach(node -> previousNodesVo.add(nodeToNodeVo(node, finalUserId)));
        return previousNodesVo;
    }

    @ApiOperation("获取所有私有前置节点")
    @GetMapping("/getAllPreNode")
    @ResponseBody
    public List<Node> getPreNode(@RequestParam Integer nodeId, @RequestParam Integer userId){
        return previousService.findPrivatePreviousByNodeIdAndUserId(nodeId, userId);
    }

    @ApiOperation("获取私有后置节点")
    @GetMapping("/getNexNode")
    @ResponseBody
    public List<NodeVo> getNexNode(@RequestParam Integer nodeId,
                                   @RequestParam Integer pageNumber,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String title, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Integer userId = -1;
        if (cookies != null){
            userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        }
        List<Node> nextNodes = null;
        if (title == null || "".equals(title)) {
            nextNodes = nextService.findNextByNodeId(nodeId, pageNumber, pageSize, userId != 1 ? 1 : 0);
        } else {
            nextNodes = nextService.findNextByNodeIdAndTitle(nodeId, pageNumber, pageSize, title, userId != 1 ? 1 : 0);
        }
        List<NodeVo> nextNodesVo = new ArrayList<>();
        Integer finalUserId = userId;
        nextNodes.forEach(node -> nextNodesVo.add(nodeToNodeVo(node, finalUserId)));
        return nextNodesVo;
    }

    @ApiOperation("获取所有私有前置节点")
    @GetMapping("/getAllNexNode")
    @ResponseBody
    public List<Node> getNextNode(@RequestParam Integer nodeId, @RequestParam Integer userId){
        return nextService.findPrivateNextByNodeIdAndUserId(nodeId, userId);
    }



    @ApiOperation("获取私有前置节点个数")
    @GetMapping("/getPreCount")
    @ResponseBody
    public int getPreNodeCount(@RequestParam Integer nodeId,
                               @RequestParam String title, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Integer userId = -1;
        if (cookies != null){
            userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        }
        if (title == null || "".equals(title)) {
            return previousService.countPreviousNode(nodeId, userId != 1 ? 1 : 0);
        }
        else{
            return previousService.countPreviousNode(nodeId, title, userId != 1 ? 1 : 0);
        }
    }


    @ApiOperation("获取私有后置节点个数")
    @GetMapping("/getNexCount")
    @ResponseBody
    public int getNexNodeCount(@RequestParam Integer nodeId,
                               @RequestParam String title, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Integer userId = -1;
        if (cookies != null){
            userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        }

        if (title == null || "".equals(title)) {
            return nextService.countNextNode(nodeId, userId != 1 ? 1 : 0);
        }
        else{
            return nextService.countNextNode(nodeId, title, userId != 1 ? 1 : 0);
        }
    }

    @ApiOperation("跳转添加前置节点")
    @GetMapping("/toAddPrevious/{nodeId}")
    public String toAddPreNode(@PathVariable Integer nodeId, Model model){
        model.addAttribute("title", "前置");
        model.addAttribute("rel", "下");
        model.addAttribute("nodeId", nodeId);
        model.addAttribute("isPre", "1");
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(publicNodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(publicNodeService.findNodeByTopStar(5)));
        return "addNode";
    }

    @ApiOperation("添加前置节点")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addPrevious/{nodeId}")
    @ResponseBody
    public void addPreNode(@PathVariable Integer nodeId, AddNodeVo nodeVo) throws Exception {
        if ("".equals(nodeVo.getBlogTitle()) || "".equals(nodeVo.getContent()) || "".equals(nodeVo.getDesc())){
            return;
        }
        User user = userService.getUserById(nodeVo.getUserId());
        int blogId = blogService.insertBlogByTitleAndContent(nodeVo.getBlogTitle(),
                nodeVo.getContent(), nodeVo.getDesc());
        if (blogId == -1){
            throw new Exception("博客内容添加失败");
        }

        int mapId = mapService.insertMapByMapNameAndMapAuthorAndMapData(nodeVo.getBlogTitle(),
                user.getUsername(), nodeVo.getMapData());
        if (mapId == -1){
            throw new Exception("思维导图内容添加失败");
        }
        Node node = new Node();
        node.setUserId(user.getUserId());
        node.setNodeName(nodeVo.getBlogTitle());
        node.setRelationship(nodeVo.getRelationShip());
        node.setBlogId(blogId);
        node.setMapId(mapId);
        node.setCollect(0);
        node.setIsPrivate(nodeVo.getUserId() != 1);
        node.setStar(0);
        node.setCreateDate(new Date());
        node.setUpdateDate(new Date());
        int preId = privateNodeService.insertNode(node);
        if (preId == -1){
            throw new Exception("节点内容添加失败");
        }
        int i = previousService.addPrevious(nodeId, preId);
        if (i == -1){
            throw new Exception("前置关系添加失败");
        }
        int j = nextService.addNext(preId, nodeId);
        if (j == -1){
            throw new Exception("后置关系添加失败");
        }
    }

    @ApiOperation("跳转添加后置节点页面")
    @GetMapping("/toAddNext/{nodeId}")
    public String toAddNexNode(@PathVariable Integer nodeId, Model model){
        model.addAttribute("title", "后置");
        model.addAttribute("rel", "上");
        model.addAttribute("nodeId", nodeId);
        model.addAttribute("isPre", "0");
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(publicNodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(publicNodeService.findNodeByTopStar(5)));
        return "addNode";
    }

    @ApiOperation("添加后置节点")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addNext/{nodeId}")
    @ResponseBody
    public void addNexNode(@PathVariable Integer nodeId, AddNodeVo nodeVo) throws Exception {
        if ("".equals(nodeVo.getBlogTitle()) || "".equals(nodeVo.getContent()) || "".equals(nodeVo.getDesc())){
            return;
        }
        User user = userService.getUserById(nodeVo.getUserId());
        int blogId = blogService.insertBlogByTitleAndContent(nodeVo.getBlogTitle(),
                nodeVo.getContent(), nodeVo.getDesc());
        if (blogId == -1){
            throw new Exception("博客内容添加失败");
        }

        int mapId = mapService.insertMapByMapNameAndMapAuthorAndMapData(nodeVo.getBlogTitle(),
                user.getUsername(), nodeVo.getMapData());
        if (mapId == -1){
            throw new Exception("思维导图内容添加失败");
        }
        Node node = new Node();
        node.setUserId(user.getUserId());
        node.setNodeName(nodeVo.getBlogTitle());
        node.setRelationship(nodeVo.getRelationShip());
        node.setBlogId(blogId);
        node.setMapId(mapId);
        node.setCollect(0);
        node.setIsPrivate(nodeVo.getUserId() != 1);
        node.setStar(0);
        node.setCreateDate(new Date());
        node.setUpdateDate(new Date());
        int nexId = privateNodeService.insertNode(node);
        if (nexId == -1){
            throw new Exception("节点内容添加失败");
        }
        int i = nextService.addNext(nodeId, nexId);
        if (i == -1){
            throw new Exception("前置关系添加失败");
        }
        int j = previousService.addPrevious(nexId, nodeId);
        if (j == -1){
            throw new Exception("后置关系添加失败");
        }
    }

    @ApiOperation("获取节点内容")
    @GetMapping("/getNodeByUserId")
    @ResponseBody
    public PrivateNodeVo getNodeByUserId(@RequestParam Integer nodeId, @RequestParam Integer userId,
                                         HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        Node node;
        Blog blog;
        if (JwtUtils.getUserId(JwtUtils.getToken(cookies)) == 1){
            node = privateNodeService.findNodeByNodeId(nodeId);
            if (node != null){
                blog = blogService.findBlogById(node.getBlogId());
                return nodeToPublicNodeVo(node, blog, 1);
            }
        }
        if (JwtUtils.getUserId(JwtUtils.getToken(cookies)).equals(userId)){
            node = privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(nodeId,userId);
            if (node != null){
                blog = blogService.findBlogById(node.getBlogId());
                return nodeToPublicNodeVo(node,blog, userId);
            }
        }
        node = publicNodeService.findNodeById(nodeId);
        blog = blogService.findBlogById(node.getBlogId());
        return nodeToPublicNodeVo(node, blog, userId);
    }

    @ApiOperation("删除节点内容")
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/deleteNode")
    @ResponseBody
    public String deleteNode(@RequestParam Integer nodeId,
                             @RequestParam boolean flag,
                             HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        Integer userId = -1;
        if (cookies != null){
            userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        }
        else{
            return "用户没有登录";
        }
        User user = userService.getUserById(userId);
        if (user.getIndexId().equals(nodeId)){
            return "用户根节点不允许删除";
        }
        Node node = privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(nodeId, userId);
        if (node == null){
            return "该节点不属于你";
        }
        // pre
        if (flag){
            List<Node> previous = previousService.findPrivatePreviousByNodeIdAndUserId(nodeId, userId);
            if (!previous.isEmpty()){
                return "当前节点还有前置节点";
            }
            else{
                if (deleteNode(nodeId, node.getMapId(), node.getBlogId())){
                    cacheService.deleteNodeByKey(NODE_CACHE + nodeId);
                    cacheService.deleteNodeByKey(BLOG_CACHE + node.getBlogId());
                    return "删除前置节点成功";
                }
            }
        }
        // nex
        else{
            List<Node> next = nextService.findPrivateNextByNodeIdAndUserId(nodeId, userId);
            if (!next.isEmpty()){
                return "当前节点还有后置节点";
            }
            else{
                if (deleteNode(nodeId, node.getMapId(), node.getBlogId())){
                    cacheService.deleteNodeByKey(NODE_CACHE + nodeId);
                    cacheService.deleteNodeByKey(BLOG_CACHE + node.getBlogId());
                    return "删除后置节点成功";
                }
            }
        }
        return "删除失败";
    }

    @ApiOperation("更新节点内容")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateNode/{nodeId}")
    @ResponseBody
    public void updateNode(@PathVariable Integer nodeId, UpdatePrivateVo updateVo,
                              HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return;
        }
        Integer userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        if ("".equals(updateVo.getTitle()) || "".equals(updateVo.getDesc()) || "".equals(updateVo.getBlogContent())){
            return;
        }
        Node node = privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(nodeId, userId);
        Blog blog = blogService.findBlogById(node.getBlogId());
        if (!updateVo.getTitle().equals(node.getNodeName())){
            if (privateNodeService.updateNodeByNodeId(nodeId, updateVo.getTitle()) == -1){
                throw new Exception("更新标题失败");
            }
            cacheService.deleteNodeByKey(NODE_CACHE + nodeId);
        }
        if (updateVo.getSelect().contains("1") || !blog.getBlogTitle().equals(updateVo.getTitle())
                || !blog.getBlogDes().equals(updateVo.getDesc())){
            if (blogService.updateBlogByBlogId(node.getBlogId(), updateVo.getTitle(),
                    updateVo.getBlogContent(), updateVo.getDesc()) == -1){
                throw new Exception("更新博客内容失败");
            }
            cacheService.deleteBlogByKey(BLOG_CACHE + node.getBlogId());
        }
        if (updateVo.getSelect().contains("2")){
            if (mapService.updateMapByMapId(node.getMapId(), updateVo.getMapData()) == -1){
                throw new Exception("更新思维导图失败");
            }
        }
    }

    @ApiOperation("获取所有私有节点")
    @GetMapping("/getAllPrivate")
    @ResponseBody
    public List<NodeVo> getAllPrivateNode(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        return privateNodeService.getAllPrivate(JwtUtils.getUserId(JwtUtils.getToken(cookies)));
    }

    @ApiOperation("获取所有公开节点")
    @GetMapping("/getAllPublic")
    @ResponseBody
    public List<NodeVo> getAllPublicNode(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        return publicNodeService.getAllPublicNode();
    }

    @ApiOperation("全局查询公开节点")
    @GetMapping("/searchNodes")
    @ResponseBody
    public List<PrivateNodeVo> getNodeByStr(Integer pageNumber,Integer pageSize, String str, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        Integer userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        List<PrivateNodeVo> list = new ArrayList<>();
        List<Node> nodes = privateNodeService.getPrivateNodeByStr(pageNumber, pageSize, str, userId);
        nodes.forEach(node ->{
            Blog blog = blogService.findBlogById(node.getBlogId());
            list.add(nodeToPublicNodeVo(node, blog, userId));
        });
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteNode(Integer nodeId, Integer mapId, Integer blogId) throws Exception {
        synchronized (this){
            int i = blogService.deleteBlogByBlogId(blogId);
            if (i == -1){
                throw new Exception("删除博客失败");
            }
            int j = mapService.deleteMapByMapId(mapId);
            if (j == -1){
                throw new Exception("删除map失败");
            }
            int k = privateNodeService.deleteNodeByNodeId(nodeId);
            if (k == -1){
                throw new Exception("删除节点失败");
            }
            if (!previousService.deletePrevious(nodeId)){
                throw new Exception("前置关系删除失败");
            }
            if (!nextService.deleteNext(nodeId)){
                throw new Exception("后置关系删除失败");
            }
            int l = commentService.deleteCommentByNodeId(nodeId);
            if (l == -1){
                throw new Exception("删除评论失败");
            }
        }
        return true;
    }

    private NodeVo nodeToNodeVo(Node node, Integer userId){
        NodeVo nodeVo = new NodeVo();
        nodeVo.setId(node.getNodeId());
        nodeVo.setUrl("/private/node/" + node.getNodeId() + "/" + node.getUserId() + "?isUpdate=0");
        nodeVo.setTitle(node.getNodeName());
        nodeVo.setRelationship(node.getRelationship());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Period p = Period.between(
                LocalDate.parse(df.format(node.getUpdateDate())), LocalDate.now());
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        if (p.getYears() != 0) {
            flag = true;
            sb.append(p.getYears()).append("年");
        }
        if (p.getMonths() != 0){
            flag = true;
            sb.append(p.getMonths()).append("月");
        }
        if (p.getDays() != 0){
            flag = true;
            sb.append(p.getDays()).append("日");
        }
        if (!flag){
            sb.append("今天");
        }
        else{
            sb.append("前");
        }
        nodeVo.setTime(sb.toString());
        nodeVo.setLike(node.getStar());
        nodeVo.setCollect(node.getCollect());
        // 所属用户信息
        User user = userService.getUserById(node.getUserId());
        nodeVo.setUserUrl("/private/user/" + node.getUserId());
        nodeVo.setAvatar(user.getUserAvatar());
        nodeVo.setUpdateUrl("/private/node/" + node.getNodeId() + "/" + userId + "?isUpdate=1");
        return nodeVo;
    }

    private List<SimpleNodeVo> nodeToSimpleNodeVo(List<Node> nodes){
        List<SimpleNodeVo> simpleNodeVos = new ArrayList<>(nodes.size());
        nodes.forEach(node -> {
                    SimpleNodeVo vo = new SimpleNodeVo();
                    vo.setUrl("/public/node/" + node.getNodeId());
                    vo.setTitle(node.getNodeName());
                    simpleNodeVos.add(vo);
                }
        );
        return simpleNodeVos;
    }

    /**
     * 私有的卡片信息
     * @param node node
     * @param blog blog
     * @return PublicNodeVo
     */
    private PrivateNodeVo nodeToPublicNodeVo(Node node, Blog blog, Integer userId){
        PrivateNodeVo nodeVo = new PrivateNodeVo();
        nodeVo.setTitle(node.getNodeName());
        nodeVo.setDesc(blog.getBlogDes());
        nodeVo.setStar(node.getCollect());
        nodeVo.setLike(node.getStar());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Period p = Period.between(
                LocalDate.parse(df.format(node.getUpdateDate())), LocalDate.now());
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        if (p.getYears() != 0) {
            flag = true;
            sb.append(p.getYears()).append("年");
        }
        if (p.getMonths() != 0){
            flag = true;
            sb.append(p.getMonths()).append("月");
        }
        if (p.getDays() != 0){
            flag = true;
            sb.append(p.getDays()).append("日");
        }
        if (!flag){
            sb.append("今天");
        }
        else{
            sb.append("前");
        }
        nodeVo.setTime(sb.toString());
        // 所属用户信息
        User user = userService.getUserById(node.getUserId());
        nodeVo.setUsername(user.getUsername());
        nodeVo.setUserUrl("/private/user/" + node.getUserId());
        nodeVo.setAvatar(user.getUserAvatar());
        nodeVo.setContent(blog.getBlogContent());
        nodeVo.setUrl("/private/node/" + node.getNodeId() + "/" + userId + "?isUpdate=0");
        return nodeVo;
    }


}
