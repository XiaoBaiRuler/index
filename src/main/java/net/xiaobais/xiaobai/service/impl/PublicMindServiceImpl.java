package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.Suggest;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.utils.HtmlUtils;
import net.xiaobais.xiaobai.vo.MindVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    private static final String PUBLIC_USER_URL = "/public/user/";

    private static final String ROOT = "root";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String ITERATOR = "iterator";
    private static final String SUGGEST = "suggest";
    private static final String SUGGEST_NODE = "suggestNode";

    @Override
    public List<MindVo> getMindVoByLevel(Integer level, Integer nodeId){
        Node node = publicNodeService.findNodeById(nodeId);
        if (node == null){
            return null;
        }
        List<MindVo> lists = new ArrayList<>();
        Queue<ArrayList<String>> parent = new LinkedList<>();

        // 根节点
        List<Node> previous;
        int count  = 0;
        MindVo mindVo = new MindVo(ROOT + nodeId, null, true,
                HtmlUtils.publicHtmlToString(nodeId, node.getNodeName()),
                null, true);
        lists.add(mindVo);

        // 迭代节点
        MindVo iterator = new MindVo(ITERATOR + nodeId, ROOT + nodeId, false, ITERATOR, LEFT, true);
        lists.add(iterator);
        List<Node> iterators = iteratorService.getIteratorByNodeId(nodeId);
        iterators.forEach(it -> {
            MindVo mv = new MindVo(LEFT + it.getNodeId(), ITERATOR + nodeId,false,
                    HtmlUtils.publicIteratorToString(it.getNodeId(), it.getNodeName()), LEFT, true);
            lists.add(mv);
        });

        // 前置节点
        previous = previousService.findPreviousByNodeId(nodeId);
        Queue<Node> queue = new LinkedList<>(previous);
        ArrayList<String> pair = new ArrayList<>(2);
        pair.add(ROOT + nodeId);
        pair.add(""+ previous.size());
        parent.add(pair);
        while (!queue.isEmpty() && count < level){
            for (int i = 0; i < parent.size(); i++) {
                ArrayList<String> item = parent.poll();
                for (int j = 0; j < Integer.parseInt(Objects.requireNonNull(item).get(1)); j++) {
                    Node remove = queue.poll();
                    if (remove != null){
                        previous = previousService.findPreviousByNodeId(remove.getNodeId());

                        ArrayList<String> newPair = new ArrayList<>(2);
                        newPair.add(LEFT + remove.getNodeId());
                        newPair.add(""+ previous.size());
                        parent.add(newPair);
                        queue.addAll(previous);

                        MindVo mv = new MindVo(LEFT + remove.getNodeId(), item.get(0), false,
                                HtmlUtils.publicHtmlToString(remove.getNodeId(), remove.getNodeName()),
                                LEFT, true);
                        lists.add(mv);
                    }
                }
            }
            count ++;
        }
        // 建议节点
        MindVo suggest = new MindVo(SUGGEST + nodeId, ROOT + nodeId, false, SUGGEST, RIGHT, true);
        lists.add(suggest);
        List<Suggest> suggests = suggestService.getSuggestsByNodeId(nodeId);
        suggests.forEach(s -> {
            MindVo mv = new MindVo(SUGGEST_NODE + s.getSuggestId(),
                    SUGGEST + nodeId, false,
                     HtmlUtils.publicSuggestToString(s.getSuggestId(), userService.getUserById(s.getUserId()).getUsername()),
                    RIGHT, true);
            lists.add(mv);
        });
        // 后置节点
        queue.clear();
        List<Node> next;
        count = 0;
        next = nextService.findNextByNodeId(nodeId);
        Queue<ArrayList<String>> nextParent = new LinkedList<>();
        queue.addAll(next);

        ArrayList<String> nextPair = new ArrayList<>(2);
        nextPair.add(ROOT + nodeId);
        nextPair.add(""+ next.size());
        nextParent.add(nextPair);
        while (!queue.isEmpty() && count < level){
            for (int i = 0; i < nextParent.size(); i++) {
                ArrayList<String> item = nextParent.poll();
                for (int j = 0; j < Integer.parseInt(item.get(1)); j++) {
                    Node remove = queue.poll();
                    if (remove != null){
                        next = nextService.findNextByNodeId(remove.getNodeId());

                        ArrayList<String> newPair = new ArrayList<>(2);
                        newPair.add(RIGHT + remove.getNodeId());
                        newPair.add(""+ next.size());
                        nextParent.add(newPair);

                        queue.addAll(next);
                        MindVo mv = new MindVo(RIGHT + remove.getNodeId(), item.get(0), false,
                                HtmlUtils.publicHtmlToString(remove.getNodeId(), remove.getNodeName()), RIGHT, true);
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
        MindVo mindVo = new MindVo(ROOT + node.getNodeId(), null, true,
                HtmlUtils.publicHtmlToString(node.getNodeId(), node.getNodeName()),
                null, true);
        lists.add(mindVo);

        // 迭代节点
        Node iterator = publicNodeService.findNodeById(nodeId);
        MindVo mv = new MindVo(LEFT + iterator.getNodeId(),
                ROOT + node.getNodeId(),false,
                HtmlUtils.publicIteratorToString(iterator.getNodeId(), iterator.getNodeName()),
                LEFT, true);
        lists.add(mv);
        return lists;
    }

    @Override
    public List<MindVo> getCollectMindVoByUserId(Integer userId) {
        List<MindVo> lists = new ArrayList<>();
        List<Node> collects = publicNodeService.findNodeByCollect(userId, 0, 8, "");
        User user = userService.getUserById(userId);
        // 根节点
        MindVo mindVo = new MindVo(ROOT + user.getUserId(), null, true,
                "<a href='" + PUBLIC_USER_URL + user.getUserId() + HtmlUtils.A_NEXT + user.getUsername() + HtmlUtils.A_NEXT_URL,
                null, true);
        lists.add(mindVo);
        int i = 0;
        while (i < 4 && i < collects.size()){
            Node node = collects.get(i);
            MindVo mv = new MindVo(LEFT + node.getNodeId(),
                    ROOT + user.getUserId(),false,
                    HtmlUtils.publicHtmlToString(node.getNodeId(), node.getNodeName()),
                    LEFT, true);
            lists.add(mv);
            i ++;
        }
        while (i < 8 && i < collects.size()){
            Node node = collects.get(i);
            MindVo mv = new MindVo(RIGHT + node.getNodeId(),
                    ROOT + user.getUserId(),false,
                    HtmlUtils.publicHtmlToString(node.getNodeId(), node.getNodeName()),
                    RIGHT, true);
            lists.add(mv);
            i ++;
        }
        return lists;
    }

    @Override
    public List<MindVo> getPublicMindVoByUserId(Integer userId) {
        List<MindVo> lists = new ArrayList<>();
        List<Node> pls = publicNodeService.findNodeByPublic(userId, 0, 8, "");
        User user = userService.getUserById(userId);
        // 根节点
        MindVo mindVo = new MindVo(ROOT + user.getUserId(), null, true,
                "<a href='" + PUBLIC_USER_URL + user.getUserId() + HtmlUtils.A_NEXT + user.getUsername() + HtmlUtils.A_NEXT_URL,
                null, true);
        lists.add(mindVo);
        int i = 0;
        while (i < 4 && i < pls.size()){
            Node node = pls.get(i);
            MindVo mv = new MindVo(LEFT + node.getNodeId(),
                    ROOT + user.getUserId(),false,
                    HtmlUtils.publicHtmlToString(node.getNodeId(), node.getNodeName()),
                    LEFT, true);
            lists.add(mv);
            i ++;
        }
        while (i < 8 && i < pls.size()){
            Node node = pls.get(i);
            MindVo mv = new MindVo(RIGHT + node.getNodeId(),
                    ROOT + user.getUserId(),false,
                    HtmlUtils.publicHtmlToString(node.getNodeId(), node.getNodeName()),
                    RIGHT, true);
            lists.add(mv);
            i ++;
        }
        return lists;
    }

}
