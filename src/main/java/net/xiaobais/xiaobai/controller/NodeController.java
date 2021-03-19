package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.NextService;
import net.xiaobais.xiaobai.service.PreviousService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.vo.NodeVo;
import org.springframework.stereotype.Controller;
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
public class NodeController {

    @Resource
    private PreviousService previousService;
    @Resource
    private NextService nextService;
    @Resource
    private UserService userService;

    @ApiOperation("获取前置节点")
    @GetMapping("/public/getPreNode")
    @ResponseBody
    public List<NodeVo> getPreNode(@RequestParam Integer nodeId,
                                   @RequestParam Integer pageNumber,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String title){
        List<Node> previousNodes = null;
        if (title == null || "".equals(title)){
            previousNodes = previousService.findPreviousByNodeId(nodeId, pageNumber, pageSize);
        }
        else{
            previousNodes = previousService.findPreviousByNodeIdAndTitle(nodeId, pageNumber, pageSize, title);
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

    @ApiOperation("获取后置节点")
    @GetMapping("/public/getNexNode")
    @ResponseBody
    public List<NodeVo> getNexNode(@RequestParam Integer nodeId,
                                   @RequestParam Integer pageNumber,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String title){
        List<Node> nextNodes = null;
        if (title == null || "".equals(title)) {
            nextNodes = nextService.findNextByNodeId(nodeId, pageNumber, pageSize);
        } else {
            nextNodes = nextService.findNextByNodeIdAndTitle(nodeId, pageNumber, pageSize, title);
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



    @ApiOperation("获取前置节点个数")
    @GetMapping("/public/getPreCount")
    @ResponseBody
    public int getPreNodeCount(@RequestParam Integer nodeId,
                               @RequestParam String title){
        if (title == null || "".equals(title)) {
            return previousService.countPreviousNode(nodeId);
        }
        else{
            return previousService.countPreviousNode(nodeId, title);
        }
    }


    @ApiOperation("获取后置节点个数")
    @GetMapping("/public/getNexCount")
    @ResponseBody
    public int getNexNodeCount(@RequestParam Integer nodeId,
                               @RequestParam String title){
        if (title == null || "".equals(title)) {
            return nextService.countNextNode(nodeId);
        }
        else{
            return nextService.countNextNode(nodeId, title);
        }
    }

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
}
