package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.SuggestMapper;
import net.xiaobais.xiaobai.model.Suggest;
import net.xiaobais.xiaobai.model.SuggestExample;
import net.xiaobais.xiaobai.model.SuggestWithBLOBs;
import net.xiaobais.xiaobai.service.SuggestService;
import org.springframework.stereotype.Service;

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

    @Override
    public int addSuggest(SuggestWithBLOBs suggestWb) {
        return suggestMapper.insert(suggestWb);
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
