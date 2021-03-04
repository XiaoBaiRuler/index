package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.BlogService;
import net.xiaobais.xiaobai.service.MapService;
import net.xiaobais.xiaobai.service.NodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

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


    @ApiOperation("首页")
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

}
