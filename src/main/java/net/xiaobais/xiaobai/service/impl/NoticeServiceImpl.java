package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.MyNoticeMapper;
import net.xiaobais.xiaobai.mapper.NoticeMapper;
import net.xiaobais.xiaobai.model.Notice;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.NoticeService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.vo.PublicNoticeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/15 22:37
 * @Version 1.0
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private MyNoticeMapper myNoticeMapper;
    @Resource
    private UserService userService;

    private static final String DEAL_PREFIX = "/person/public";
    private static final String NODE_PREFIX = "/public/node/";
    private static final Integer SIZE = 5;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addPublicNodeNotice(String username, Integer nodeId, Integer userId,
                                   Integer rootId, Integer flag) throws Exception {

        Notice notice = new Notice();
        notice.setUserId(userId);
        notice.setAcceptId(1);
        notice.setSubmitType(0);
        notice.setAcceptType(false);
        notice.setNodeId(nodeId);
        String str = flag == 1 ? "前置方向" : "后置方向";
        notice.setMessage(username + "请求发布博客到<<" + nodeId + ">>的" + str);

        notice.setDealUrl(str);
        notice.setErrorUrl(DEAL_PREFIX + "/errorPublicNodeNotice");
        notice.setIsDelete(false);
        if (noticeMapper.insertSelective(notice) == -1){
            throw new Exception("创建通知失败");
        }
        str = "dealPublicNodeNotice/" + notice.getNoticeId() + "/"
                + nodeId + "/"  + userId + "/" + rootId + "/" + flag;
        Notice changNotice = new Notice();
        changNotice.setNoticeId(notice.getNoticeId());
        changNotice.setDealUrl(str);
        if (noticeMapper.updateByPrimaryKeySelective(changNotice) == -1){
            throw new Exception("更新失败");
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean dealPublicNodeNotice(String message, Integer userId,
                                    Integer nodeId, Integer newNodeId, Integer noticeId) throws Exception {
        // 管理员发给用户
        Notice notice = new Notice();
        notice.setUserId(1);
        notice.setAcceptId(userId);
        notice.setSubmitType(0);
        notice.setAcceptType(true);
        notice.setMessage(message);
        notice.setNodeId(nodeId);
        notice.setIsDelete(false);
        int i = noticeMapper.insertSelective(notice);
        if (i == -1){
            throw new Exception("处理通知创建失败");
        }
        notice.setUserId(userId);
        notice.setAcceptId(-1);
        notice.setSubmitType(0);
        notice.setAcceptType(true);
        notice.setMessage("你关注的用户发布了博客:" + NODE_PREFIX + nodeId);
        notice.setNodeId(newNodeId);
        int j = noticeMapper.insertSelective(notice);
        if (j == -1){
            throw new Exception("发布给关注用户的通知创建失败");
        }
        Notice changeNotice = new Notice();
        changeNotice.setNoticeId(noticeId);
        changeNotice.setIsDelete(true);
        if (noticeMapper.updateByPrimaryKeySelective(changeNotice) == -1){
            throw new Exception("删除通知失败");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean errorPublicNodeNotice(Integer nodeId, String message, Integer userId, Integer noticeId) throws Exception {
        Notice notice = new Notice();
        notice.setUserId(1);
        notice.setAcceptId(userId);
        notice.setSubmitType(0);
        notice.setAcceptType(true);
        notice.setNodeId(nodeId);
        notice.setMessage("管理拒绝你的发布请求，理由" + message);
        notice.setIsDelete(false);
        if (noticeMapper.insertSelective(notice) == -1){
            throw new Exception("创建错误通知失败");
        }
        Notice changeNotice = new Notice();
        changeNotice.setNoticeId(noticeId);
        changeNotice.setIsDelete(true);
        if (noticeMapper.updateByPrimaryKeySelective(changeNotice) == -1){
            throw new Exception("删除通知失败");
        }
        return true;
    }

    @Override
    public List<PublicNoticeVo> getAllPublicNotice(Integer pageNumber, Integer pageSize, String message) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * SIZE;
        List<Notice> notices;
        if ("".equals(message)){
            notices = myNoticeMapper.findPublicNotice(pageNumber, pageSize);
        }
        else{
            message = "%" + message + "%";
            notices = myNoticeMapper.findPublicNoticeByMessage(pageNumber, pageSize, message);
        }
        List<PublicNoticeVo> lists = new ArrayList<>();
        notices.forEach(notice -> {
            PublicNoticeVo publicNoticeVo = new PublicNoticeVo();
            User user = userService.getUserById(notice.getUserId());
            publicNoticeVo.setNoticeId(notice.getNoticeId());
            publicNoticeVo.setUsername(user.getUsername());
            publicNoticeVo.setType("发布博客请求");
            publicNoticeVo.setMessage(notice.getMessage());
            publicNoticeVo.setNodeId(notice.getNodeId());
            publicNoticeVo.setDealUrl(notice.getDealUrl());
            publicNoticeVo.setErrorUrl(notice.getErrorUrl());
            publicNoticeVo.setUserId(notice.getUserId());
            lists.add(publicNoticeVo);
        });
        return lists;
    }

    @Override
    public Integer getAllPublicNoticeCount(String message) {
        if ("".equals(message)){
            return myNoticeMapper.findPublicNoticeCount();
        }
        else{
            message = "%" + message + "%";
            return myNoticeMapper.findPublicNoticeByMessageCount(message);
        }
    }
}
