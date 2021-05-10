package net.xiaobais.xiaobai.service.impl;

import com.github.pagehelper.PageHelper;
import net.xiaobais.xiaobai.mapper.SuggestMapper;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.Suggest;
import net.xiaobais.xiaobai.model.SuggestExample;
import net.xiaobais.xiaobai.model.SuggestWithBLOBs;
import net.xiaobais.xiaobai.service.NoticeService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.service.SuggestService;
import net.xiaobais.xiaobai.vo.AdminSuggestVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private static final String PREFIX = "/person/public/getSuggest/";

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

    @Override
    public List<AdminSuggestVo> getAllSuggest(Integer type, Integer pageNumber, Integer pageSize, String message) {
        SuggestExample example = new SuggestExample();
        SuggestExample.Criteria criteria = example.createCriteria();
        if (type != 0){
            criteria.andChoiceLike("%" + type + "%");
        }
        if (type == 1){
            criteria.andQuestionLike("%" + message + "%");
        }
        else if (type == 2){
            criteria.andExtendLike("%" + message + "%");
        }
        List<AdminSuggestVo> lists = new ArrayList<>();
        PageHelper.startPage(pageNumber, pageSize);
        List<Suggest> suggests = suggestMapper.selectByExample(example);
        suggests.stream().filter(Objects::nonNull).forEach(s -> {
            AdminSuggestVo adminSuggestVo = new AdminSuggestVo();
            adminSuggestVo.setSuggestId(s.getSuggestId());
            adminSuggestVo.setNodeId(s.getNodeId());
            adminSuggestVo.setUrl(PREFIX + s.getSuggestId());
            lists.add(adminSuggestVo);
        });
        return lists;
    }

    @Override
    public Long countAllSuggest(Integer type, String message) {
        SuggestExample example = new SuggestExample();
        SuggestExample.Criteria criteria = example.createCriteria();
        if (type != 0){
            criteria.andChoiceLike("%" + type + "%");
        }
        if (type == 1){
            criteria.andQuestionLike("%" + message + "%");
        }
        else if (type == 2){
            criteria.andExtendLike("%" + message + "%");
        }
        long l = suggestMapper.countByExample(example);
        return  l % 8 == 0 ? l / 8 : l / 8 + 1;
    }

    @Override
    public boolean deleteSuggest(Integer suggestId) {
        return suggestMapper.deleteByPrimaryKey(suggestId) != -1;
    }
}
