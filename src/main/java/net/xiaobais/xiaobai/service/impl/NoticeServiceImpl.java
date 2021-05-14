package net.xiaobais.xiaobai.service.impl;

import com.github.pagehelper.PageHelper;
import net.xiaobais.xiaobai.mapper.IteratorMapper;
import net.xiaobais.xiaobai.mapper.MyNoticeMapper;
import net.xiaobais.xiaobai.mapper.NodeMapper;
import net.xiaobais.xiaobai.mapper.NoticeMapper;
import net.xiaobais.xiaobai.model.*;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.vo.AdminNoticeVo;
import net.xiaobais.xiaobai.vo.IteratorNoticeVo;
import net.xiaobais.xiaobai.vo.PublicNoticeVo;
import net.xiaobais.xiaobai.vo.SuggestNoticeVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Resource
    private FansService fansService;
    @Resource
    private PublicNodeService publicNodeService;
    @Resource
    private NodeMapper nodeMapper;
    @Resource
    private IteratorMapper iteratorMapper;
    @Resource
    private CacheService cacheService;
    @Resource
    private EmailService emailService;

    @Value("${mail.url}")
    private String url;

    private static final String DEAL_PREFIX = "/person/public";
    private static final String NODE_PREFIX = "/public/node/";
    private static final String BLOG_CACHE = "/public/blog";
    private static final String PUBLIC_ID = "/public/getNodeMind";
    private static final String EMAIL_STR = "\n,进入通知:界面:";
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
        emailService.sendMessage(1, notice.getMessage() + EMAIL_STR + url);
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
        emailService.sendMessage(userId, message + EMAIL_STR + url);
        notice.setNodeId(newNodeId);
        notice.setDealUrl(NODE_PREFIX + newNodeId);
        notice.setIsDelete(false);
        int i = noticeMapper.insertSelective(notice);
        if (i == -1){
            throw new Exception("处理通知创建失败");
        }
        // 用户发给粉丝
        User user = userService.getUserById(userId);
        List<Fans> fans = fansService.getAllFansByUserId(userId);
        fans.forEach(fan -> {
            notice.setUserId(userId);
            notice.setAcceptId(fan.getFansId());
            notice.setSubmitType(0);
            notice.setAcceptType(true);
            notice.setMessage("你关注的用户" + user.getUsername() +"发布了博客");
            if (noticeMapper.insertSelective(notice) == -1){
                try {
                    throw new Exception("发布给关注用户的通知创建失败");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
        emailService.sendMessage(userId, notice.getMessage() + EMAIL_STR + url);
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

    @Override
    public Integer getPersonNoticeCount(String message, Integer userId) {
        if ("".equals(message)){
            return myNoticeMapper.findPersonNoticeCount(userId);
        }
        else{
            message = "%" + message + "%";
            return myNoticeMapper.findPersonNoticeCountByMessage(userId, message);
        }
    }

    @Override
    public List<PublicNoticeVo> getAllPersonNotice(Integer pageNumber, Integer pageSize, String message, Integer userId) {
        pageNumber = pageNumber == 0 ? pageNumber : pageNumber * SIZE;
        List<Notice> notices;
        if ("".equals(message)){
            notices = myNoticeMapper.findPersonNotice(pageNumber, pageSize, userId);
        }
        else{
            message = "%" + message + "%";
            notices = myNoticeMapper.findPersonNoticeByMessage(pageNumber, pageSize, message, userId);
        }
        List<PublicNoticeVo> lists = new ArrayList<>();
        notices.forEach(notice -> {
            PublicNoticeVo publicNoticeVo = new PublicNoticeVo();
            User user = userService.getUserById(notice.getUserId());
            publicNoticeVo.setNoticeId(notice.getNoticeId());
            publicNoticeVo.setUsername(user.getUsername());
            publicNoticeVo.setType("反馈通知");
            publicNoticeVo.setMessage(notice.getMessage());
            publicNoticeVo.setNodeId(notice.getNodeId());
            publicNoticeVo.setUserId(notice.getUserId());
            publicNoticeVo.setDealUrl(notice.getDealUrl());
            lists.add(publicNoticeVo);
        });
        return lists;
    }

    @Override
    public boolean dealReplyNotice(Integer noticeId, Integer userId) {
        NoticeExample example = new NoticeExample();
        example.createCriteria().andNoticeIdEqualTo(noticeId)
                .andAcceptIdEqualTo(userId);
        Notice notice = new Notice();
        notice.setIsDelete(true);
        return noticeMapper.updateByExampleSelective(notice, example) != -1;
    }

    @Override
    public boolean addIteratorNotice(Integer userId, Integer acceptId, Integer iteratorId, Integer nodeId, String message) {
        Notice notice = new Notice();
        notice.setUserId(userId);
        notice.setAcceptId(acceptId);
        notice.setNodeId(nodeId);
        notice.setIteratorId(iteratorId);
        notice.setMessage(message);
        emailService.sendMessage(acceptId, message + EMAIL_STR + url);
        notice.setIsDelete(false);
        notice.setSubmitType(1);
        notice.setAcceptType(false);
        return noticeMapper.insertSelective(notice) != -1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean dealIteratorNotice(Integer noticeId, Integer userId, Integer acceptId, Integer iteratorId, Integer nodeId)
            throws Exception {
        User user = userService.getUserById(userId);
        Notice notice = new Notice();
        notice.setUserId(userId);
        notice.setAcceptId(acceptId);
        notice.setMessage(user.getUsername() + "同意了你的迭代请求");
        emailService.sendMessage(acceptId, notice.getMessage() + EMAIL_STR + url);
        notice.setSubmitType(1);
        notice.setAcceptType(true);
        notice.setNodeId(nodeId);
        notice.setIteratorId(iteratorId);
        notice.setIsDelete(false);
        if (noticeMapper.insertSelective(notice) == -1){
            throw new Exception("发送同意迭代请求失败");
        }
        Notice changeNotice = new Notice();
        changeNotice.setNoticeId(noticeId);
        changeNotice.setIsDelete(true);
        if (noticeMapper.updateByPrimaryKeySelective(changeNotice) == -1){
            throw new Exception("删除iterator通知失败");
        }
        // 更换
        Node node = publicNodeService.findNodeById(nodeId);
        Node iterator = publicNodeService.findNodeById(iteratorId);
        node.setUserId(iterator.getUserId());
        node.setBlogId(iterator.getBlogId());
        node.setMapId(iterator.getMapId());
        if (nodeMapper.updateByPrimaryKeySelective(node) == -1){
            throw new Exception("替换迭代节点");
        }
        IteratorExample itExample = new IteratorExample();
        itExample.createCriteria().andIteratorIdEqualTo(iteratorId);
        if (iteratorMapper.deleteByExample(itExample) == -1){
            throw new Exception("删除迭代关系失败");
        }
        if (nodeMapper.deleteByPrimaryKey(iteratorId) == -1){
            throw new Exception("删除迭代节点失败");
        }
        cacheService.deleteNodeByKey(NODE_PREFIX + iteratorId);
        cacheService.deleteNodeByKey(NODE_PREFIX + nodeId);
        cacheService.deleteBlogByKey(BLOG_CACHE + node.getBlogId());
        cacheService.deleteAllMindListByKey(PUBLIC_ID);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean errorIteratorNotice(Integer noticeId, Integer userId, Integer acceptId, Integer iteratorId, Integer nodeId) throws Exception {
        User user = userService.getUserById(userId);
        Notice notice = new Notice();
        notice.setUserId(userId);
        notice.setAcceptId(acceptId);
        notice.setMessage(user.getUsername() + "驳回了你的迭代请求");
        emailService.sendMessage(acceptId, notice.getMessage() + EMAIL_STR + url);
        notice.setSubmitType(1);
        notice.setAcceptType(true);
        notice.setNodeId(nodeId);
        notice.setIteratorId(iteratorId);
        notice.setIsDelete(false);
        if (noticeMapper.insertSelective(notice) == -1){
            throw new Exception("发送驳回迭代请求失败");
        }
        Notice changeNotice = new Notice();
        changeNotice.setNoticeId(noticeId);
        changeNotice.setIsDelete(true);
        if (noticeMapper.updateByPrimaryKeySelective(changeNotice) == -1){
            throw new Exception("删除iterator通知失败");
        }
        return true;
    }

    @Override
    public long getAllIteratorNoticeCount(Integer userId, String message) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria().andAcceptIdEqualTo(userId)
                .andSubmitTypeEqualTo(1).andIsDeleteEqualTo(false);
        if (!"".equals(message)){
            criteria.andMessageLike( "%" + message + "%");
        }
        return noticeMapper.countByExample(example);
    }

    @Override
    public List<IteratorNoticeVo> getAllIteratorNotice(Integer pageNumber, Integer pageSize, Integer userId, String message) {

        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria().andAcceptIdEqualTo(userId)
                .andSubmitTypeEqualTo(1).andIsDeleteEqualTo(false);
        if (!"".equals(message)){
            criteria.andMessageLike("%" + message + "%");
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Notice> notices = noticeMapper.selectByExample(example);
        List<IteratorNoticeVo> list = new ArrayList<>();
        notices.forEach(notice -> {
            IteratorNoticeVo noticeVo = new IteratorNoticeVo();
            noticeVo.setNoticeId(notice.getNoticeId());
            noticeVo.setReply(notice.getAcceptType());
            noticeVo.setMessage(notice.getMessage());
            noticeVo.setIteratorId(notice.getIteratorId());
            noticeVo.setNodeId(notice.getNodeId());
            list.add(noticeVo);
        });
        return list;
    }

    @Override
    public Notice getNoticeByNoticeId(Integer noticeId) {
        return noticeMapper.selectByPrimaryKey(noticeId);
    }

    @Override
    public boolean addSuggestNotice(Integer userId, Integer acceptId, Integer nodeId, Integer suggestId) {
        Notice notice = new Notice();
        notice.setUserId(userId);
        notice.setAcceptId(acceptId);
        notice.setNodeId(nodeId);
        notice.setSubmitType(2);
        notice.setAcceptType(false);
        notice.setIsDelete(false);
        notice.setSuggestId(suggestId);
        notice.setMessage("用户发起了建议请求");
        emailService.sendMessage(acceptId, "有用户发起建议请求" + EMAIL_STR + url);
        return noticeMapper.insertSelective(notice) != -1;
    }

    @Override
    public long getAllSuggestNoticeCount(Integer userId, String message) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria().andAcceptIdEqualTo(userId)
                .andSubmitTypeEqualTo(2).andIsDeleteEqualTo(false);
        if (!"".equals(message)){
            criteria.andMessageLike( "%" + message + "%");
        }
        return noticeMapper.countByExample(example);
    }

    @Override
    public List<SuggestNoticeVo> getAllSuggestNotice(Integer pageNumber, Integer pageSize, Integer userId, String message) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria().andAcceptIdEqualTo(userId)
                .andSubmitTypeEqualTo(2).andIsDeleteEqualTo(false);
        if (!"".equals(message)){
            criteria.andMessageLike("%" + message + "%");
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Notice> notices = noticeMapper.selectByExample(example);
        List<SuggestNoticeVo> list = new ArrayList<>();
        notices.stream().filter(Objects::nonNull).forEach(notice -> {
            SuggestNoticeVo noticeVo = new SuggestNoticeVo();
            noticeVo.setNoticeId(notice.getNoticeId());
            noticeVo.setSuggestId(notice.getSuggestId());
            noticeVo.setNodeId(notice.getNodeId());
            noticeVo.setSuggestId(notice.getSuggestId());
            noticeVo.setMessage(notice.getMessage());
            noticeVo.setReply(notice.getAcceptType());
            list.add(noticeVo);
        });
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int dealSuggestNotice(Integer noticeId, Integer userId) throws Exception {

        Notice notice = noticeMapper.selectByPrimaryKey(noticeId);
        if (notice == null){
            return -1;
        }
        Notice reply = new Notice();
        User user = userService.getUserById(notice.getAcceptId());
        if (notice.getAcceptId().equals(userId)){
            Node node = publicNodeService.findNodeById(notice.getNodeId());
            reply.setUserId(node.getUserId());
            reply.setAcceptId(notice.getUserId());
            reply.setNodeId(notice.getNodeId());
            reply.setSubmitType(2);
            reply.setAcceptType(true);
            reply.setSuggestId(notice.getSuggestId());
            reply.setMessage(user.getUsername() + "根据你的建议修改了博客节点");
            emailService.sendMessage(notice.getUserId(), reply.getMessage() + EMAIL_STR + url);
            reply.setIsDelete(false);
            if (noticeMapper.insertSelective(reply) == -1){
                throw new Exception("添加回复建议通知失败");
            }
            notice.setIsDelete(true);
            if (noticeMapper.updateByPrimaryKeySelective(notice) == -1){
                throw new Exception("删除通知失败");
            }
        }
        return reply.getNoticeId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int errorSuggestNotice(Integer noticeId, Integer userId, String message) throws Exception {
        Notice notice = noticeMapper.selectByPrimaryKey(noticeId);
        if (notice == null){
            return -1;
        }
        User user = userService.getUserById(userId);
        Notice reply = new Notice();
        if (notice.getAcceptId().equals(userId)){
            reply.setUserId(userId);
            reply.setAcceptId(notice.getUserId());
            reply.setNodeId(notice.getNodeId());
            reply.setSubmitType(2);
            reply.setAcceptType(true);
            reply.setSuggestId(notice.getSuggestId());
            reply.setMessage(user.getUsername() + "拒绝了你的建议请求: 理由: " + message);
            emailService.sendMessage(notice.getUserId(), reply.getMessage() + EMAIL_STR + url);
            reply.setIsDelete(false);
            if (noticeMapper.insertSelective(reply) == -1){
                throw new Exception("添加回复建议通知失败");
            }
            notice.setIsDelete(true);
            if (noticeMapper.updateByPrimaryKeySelective(notice) == -1){
                throw new Exception("删除通知失败");
            }
        }
        return reply.getNoticeId();
    }

    @Override
    public List<AdminNoticeVo> getAllNotice(Integer type, Integer pageNumber, Integer pageSize, String message) {
        NoticeExample noticeExample = new NoticeExample();
        NoticeExample.Criteria criteria = noticeExample.createCriteria();
        if (!"".equals(message)){
            criteria.andMessageLike("%" + message + "%");
        }
        if (type != -1){
            criteria.andSubmitTypeEqualTo(type);
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Notice> notices = noticeMapper.selectByExample(noticeExample);
        List<AdminNoticeVo> lists = new ArrayList<>();
        notices.stream().filter(Objects::nonNull).forEach(notice -> {
            AdminNoticeVo adminNoticeVo = new AdminNoticeVo();
            adminNoticeVo.setNoticeId(notice.getNoticeId());
            adminNoticeVo.setUsername(userService.getUserById(notice.getUserId()).getUsername());
            adminNoticeVo.setAcceptName(userService.getUserById(notice.getAcceptId()).getUsername());
            adminNoticeVo.setSubmitType(notice.getSubmitType());
            adminNoticeVo.setNodeId(notice.getNodeId());
            adminNoticeVo.setSuggestId(notice.getSuggestId());
            adminNoticeVo.setIteratorId(notice.getIteratorId());
            adminNoticeVo.setMessage(notice.getMessage());
            adminNoticeVo.setDelete(notice.getIsDelete());
            lists.add(adminNoticeVo);
        });
        return lists;
    }

    @Override
    public Long countAllNotice(Integer type, String message) {
        NoticeExample noticeExample = new NoticeExample();
        NoticeExample.Criteria criteria = noticeExample.createCriteria();
        if (!"".equals(message)){
            criteria.andMessageLike("%" + message + "%");
        }
        if (type != -1){
            criteria.andSubmitTypeEqualTo(type);
        }
        long l = noticeMapper.countByExample(noticeExample);
        return l % 8 == 0 ? l / 8 : l / 8 + 1;
    }

    @Override
    public boolean deleteNotice(Integer noticeId) {
        Notice notice = new Notice();
        notice.setNoticeId(noticeId);
        notice.setIsDelete(true);
        return noticeMapper.updateByPrimaryKeySelective(notice) != -1;
    }

    @Override
    public boolean completeDeleteNotice(Integer noticeId) {
        NoticeExample example = new NoticeExample();
        example.createCriteria().andIsDeleteEqualTo(true)
                .andNoticeIdEqualTo(noticeId);
        return noticeMapper.deleteByExample(example) != -1;
    }

    @Override
    public boolean updateNoticeMessage(Integer noticeId, String message) {
        Notice notice = new Notice();
        notice.setNoticeId(noticeId);
        notice.setMessage(message);
        return noticeMapper.updateByPrimaryKeySelective(notice) != -1;
    }


}
