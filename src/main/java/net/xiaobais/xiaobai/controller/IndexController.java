package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.BlogService;
import net.xiaobais.xiaobai.service.MapService;
import net.xiaobais.xiaobai.service.NodeService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author xiaobai
 * @Date 2021/2/19 21:03
 * @Version 1.0
 */
@Api(tags = "IndexController")
@Controller
public class IndexController {

    @Resource
    private NodeService nodeService;
    @Resource
    private BlogService blogService;
    @Resource
    private MapService mapService;

    private static final int SIZE = 1000;



    @ApiOperation("公开首页")
    @GetMapping({"/index","/"})
    public String index(Model model) {

        // 1. 获取当前节点: 根节点的内容
        Node index = nodeService.findIndex();
        // 1.1. 获取当前节点的博客内容
        Blog blog = blogService.findBlogById(index.getBlogId());
        // 1.2. 获取当前节点的视图内容
        Map map = mapService.findMapById(index.getMapId());
        model.addAttribute("nodeId", index.getNodeId());
        model.addAttribute("html", blog.getBlogContent());
        model.addAttribute("map", map.getMapData());
        model.addAttribute("flag", false);
        return "index";
    }

    @ApiOperation("公开节点页")
    @GetMapping("/node/{nodeId}")
    public String node(@PathVariable Integer nodeId, Model model){
        Blog blog = blogService.findBlogById(nodeId);
        Map map = mapService.findMapById(nodeId);
        model.addAttribute("nodeId", nodeId);
        model.addAttribute("html", blog.getBlogContent());
        model.addAttribute("map", map.getMapData());
        if (blog.getBlogContent() != null && blog.getBlogContent().length() > SIZE) {
            model.addAttribute("flag", false);
        } else {
            model.addAttribute("flag", true);
        }
        return "index";
    }

    @ApiOperation("是否登录")
    @GetMapping("/generateLogin")
    @ResponseBody
    public String generateLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "0";
        }
        String token = cookies[0].getValue();
        if(JwtUtils.checkJwt(token) != null && !JwtUtils.isExpiration(token)){
            return "1";
        }
        return "0";
    }

}
