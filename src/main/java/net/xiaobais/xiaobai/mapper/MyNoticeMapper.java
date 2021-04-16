package net.xiaobais.xiaobai.mapper;

import net.xiaobais.xiaobai.model.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/16 20:45
 * @Version 1.0
 */
public interface MyNoticeMapper {

    /**
     * 通过pageNumber和pageSize分页
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @return List<Notice>
     */
    List<Notice> findPublicNotice(Integer pageNumber, Integer pageSize);

    /**
     * 通过message查找分页
     * @param pageNumber pageNumber
     * @param pageSize pageSize
     * @param message message
     * @return
     */
    List<Notice> findPublicNoticeByMessage(@Param("pageNumber") Integer pageNumber,
                                           @Param("pageSize") Integer pageSize,
                                           @Param("message") String message);

    /**
     * 获取所有公开notice的个数
     * @return Integer
     */
    Integer findPublicNoticeCount();

    /**
     * 通过message获取所有公开notice的个数
     * @param message message
     * @return Integer
     */
    Integer findPublicNoticeByMessageCount(@Param("message") String message);
}
