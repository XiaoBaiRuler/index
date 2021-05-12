package net.xiaobais.xiaobai.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.utils.HtmlUtils;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.MindVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private CacheService cacheService;

    private static final String NOTHING = "[]";
    private static final String MIND_ID = "/public/getMindById";
    private static final String PUBLIC_ID = "/public/getNodeMind";
    private static final String PRIVATE_ID = "/private/getNodeMind";
    private static final String ROOT = "root";

    @ApiOperation("通过mindId获取Mind数据(public)")
    @GetMapping("/public/getMindById")
    @ResponseBody
    public List<MindVo> findMindByMindId(@RequestParam Integer mindId){
        List<MindVo> list = cacheService.getMindListByKey(MIND_ID + mindId);
        if (list == null){
            Map map = mapService.findMapById(mindId);
            if (map == null || NOTHING.equals(map.getMapData())){
                list = new ArrayList<>();
                MindVo mindVo = new MindVo("root", null, true, "这个节点没有导图呢", null, true);
                list.add(mindVo);
                return list;
            }
            list = JSONObject.parseArray(map.getMapData(), MindVo.class);
            cacheService.setMindListByKey(MIND_ID + mindId, list);
        }
        return list;
    }

    @ApiOperation("通过nodeId获取Mind数据(public)")
    @GetMapping("/public/getMind")
    @ResponseBody
    public List<MindVo> findMindByNodeId(@RequestParam Integer nodeId){
        Node node = publicNodeService.findNodeById(nodeId);
        Map map = mapService.findMapById(node.getMapId());
        if (map == null || NOTHING.equals(map.getMapData())){
            List<MindVo> list = new ArrayList<>();
            MindVo mindVo = new MindVo(ROOT + nodeId, null, true,
                    HtmlUtils.publicSimpleHtmlToString(nodeId, node.getNodeName()), null, true);
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
        String key = PUBLIC_ID + nodeId + "/" + level;
        List<MindVo> list = cacheService.getMindListByKey(key);
        if (list == null){
            list = publicMindService.getMindVoByLevel(level, nodeId);
            cacheService.setMindListByKey(key, list);
        }
        return list;
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
    public List<MindVo> findPrivateMindByNodeId(@RequestParam Integer nodeId, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        Integer userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        Node node = privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(nodeId, userId);
        Map map = mapService.findMapById(node.getMapId());
        if (map == null || NOTHING.equals(map.getMapData())){
            List<MindVo> list = new ArrayList<>();
            MindVo mindVo = new MindVo(ROOT + nodeId, null, true,
                    HtmlUtils.privateSimpleHtmlToString(node.getNodeId(),userId, node.getNodeName()),
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
        String key = PRIVATE_ID + nodeId + "/" + userId + "/" + level;
        List<MindVo> list = cacheService.getMindListByKey(key);
        if (list == null){
            list = privateMindService.getPrivateMindVoByLevel(level, nodeId, userId);
            cacheService.setMindListByKey(key, list);
        }
        return list;
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
