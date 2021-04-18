package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.SuggestMapper;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.Suggest;
import net.xiaobais.xiaobai.model.SuggestExample;
import net.xiaobais.xiaobai.model.SuggestWithBLOBs;
import net.xiaobais.xiaobai.service.NoticeService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.service.SuggestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/13 0:13
 * @Version 1.0
 */
@Service
public class SuggestServiceImpl implements SuggestService {

    @Resource
    private SuggestMapper suggestMapper;
    @Resource
    private NoticeService noticeService;
    @Resource
    private PublicNodeService publicNodeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addSuggest(SuggestWithBLOBs suggestWb) throws Exception {
        int insert = suggestMapper.insert(suggestWb);
        if (insert == -1){
            throw new Exception("添加建议节点失败");
        }
        Node node = publicNodeService.findNodeById(suggestWb.getNodeId());
        if (!noticeService.addSuggestNotice(suggestWb.getUserId(), node.getUserId(), node.getNodeId(), suggestWb.getSuggestId())){
            throw new Exception("添加建议通知失败");
        }
        return 1;
    }

    @Override
    public SuggestWithBLOBs getSuggestBySuggestId(Integer suggestId) {
        return suggestMapper.selectByPrimaryKey(suggestId);
    }

    @Override
    public List<Suggest> getSuggestsByNodeId(Integer nodeId) {
        SuggestExample suggestExample = new SuggestExample();
        suggestExample.createCriteria().andNodeIdEqualTo(nodeId);
        return suggestMapper.selectByExample(suggestExample);
    }
}
