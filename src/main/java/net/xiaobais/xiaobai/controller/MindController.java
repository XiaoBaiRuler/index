package net.xiaobais.xiaobai.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.service.MapService;
import net.xiaobais.xiaobai.service.MindService;
import net.xiaobais.xiaobai.vo.MindVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/7 19:57
 * @Version 1.0
 */
@Api(tags = "MindController")
@Controller
public class MindController {

    @Resource
    private MapService mapService;
    @Resource
    private MindService mindService;

    @ApiOperation("通过nodeId获取Mind数据")
    @GetMapping("/getMind")
    @ResponseBody
    public List<MindVo> findMindByNodeId(@RequestParam Integer nodeId){

        Map map = mapService.findMapById(nodeId);
        return JSONObject.parseArray(map.getMapData(), MindVo.class);
    }

    @ApiOperation("通过nodeId和层级获取Mind数据")
    @GetMapping("/getNodeMind")
    @ResponseBody
    public List<MindVo> findMindByNodeIdAndLevel(@RequestParam Integer nodeId,
                                                 @RequestParam Integer level){
        return mindService.getMindVoByLevel(level, nodeId);
    }

}
