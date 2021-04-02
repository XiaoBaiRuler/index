package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.NextService;
import net.xiaobais.xiaobai.service.PreviousService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.NodeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
            nextNodes = nextService.findNextByNodeIdAndTitle(nodeId, pageNumber, pageSize, title,
                    userId == 1 ? 1 : 0);
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

}
