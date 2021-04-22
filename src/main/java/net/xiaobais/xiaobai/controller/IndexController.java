package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.BlogService;
import net.xiaobais.xiaobai.service.CacheService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.UserVo;
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
    private PublicNodeService nodeService;
    @Resource
    private BlogService blogService;
    @Resource
    private CacheService cacheService;

    private static final int SIZE = 1000;

    private static final String NODE_CACHE = "/public/node/";
    private static final String BLOG_CACHE = "/public/blog/";


    @ApiOperation("公开首页")
    @GetMapping({"/index","/"})
    public String index(Model model) {
        Node node = cacheService.getNodeByKey(NODE_CACHE + 1);
        if (node == null){
            node = nodeService.findIndex();
        }
        Blog blog = cacheService.getBlogByKey(BLOG_CACHE + node.getBlogId());
        if (blog == null){
            blog = blogService.findBlogById(node.getBlogId());
        }
        model.addAttribute("nodeId", node.getNodeId());
        model.addAttribute("html", blog.getBlogContent());
        model.addAttribute("flag", false);
        return "index";
    }

    @ApiOperation("公开节点页")
    @GetMapping("/public/node/{nodeId}")
    public String node(@PathVariable Integer nodeId, Model model){
        Node node = cacheService.getNodeByKey(NODE_CACHE + nodeId);
        if (node == null){
            node = nodeService.findPublicNodeByNodeId(nodeId);
        }
        else if (node.getIsPrivate()){
            return "/error/403";
        }
        Blog blog = cacheService.getBlogByKey(BLOG_CACHE + node.getBlogId());
        if (blog == null){
            blog = blogService.findBlogById(node.getBlogId());
        }
        model.addAttribute("nodeId", nodeId);
        model.addAttribute("html", blog.getBlogContent());
        if (blog.getBlogContent() != null && blog.getBlogContent().length() > SIZE) {
            model.addAttribute("flag", false);
        }
        else {
            model.addAttribute("flag", true);
        }
        return "index";
    }

    @ApiOperation("全局查询公开节点")
    @GetMapping("/public/search")
    public String getNodeByStr(Integer pageNumber,Integer pageSize, String str, Model model){
        model.addAttribute("str", str);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("size", nodeService.getNodeCountByTitle(str, pageSize));
        return "publicSearch";
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

}
