package net.xiaobais.xiaobai.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.MapService;
import net.xiaobais.xiaobai.service.MindService;
import net.xiaobais.xiaobai.service.NodeService;
import net.xiaobais.xiaobai.vo.MindVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private NodeService nodeService;

    private static final String NOTHING = "[]";
    private static final String PREFIX_URL = "/public/node/";

    @ApiOperation("通过nodeId获取Mind数据")
    @GetMapping("/public/getMind")
    @ResponseBody
    public List<MindVo> findMindByNodeId(@RequestParam Integer nodeId){

        Map map = mapService.findMapById(nodeId);
        Node node = nodeService.findNodeById(nodeId);
        if (NOTHING.equals(map.getMapData())){
            List<MindVo> list = new ArrayList<>();
            MindVo mindVo = new MindVo("root" + nodeId, null, true,
                    "<a href='" + PREFIX_URL + nodeId + "'>" + node.getNodeName() + "</a>",
                    null, true);
            list.add(mindVo);
            return list;
        }
        return JSONObject.parseArray(map.getMapData(), MindVo.class);
    }

    @ApiOperation("通过nodeId和层级获取Mind数据")
    @GetMapping("/public/getNodeMind")
    @ResponseBody
    public List<MindVo> findMindByNodeIdAndLevel(@RequestParam Integer nodeId,
                                                 @RequestParam Integer level){
        return mindService.getMindVoByLevel(level, nodeId);
    }

}
