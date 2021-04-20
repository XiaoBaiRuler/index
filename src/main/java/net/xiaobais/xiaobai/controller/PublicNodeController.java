package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.vo.NodeVo;
import net.xiaobais.xiaobai.vo.PublicNodeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/4 17:23
 * @Version 1.0
 */
@Api(tags = "NodeController")
@Controller
public class PublicNodeController {

    @Resource
    private PreviousService previousService;
    @Resource
    private NextService nextService;
    @Resource
    private UserService userService;
    @Resource
    private PublicNodeService publicNodeService;
    @Resource
    private BlogService blogService;
    @Resource
    private CacheService cacheService;

    @ApiOperation("获取非私有前置节点")
    @GetMapping("/public/getPreNode")
    @ResponseBody
    public List<NodeVo> getPreNode(@RequestParam Integer nodeId,
                                   @RequestParam Integer pageNumber,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String title){
        List<Node> previousNodes = null;
        if (title == null || "".equals(title)){
            previousNodes = previousService.findPreviousByNodeId(nodeId, pageNumber, pageSize, 0);
        }
        else{
            previousNodes = previousService.findPreviousByNodeIdAndTitle(nodeId, pageNumber, pageSize, title, 0);
        }
        List<NodeVo> previousNodesVo = new ArrayList<>();
        previousNodes.forEach(node -> previousNodesVo.add(nodeToNodeVo(node)));
        return previousNodesVo;
    }

    @ApiOperation("获取所有非私有前置节点")
    @GetMapping("/public/getAllPreNode")
    @ResponseBody
    public List<Node> getPreNode(@RequestParam Integer nodeId){
        return previousService.findPreviousByNodeId(nodeId);
    }

    @ApiOperation("获取非私有后置节点")
    @GetMapping("/public/getNexNode")
    @ResponseBody
    public List<NodeVo> getNexNode(@RequestParam Integer nodeId,
                                   @RequestParam Integer pageNumber,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String title){
        List<Node> nextNodes = null;
        if (title == null || "".equals(title)) {
            nextNodes = nextService.findNextByNodeId(nodeId, pageNumber, pageSize, 0);
        } else {
            nextNodes = nextService.findNextByNodeIdAndTitle(nodeId, pageNumber, pageSize, title, 0);
        }
        List<NodeVo> nextNodesVo = new ArrayList<>();
        nextNodes.forEach(node -> nextNodesVo.add(nodeToNodeVo(node)));
        return nextNodesVo;
    }

    @ApiOperation("获取所有非私有前置节点")
    @GetMapping("/public/getAllNexNode")
    @ResponseBody
    public List<Node> getNextNode(@RequestParam Integer nodeId){
        return nextService.findNextByNodeId(nodeId);
    }



    @ApiOperation("获取非私有前置节点个数")
    @GetMapping("/public/getPreCount")
    @ResponseBody
    public int getPreNodeCount(@RequestParam Integer nodeId,
                               @RequestParam String title){
        if (title == null || "".equals(title)) {
            return previousService.countPreviousNode(nodeId, 0);
        }
        else{
            return previousService.countPreviousNode(nodeId, title, 0);
        }
    }


    @ApiOperation("获取非私有后置节点个数")
    @GetMapping("/public/getNexCount")
    @ResponseBody
    public int getNexNodeCount(@RequestParam Integer nodeId,
                               @RequestParam String title){
        if (title == null || "".equals(title)) {
            return nextService.countNextNode(nodeId, 0);
        }
        else{
            return nextService.countNextNode(nodeId, title, 0);
        }
    }

    @ApiOperation("获取公开节点的简要信息")
    @GetMapping("/public/getNode")
    @ResponseBody
    public PublicNodeVo getNodeByNodeId(@RequestParam Integer nodeId){
        Node node = publicNodeService.findNodeById(nodeId);
        Blog blog = blogService.findBlogById(node.getBlogId());
        return nodeToPublicNodeVo(node, blog);
    }

