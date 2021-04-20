package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.utils.HtmlUtils;
import net.xiaobais.xiaobai.vo.MindVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author xiaobai
 * @Date 2021/3/31 10:38
 * @Version 1.0
 */
@Service
public class PrivateMindServiceImpl implements PrivateMindService {

    @Resource
    private PreviousService previousService;
    @Resource
    private NextService nextService;
    @Resource
    private PrivateNodeService privateNodeService;

    private static final String ROOT = "root";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    @Override
    public List<MindVo> getPrivateMindVoByLevel(Integer level, Integer nodeId, Integer userId){
        Node node = privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(nodeId, userId);
        if (node == null){
            return null;
        }
        List<MindVo> lists = new ArrayList<>();
        Queue<ArrayList<String>> parent = new LinkedList<>();

        // 根节点
        List<Node> previous;
        int count  = 0;
        MindVo mindVo = new MindVo(ROOT + nodeId, null, true,
                HtmlUtils.privateSimpleHtmlToString(nodeId, userId, node.getNodeName())
                , null, true);
        lists.add(mindVo);

        // 前置节点
        previous = previousService.findPrivatePreviousByNodeIdAndUserId(nodeId, userId);
        Queue<Node> queue = new LinkedList<>(previous);

        ArrayList<String> pItem = new ArrayList<>(2);
        pItem.add(ROOT + nodeId);
        pItem.add("" + previous.size());
        parent.add(pItem);
        while (count < level){
            while (!queue.isEmpty()){
                for (int i = 0; i < parent.size(); i++) {
                    ArrayList<String> item = parent.poll();
                    for (int j = 0; j < Integer.parseInt(item.get(1)); j++) {
                        Node remove = queue.poll();
                        if (remove != null){
                            previous = previousService.findPrivatePreviousByNodeIdAndUserId(remove.getNodeId(), userId);

                            ArrayList<String> newItem = new ArrayList<>();
                            newItem.add(LEFT + remove.getNodeId());
                            newItem.add("" + previous.size());
                            parent.add(newItem);
                            queue.addAll(previous);
                            MindVo mv = new MindVo(LEFT + remove.getNodeId(),
                                    item.get(0), false,
                                    HtmlUtils.privateHtmlToString(remove.getNodeId(), userId,
                                            remove.getNodeName(), LEFT + remove.getNodeId()),
                                    LEFT, true);
                            lists.add(mv);
                        }
                    }
                }
            }
            count ++;
        }

        // 后置节点
        List<Node> next;
        count = 0;
        next = nextService.findPrivateNextByNodeIdAndUserId(nodeId, userId);
        Queue<ArrayList<String>> nextParent = new LinkedList<>();
        queue.addAll(next);

        ArrayList<String> nItem = new ArrayList<>(2);
        nItem.add(ROOT + nodeId);
        nItem.add("" + next.size());
        nextParent.add(nItem);

        while (count < level){
            while (!queue.isEmpty()){
                for (int i = 0; i < nextParent.size(); i++) {
                    ArrayList<String> item = nextParent.poll();
                    for (int j = 0; j < Integer.parseInt(item.get(1)); j++) {
                        Node remove = queue.poll();
                        if (remove != null){
                            next = nextService.findPrivateNextByNodeIdAndUserId(remove.getNodeId(), userId);

                            ArrayList<String> newItem = new ArrayList<>();
                            newItem.add(RIGHT + remove.getNodeId());
                            newItem.add("" + next.size());
                            nextParent.add(newItem);

                            queue.addAll(next);
                            MindVo mv = new MindVo(RIGHT + remove.getNodeId(), item.get(0), false,
                                    HtmlUtils.privateHtmlToString(remove.getNodeId(), userId, remove.getNodeName(), RIGHT + remove.getNodeId()),
                                    RIGHT, true);
                            lists.add(mv);
                        }
                    }
                }
            }
            count ++;
        }
        return lists;
    }
}
