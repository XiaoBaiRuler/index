package net.xiaobais.xiaobai.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.vo.MindVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    private PublicMindService publicMindService;
    @Resource
    private PrivateMindService privateMindService;
    @Resource
    private PublicNodeService publicNodeService;
    @Resource
    private PrivateNodeService privateNodeService;

    private static final String NOTHING = "[]";
    private static final String PREFIX_URL = "/public/node/";
    private static final String PRIVATE_URL = "/private/node/";

    @ApiOperation("通过nodeId获取Mind数据(public)")
    @GetMapping("/public/getMind")
    @ResponseBody
    public List<MindVo> findMindByNodeId(@RequestParam Integer nodeId){

        Map map = mapService.findMapById(nodeId);
        Node node = publicNodeService.findNodeById(nodeId);
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

    @ApiOperation("通过公开的nodeId和层级获取Mind数据")
    @GetMapping("/public/getNodeMind")
    @ResponseBody
    public List<MindVo> findMindByNodeIdAndLevel(@RequestParam Integer nodeId,
                                                 @RequestParam Integer level){
        return publicMindService.getMindVoByLevel(level, nodeId);
    }

    @ApiOperation("通过nodeId获取所有迭代节点")
    @GetMapping("/person/public/getIteratorMind")
    @ResponseBody
    public List<MindVo> findIteratorMindByNodeId(@RequestParam Integer nodeId){
        return publicMindService.getIteratorMindVoByNodeId(nodeId);
    }


    @ApiOperation("通过nodeId和userId获取Mind数据(private)")
    @GetMapping("/private/getMind")
    @ResponseBody
    public List<MindVo> findPrivateMindByNodeId(@RequestParam Integer nodeId,
                                                @RequestParam Integer userId){
        Map map = mapService.findMapById(nodeId);
        Node node = privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(nodeId, userId);
        if (NOTHING.equals(map.getMapData())){
            List<MindVo> list = new ArrayList<>();
            MindVo mindVo = new MindVo("root" + nodeId, null, true,
                    "<a href='" + PRIVATE_URL + nodeId + "/" + userId + "'>"
                            + node.getNodeName() + "</a>",
                    null, true);
            list.add(mindVo);
            return list;
        }
        return JSONObject.parseArray(map.getMapData(), MindVo.class);
    }

    @ApiOperation("通过私有的nodeId和userId和层级获取Mind数据")
    @GetMapping("/private/getNodeMind")
    @ResponseBody
    public List<MindVo> findPrivateMindByNodeIdAndUserIdAndLevel(@RequestParam Integer nodeId,
                                                                 @RequestParam Integer userId,
                                                                 @RequestParam Integer level){
        return privateMindService.getPrivateMindVoByLevel(level, nodeId, userId);
    }

    @ApiOperation("通过nodeId获取所有迭代节点")
    @GetMapping("/private/getIteratorMind")
    @ResponseBody
    public List<MindVo> findPrivateIteratorMindByNodeId(@RequestParam Integer nodeId,
                                                 @RequestParam Integer userId){
        return privateMindService.getIteratorMindVoByNodeId(nodeId, userId);
    }


    @CrossOrigin
    @ApiOperation("通过userID获取用户的最新8个公开节点")
    @GetMapping("/public/getPublicMind")
    @ResponseBody
    public List<MindVo> findPublicMindVoByUserId(@RequestParam Integer userId){
        return publicMindService.getPublicMindVoByUserId(userId);
    }

    @CrossOrigin
    @ApiOperation("通过userID获取用户的最新8个公开节点")
    @GetMapping("/public/getCollectMind")
    @ResponseBody
    public List<MindVo> findCollectMindVoByUserId(@RequestParam Integer userId){
        return publicMindService.getCollectMindVoByUserId(userId);
    }
}