    @CrossOrigin
    @ApiOperation("获取收藏节点")
    @GetMapping("/public/getCollectNode")
    @ResponseBody
    public List<NodeVo> getCollectNodeByUserId(@RequestParam Integer userId,
                                               @RequestParam Integer pageNumber,
                                               @RequestParam Integer pageSize,
                                               @RequestParam String title){
        List<Node> nodes = publicNodeService.findNodeByCollect(userId, pageNumber, pageSize, title);
        List<NodeVo> nodeVos = new ArrayList<>();
        nodes.forEach(node -> nodeVos.add(nodeToNodeVo(node)));
        return nodeVos;
    }

    @CrossOrigin
    @ApiOperation("获取公开节点")
    @GetMapping("/public/getPublicNode")
    @ResponseBody
    public List<NodeVo> getPublicNodeByUserId(@RequestParam Integer userId,
                                              @RequestParam Integer pageNumber,
                                              @RequestParam Integer pageSize,
                                              @RequestParam String title){
        List<Node> nodes = publicNodeService.findNodeByPublic(userId, pageNumber, pageSize, title);
        List<NodeVo> nodeVos = new ArrayList<>();
        nodes.forEach(node -> nodeVos.add(nodeToNodeVo(node)));
        return nodeVos;
    }

    @CrossOrigin
    @ApiOperation("获取公开节点")
    @GetMapping("/public/countPublicNode")
    @ResponseBody
    public int countPublicNodeByUserIdAndTitle(@RequestParam Integer userId,
                                               @RequestParam String title){
        return publicNodeService.countPublicNodeByUserIdAndTitle(userId, title);
    }

    @CrossOrigin
    @ApiOperation("获取公开节点")
    @GetMapping("/public/countCollectNode")
    @ResponseBody
    public int countCollectNodeByUserIdAndTitle(@RequestParam Integer userId,
                                                @RequestParam String title){
        return publicNodeService.countCollectNodeByUserIdAndTitle(userId, title);
    }

    @ApiOperation("全局查询公开节点")
    @GetMapping("/public/searchNodes")
    @ResponseBody
    public List<PublicNodeVo> getNodeByStr(Integer pageNumber,Integer pageSize, String str){
        String key = pageNumber + "#" + pageSize + "#" + str;
        List<PublicNodeVo> cacheList = cacheService.getPublicNodeVoListByKey(key);
        if (cacheList == null){
            List<PublicNodeVo> list = new ArrayList<>();
            List<Node> nodes = publicNodeService.getNodeByTitle(pageNumber, pageSize, str);
            nodes.forEach(node ->{
                Blog blog = blogService.findBlogById(node.getBlogId());
                list.add(nodeToPublicNodeVo(node, blog));
            });
            cacheService.setPublicNodeVoListByKey(key, list);
            return list;
        }
        else{
            return cacheList;
        }

    }

    @ApiOperation("全局查询公开节点")
    @GetMapping("/public/getNodeById")
    @ResponseBody
    public PublicNodeVo getNodeById(@RequestParam Integer id){
        Node node = publicNodeService.findNodeById(id);
        Blog blog = blogService.findBlogById(node.getBlogId());
        return nodeToPublicNodeVo(node, blog);
    }

    /**
     * 前后置节点的卡片信息
     * @param node node
     * @return NodeVo
     */
    private NodeVo nodeToNodeVo(Node node){
        NodeVo nodeVo = new NodeVo();
        nodeVo.setId(node.getNodeId());
        nodeVo.setUrl("/public/node/" + node.getNodeId());
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
        nodeVo.setUserUrl("/public/user/" + node.getUserId());
        nodeVo.setAvatar(user.getUserAvatar());
        return nodeVo;
    }

    /**
     * 公开的卡片信息
     * @param node node
     * @param blog blog
     * @return PublicNodeVo
     */
    private PublicNodeVo nodeToPublicNodeVo(Node node, Blog blog){
        PublicNodeVo nodeVo = new PublicNodeVo();
        nodeVo.setId(node.getNodeId());
        nodeVo.setTitle(node.getNodeName());
        nodeVo.setDesc(blog.getBlogDes());
        nodeVo.setCollect(node.getCollect());
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
        nodeVo.setUserUrl("/public/user/" + node.getUserId());
        nodeVo.setAvatar(user.getUserAvatar());
        nodeVo.setEmail(user.getUserEmail());
        nodeVo.setUrl("/public/node/" + node.getNodeId());
        nodeVo.setContent(blog.getBlogContent());
        return nodeVo;
    }
}
