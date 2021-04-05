package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.AddNodeVo;
import net.xiaobais.xiaobai.vo.NodeVo;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
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
    private PrivateNodeService nodeService;
    @Resource
    private PublicNodeService publicNodeService;

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
            userId = JwtUtils.getUserId(cookies[0].getValue());
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
        previousNodes.forEach(node -> previousNodesVo.add(nodeToNodeVo(node)));
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
            userId = JwtUtils.getUserId(cookies[0].getValue());
        }
        List<Node> nextNodes = null;
        if (title == null || "".equals(title)) {
            nextNodes = nextService.findNextByNodeId(nodeId, pageNumber, pageSize, userId != 1 ? 1 : 0);
        } else {
            nextNodes = nextService.findNextByNodeIdAndTitle(nodeId, pageNumber, pageSize, title, userId != 1 ? 1 : 0);
        }
        List<NodeVo> nextNodesVo = new ArrayList<>();
        nextNodes.forEach(node -> nextNodesVo.add(nodeToNodeVo(node)));
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
            userId = JwtUtils.getUserId(cookies[0].getValue());
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
            userId = JwtUtils.getUserId(cookies[0].getValue());
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
        int preId = nodeService.insertNode(node);
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
        int nexId = nodeService.insertNode(node);
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

    private NodeVo nodeToNodeVo(Node node){
        NodeVo nodeVo = new NodeVo();
        nodeVo.setId(node.getNodeId());
        nodeVo.setUrl("/private/node/" + node.getNodeId() + "/" + node.getUserId());
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


}
