package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Suggest;
import net.xiaobais.xiaobai.model.SuggestWithBLOBs;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/13 0:08
 * @Version 1.0
 */
public interface SuggestService {

    /**
     * 添加建议节点
     * @param suggestWb suggest with BLOBs
     * @return int
     */
    int addSuggest(SuggestWithBLOBs suggestWb);

    /**
     * 通过suggestId获取suggest
     * @param suggestId suggestId
     * @return suggestWb
     */
    SuggestWithBLOBs getSuggestBySuggestId(Integer suggestId);

    /**
     * 通过nodeId获取suggest列表
     * @param nodeId nodeId
     * @return List<Suggest>
     */
    List<Suggest> getSuggestsByNodeId(Integer nodeId);
}
