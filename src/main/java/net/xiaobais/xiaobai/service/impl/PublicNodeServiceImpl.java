package net.xiaobais.xiaobai.service.impl;

import com.github.pagehelper.PageHelper;
import net.xiaobais.xiaobai.mapper.MyNodeMapper;
import net.xiaobais.xiaobai.mapper.NodeMapper;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.NodeExample;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.vo.NodeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/3 13:09
 * @Version 1.0
 */
@Service
public class PublicNodeServiceImpl implements PublicNodeService {

    @Resource
    private NodeMapper nodeMapper;
    @Resource
    private MyNodeMapper myNodeMapper;

    private static final Integer SIZE = 4;

    @Override
    public Node findIndex() {
        return nodeMapper.selectByPrimaryKey(1);
    }

    @Override
    public Node findPublicNodeByNodeId(Integer nodeId) {
        NodeExample example = new NodeExample();
        example.createCriteria().andNodeIdEqualTo(nodeId)
                .andIsPrivateEqualTo(false);
        List<Node> nodes = nodeMapper.selectByExample(example);
        return nodes != null && nodes.size() > 0 ? nodes.get(0) : null;
    }

    @Override
    public Node findNodeById(Integer id) {
        return nodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addLike(Integer id) {
        Node node = nodeMapper.selectByPrimaryKey(id);
        node.setStar(node.getStar() + 1);
        nodeMapper.updateByPrimaryKeySelective(node);
    }

    @Override
    public void addCount(Integer id) {
        Node node = nodeMapper.selectByPrimaryKey(id);
        node.setCollect(node.getCollect() + 1);
        nodeMapper.updateByPrimaryKeySelective(node);
    }

    @Override
    public int insertNode(Node node) {
        int insert = nodeMapper.insert(node);
        return insert != -1 ? node.getNodeId() : -1;
    }

    @Override
    public Node findNodeByNodeIdAndNotIsPrivate(Integer id) {
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andNodeIdEqualTo(id)
                .andIsPrivateEqualTo(false);
        List<Node> nodes = nodeMapper.selectByExample(nodeExample);
        return nodes.isEmpty() ? null : nodes.get(0);
    }

    @Override
    public List<Node> findNodeByTopCollect(Integer top) {
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andIsPrivateEqualTo(false);
        nodeExample.setOrderByClause("collect desc");
        PageHelper.startPage(1, top);
        return nodeMapper.selectByExample(nodeExample);
    }

    @Override
    public List<Node> findNodeByTopStar(Integer top) {
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andIsPrivateEqualTo(false);
        nodeExample.setOrderByClause("star desc");
        PageHelper.startPage(1, top);
        return nodeMapper.selectByExample(nodeExample);
    }

    @Override
    public List<Node> findNodeByCollect(Integer userId, Integer pageNumber, Integer pageSize, String title) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * SIZE;
        if ("".equals(title)){
            return myNodeMapper.findCollectNodeByUserId(userId, pageNumber, pageSize);
        }
        else{
            title = "%" + title + "%";
            return myNodeMapper.findCollectNodeByUserIdAndTitle(userId, pageNumber, pageSize, title);
        }
    }

    @Override
    public List<Node> findNodeByLike(Integer userId, Integer pageNumber, Integer pageSize, String title) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * SIZE;
        if ("".equals(title)){
            return myNodeMapper.findLikeNodeByUserId(userId, pageNumber, pageSize);
        }
        else {
            title = "%" + title + "%";
            return myNodeMapper.findLikeNodeByUserIdAndTitle(userId, pageNumber, pageSize, title);
        }
    }

    @Override
    public List<Node> findNodeByPublic(Integer userId, Integer pageNumber, Integer pageSize, String title) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * SIZE;
        if ("".equals(title)){
            return myNodeMapper.findPublicNodeByUserId(userId, pageNumber, pageSize);
        }
        else {
            title = "%" + title + "%";
            return myNodeMapper.findPublicNodeByUserIdAndTitle(userId, pageNumber, pageSize, title);
        }
    }

    @Override
    public Integer countPublicNodeByUserIdAndTitle(Integer userId, String title) {
        title = "%" + title + "%";
        return myNodeMapper.countPublicNodeByUserIdAndTitle(userId, title);
    }

    @Override
    public Integer countCollectNodeByUserIdAndTitle(Integer userId, String title) {
        title = "%" + title + "%";
        return myNodeMapper.countCollectNodeByUserIdAndTitle(userId, title);
    }

    @Override
    public List<NodeVo> getAllPublicNode() {
        NodeExample nodeExample = new NodeExample();
        nodeExample.createCriteria().andIsPrivateEqualTo(false);
        List<Node> nodes = nodeMapper.selectByExample(nodeExample);
        List<NodeVo> nodeVos = new ArrayList<>();
        if (nodes == null || nodes.isEmpty()){
            return nodeVos;
        }
        nodes.forEach(node -> {
            NodeVo nodeVo = new NodeVo();
            if (!node.getNodeName().contains("=>")){
                nodeVo.setTitle(node.getNodeName());
                nodeVo.setId(node.getNodeId());
                nodeVos.add(nodeVo);
            }
        });
        return nodeVos;
    }

    @Override
    public List<Node> getNodeByTitle(Integer pageNumber, Integer pageSize, String title) {
        NodeExample example = new NodeExample();
        example.createCriteria().andNodeNameLike("%" + title + "%")
                .andIsPrivateEqualTo(false);
        PageHelper.startPage(pageNumber, pageSize);
        return nodeMapper.selectByExample(example);
    }

    @Override
    public Long getNodeCountByTitle(String title, Integer pageSize) {
        NodeExample example = new NodeExample();
        example.createCriteria().andNodeNameLike("%" + title + "%")
                .andIsPrivateEqualTo(false);
        long l = nodeMapper.countByExample(example);
        return l % pageSize == 0 ? l / pageSize : l / pageSize + 1;
    }

}
