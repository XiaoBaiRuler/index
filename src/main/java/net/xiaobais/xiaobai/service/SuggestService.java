package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Suggest;
import net.xiaobais.xiaobai.model.SuggestWithBLOBs;
import net.xiaobais.xiaobai.vo.AdminSuggestVo;

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
     * @exception Exception 事务管理
     */
    int addSuggest(SuggestWithBLOBs suggestWb) throws Exception;

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

    /**
     * 分页模糊查找建议节点
     * @param type type
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param message message
     * @return List<AdminSuggestVo>
     */
    List<AdminSuggestVo> getAllSuggest(Integer type, Integer pageNumber, Integer pageSize, String message);

    /**
     * 统计所有建议节点个数
     * @param type type
     * @param message message
     * @return Long
     */
    Long countAllSuggest(Integer type, String message);

    /**
     * 删除建议节点
     * @param suggestId suggestId
     * @return boolean
     */
    boolean deleteSuggest(Integer suggestId);
}
