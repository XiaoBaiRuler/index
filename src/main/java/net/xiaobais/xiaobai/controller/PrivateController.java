package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.BlogService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
import net.xiaobais.xiaobai.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/27 21:42
 * @Version 1.0
 */
@Api(tags = "PrivateController")
@Controller
@RequestMapping("/private/")
public class PrivateController {

    @Resource
    private PublicNodeService nodeService;
    @Resource
    private BlogService blogService;

    @ApiOperation("用户个人根节点")
    @GetMapping("/node/{indexId}/{userId}")
    public String toPrivateIndex(@PathVariable Integer indexId, @PathVariable Integer userId,
                                 HttpServletRequest request, Model model){
        // 检测是否是当前用户
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "login";
        }
        UserVo userVo = JwtUtils.getUserVo(cookies[0].getValue());
        if (!userVo.getUserId().equals(userId)){
            return "error/403";
        }
        // 获取根节点
        Node node = nodeService.findNodeById(indexId);
        Blog blog = blogService.findBlogById(node.getBlogId());
        model.addAttribute("nodeId", indexId);
        model.addAttribute("html", blog.getBlogContent());
        model.addAttribute("flag", false);

        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));
        return "private";
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
