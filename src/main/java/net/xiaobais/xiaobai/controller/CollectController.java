package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.NodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/3/4 17:31
 * @Version 1.0
 */
@Api(tags = "CollectController")
@Controller
public class CollectController {

    @Resource
    private NodeService nodeService;

    @ApiOperation("添加收藏")
    @GetMapping("/addNodeStar")
    @ResponseBody
    public String addNodeCount(@RequestParam Integer nodeId){
        nodeService.addCount(nodeId);
        return "false";
    }

}
