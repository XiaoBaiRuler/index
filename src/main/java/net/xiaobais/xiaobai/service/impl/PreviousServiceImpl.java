package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.MyPreviousMapper;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.PreviousService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/3 16:42
 * @Version 1.0
 */
@Service
public class PreviousServiceImpl implements PreviousService {

    @Resource
    private MyPreviousMapper myPreviousMapper;

    @Override
    public List<Node> findPreviousByNodeId(Integer nodeId, Integer pageNumber, Integer pageSize) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * pageSize + 1;
        return myPreviousMapper.findNotPrivatePreviousByNodeId(
                nodeId, pageNumber, pageSize);
    }

    @Override
    public int countPreviousNode(Integer nodeId) {
        return myPreviousMapper.countNotPrivatePreviousByNodeId(nodeId);
    }

    @Override
    public List<Node> findPreviousByNodeIdAndTitle(Integer nodeId, Integer pageNumber,
                                                   Integer pageSize, String title) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * pageSize + 1;
        title = "%" + title + "%";
        return myPreviousMapper.findNotPrivatePreviousByNodeIdAndTitle(
                nodeId, pageNumber, pageSize, title
        );
    }
}
