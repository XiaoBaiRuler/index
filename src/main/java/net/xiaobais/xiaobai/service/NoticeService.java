package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.vo.PublicNoticeVo;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/15 22:37
 * @Version 1.0
 */
public interface NoticeService {

    /**
     * 添加发布节点通知
     * @param username username
     * @param nodeId nodeId
     * @param userId userId
     * @param rootId rootId
     * @param flag flag
     * @return int
     * @exception Exception 事务处理
     */
    int addPublicNodeNotice(String username, Integer nodeId, Integer userId, Integer rootId, Integer flag) throws Exception;

    /**
     * 正向处理发布节点通知
     * @param message message
     * @param userId userId
     * @param nodeId nodeId
     * @param newNodeId newNodeId
     * @param noticeId noticeId
     * @return boolean
     * @exception  Exception 事务处理
     */
    boolean dealPublicNodeNotice(String message, Integer userId, Integer nodeId,
                                 Integer newNodeId, Integer noticeId)
            throws Exception;


    /**
     * 驳回发布节点的请求通知
     * @param nodeId nodeId
     * @param message  message
     * @param userId userId
     * @param noticeId noticeId
     * @return boolean
     * @exception Exception 事务处理
     */
    boolean errorPublicNodeNotice(Integer nodeId, String message, Integer userId, Integer noticeId) throws Exception;

    /**
     * 获取所有申请发布博客的通知
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param message message
     * @return List<PublicNoticeVo>
     */
    List<PublicNoticeVo> getAllPublicNotice(Integer pageNumber, Integer pageSize, String message);

    /**
     * 通过message获取所有申请发布博客的通知的个数
     * @param message message
     * @return Integer
     */
    Integer getAllPublicNoticeCount(String message);

    /**
     * 根据message获取所有返回通知的博客个数
     * @param message message
     * @param userId userId
     * @return integer
     */
    Integer getPersonNoticeCount(String message, Integer userId);

    /**
     * 获取所有申请发布博客的通知
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param message message
     * @param userId userId
     * @return List<PublicNoticeVo>
     */
    List<PublicNoticeVo> getAllPersonNotice(Integer pageNumber, Integer pageSize, String message, Integer userId);

    /**
     * 确认返回通知
     * @param noticeId noticeId
     * @param userId userId
     * @return boolean
     */
    boolean dealReplyNotice(Integer noticeId, Integer userId);
}
