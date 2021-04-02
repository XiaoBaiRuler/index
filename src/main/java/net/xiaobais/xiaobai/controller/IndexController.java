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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/19 21:03
 * @Version 1.0
 */
@Api(tags = "IndexController")
@Controller
public class IndexController {

    @Resource
    private PublicNodeService nodeService;
    @Resource
    private BlogService blogService;

    private static final int SIZE = 1000;



    @ApiOperation("公开首页")
    @GetMapping({"/index","/"})
    public String index(Model model) {

        Node index = nodeService.findIndex();
        Blog blog = blogService.findBlogById(index.getBlogId());
        model.addAttribute("nodeId", index.getNodeId());
        model.addAttribute("html", blog.getBlogContent());
        model.addAttribute("flag", false);
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));
        return "index";
    }

    @ApiOperation("公开节点页")
    @GetMapping("/public/node/{nodeId}")
    public String node(@PathVariable Integer nodeId, Model model){
        Node node = nodeService.findNodeById(nodeId);
        Blog blog = blogService.findBlogById(node.getBlogId());
        model.addAttribute("nodeId", nodeId);
        model.addAttribute("html", blog.getBlogContent());
        if (blog.getBlogContent() != null && blog.getBlogContent().length() > SIZE) {
            model.addAttribute("flag", false);
        }
        else {
            model.addAttribute("flag", true);
        }
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));
        return "index";
    }

    @ApiOperation("是否登录")
    @GetMapping("/user/getUserInfo")
    @ResponseBody
    public UserVo generateLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        String token = cookies[0].getValue();
        if(JwtUtils.checkJwt(token) != null && !JwtUtils.isExpiration(token)){
            return JwtUtils.getUserVo(token);
        }
        return null;
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
