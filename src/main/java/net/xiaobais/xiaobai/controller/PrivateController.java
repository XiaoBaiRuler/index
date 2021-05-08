package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.BlogService;
import net.xiaobais.xiaobai.service.PrivateNodeService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
import net.xiaobais.xiaobai.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Resource
    private UserService userService;
    @Resource
    private PrivateNodeService privateNodeService;

    @ApiOperation("用户个人根节点")
    @GetMapping("/node/{indexId}/{userId}")
    public String toPrivateIndex(@PathVariable Integer indexId, @PathVariable Integer userId,
                                 @RequestParam Integer isUpdate, HttpServletRequest request,
                                 Model model){
        // 检测是否是当前用户
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "login";
        }
        UserVo userVo = JwtUtils.getUserVo(JwtUtils.getToken(cookies));
        if (!userVo.getUserId().equals(userId)){
            return "error/403";
        }
        // 获取根节点
        Node node = nodeService.findNodeById(indexId);
        Blog blog = blogService.findBlogById(node.getBlogId());

        model.addAttribute("isUpdate", isUpdate);
        model.addAttribute("nodeId", indexId);
        model.addAttribute("html", blog.getBlogContent());
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));
        return "private";
    }


    @ApiOperation("全局查询私有节点")
    @GetMapping("/search")
    public String getNodeByStr(Integer pageNumber, Integer pageSize, String str, Model model, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "/error/403";
        }
        Integer userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        model.addAttribute("str", str);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("size", privateNodeService.getPrivateNodeCountByStr(str, userId, pageSize));
        return "privateSearch";
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
