package net.xiaobais.xiaobai.service.impl;

import javafx.util.Pair;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.MindService;
import net.xiaobais.xiaobai.service.NextService;
import net.xiaobais.xiaobai.service.NodeService;
import net.xiaobais.xiaobai.service.PreviousService;
import net.xiaobais.xiaobai.vo.MindVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author xiaobai
 * @Date 2021/3/7 23:28
 * @Version 1.0
 */
@Service
public class MindServiceImpl implements MindService {

    @Resource
    private PreviousService previousService;
    @Resource
    private NextService nextService;
    @Resource
    private NodeService nodeService;

    @Override
    public List<MindVo> getMindVoByLevel(Integer level, Integer nodeId){
        Node node = nodeService.findNodeById(nodeId);
        if (node == null){
            return null;
        }
        List<MindVo> lists = new ArrayList<>();
        Queue<Pair<String, Integer>> parent = new LinkedList<>();
        List<Node> previous;
        int count  = 0;
        MindVo mindVo = new MindVo("root" + nodeId, null, true,
                "<a href='/node/" + nodeId + "'>" + node.getNodeName() + "</a>",
                null, true);
        lists.add(mindVo);

        previous = previousService.findPreviousByNodeId(nodeId);
        Queue<Node> queue = new LinkedList<>(previous);
        parent.add(new Pair("root" + nodeId, previous.size()));
        while (count < level){
            while (!queue.isEmpty()){
                for (int i = 0; i < parent.size(); i++) {
                    Pair<String, Integer> item = parent.remove();
                    for (int j = 0; j < item.getValue(); j++) {
                        Node remove = queue.remove();
                        previous = previousService.findPreviousByNodeId(remove.getNodeId());
                        parent.add(new Pair("left" + remove.getNodeId(), previous.size()));
                        queue.addAll(previous);
                        MindVo mv = new MindVo("left" + remove.getNodeId(),
                                item.getKey(), false,
                                "<a href='/node/" + remove.getNodeId() + "'>" + remove.getNodeName() + "</a>",
                                "left", true);
                        lists.add(mv);
                    }
                }
            }
            count ++;
        }

        List<Node> next;
        count = 0;
        next = nextService.findNextByNodeId(nodeId);
        Queue<Pair<String, Integer>> nextParent = new LinkedList<>();
        queue.addAll(next);
        nextParent.add(new Pair("root" + nodeId, next.size()));
        while (count < level){
            while (!queue.isEmpty()){
                for (int i = 0; i < nextParent.size(); i++) {
                    Pair<String, Integer> item = nextParent.remove();
                    for (int j = 0; j < item.getValue(); j++) {
                        Node remove = queue.remove();
                        next = nextService.findNextByNodeId(remove.getNodeId());
                        nextParent.add(new Pair("right" + remove.getNodeId(), next.size()));
                        queue.addAll(next);
                        MindVo mv = new MindVo("right" + remove.getNodeId(), item.getKey(), false,
                                "<a href='/node/" + remove.getNodeId() + "'>" + remove.getNodeName() + "</a>",
                                "right", true);
                        lists.add(mv);
                    }
                }
            }
            count ++;
        }

        return lists;
    }
}
