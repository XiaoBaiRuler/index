package net.xiaobais.xiaobai.service.impl;

import javafx.util.Pair;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.Suggest;
import net.xiaobais.xiaobai.service.*;
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
public class PublicMindServiceImpl implements PublicMindService {

    @Resource
    private PreviousService previousService;
    @Resource
    private NextService nextService;
    @Resource
    private PublicNodeService publicNodeService;
    @Resource
    private SuggestService suggestService;
    @Resource
    private UserService userService;
    @Resource
    private IteratorService iteratorService;

    private static final String PREFIX_URL = "/public/node/";

    private static final String PREFIX_ITERATOR = "/person/public/iterator/";

    private static final String PREFIX_SUG = "/person/public/getSuggest/";

    @Override
    public List<MindVo> getMindVoByLevel(Integer level, Integer nodeId){
        Node node = publicNodeService.findNodeById(nodeId);
        if (node == null){
            return null;
        }
        List<MindVo> lists = new ArrayList<>();
        Queue<Pair<String, Integer>> parent = new LinkedList<>();

        // 根节点
        List<Node> previous;
        int count  = 0;
        MindVo mindVo = new MindVo("root" + nodeId, null, true,
                "<a href='" + PREFIX_URL + nodeId + "'>" + node.getNodeName() + "</a>",
                null, true);
        lists.add(mindVo);

        // 迭代节点
        MindVo iterator = new MindVo("iterator" + nodeId,
                "root" + nodeId, false,
                "迭代节点",
                "left", true);
        lists.add(iterator);
        List<Node> iterators = iteratorService.getIteratorByNodeId(nodeId);
        iterators.forEach(it -> {
            MindVo mv = new MindVo("left" + it.getNodeId(),
                    "iterator" + nodeId,false,
                    "<a href='" + PREFIX_ITERATOR + it.getNodeId() + "'>" + it.getNodeName() + "</a>",
                    "left", true);
            lists.add(mv);
        });

        // 前置节点
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
                                "<a href='" + PREFIX_URL + remove.getNodeId() + "'>" + remove.getNodeName() + "</a>",
                                "left", true);
                        lists.add(mv);
                    }
                }
            }
            count ++;
        }

        // 建议节点
        MindVo suggest = new MindVo("suggest" + nodeId,
                "root" + nodeId, false,
                "建议节点",
                "right", true);
        lists.add(suggest);
        List<Suggest> suggests = suggestService.getSuggestsByNodeId(nodeId);
        suggests.forEach(s -> {
            MindVo mv = new MindVo("suggestNode" + s.getSuggestId(),
                    "suggest" + nodeId, false,
                    "<a href='" + PREFIX_SUG + s.getSuggestId() + "'>" +
                            userService.getUserById(s.getUserId()).getUsername() + "的建议" + "</a>",
                    "right", true);
            lists.add(mv);
        });

        // 后置节点
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
                                "<a href='" + PREFIX_URL + remove.getNodeId() + "'>" + remove.getNodeName() + "</a>",
                                "right", true);
                        lists.add(mv);
                    }
                }
            }
            count ++;
        }

        return lists;
    }

    @Override
    public List<MindVo> getIteratorMindVoByNodeId(Integer nodeId) {
        List<MindVo> lists = new ArrayList<>();

        // 根节点
        Node node = iteratorService.getNodeByIteratorId(nodeId);
        MindVo mindVo = new MindVo("root" + node.getNodeId(), null, true,
                "<a href='" + PREFIX_URL + node.getNodeId() + "'>" + node.getNodeName() + "</a>",
                null, true);
        lists.add(mindVo);

        // 迭代节点
        Node iterator = publicNodeService.findNodeById(nodeId);
        MindVo mv = new MindVo("left" + iterator.getNodeId(),
                "root" + node.getNodeId(),false,
                "<a href='" + PREFIX_ITERATOR + iterator.getNodeId() + "'>" + iterator.getNodeName() + "</a>",
                "left", true);
        lists.add(mv);
        return lists;
    }


}
