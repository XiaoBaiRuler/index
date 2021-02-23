package net.xiaobais.xiaobai.mapper;

import java.util.List;
import net.xiaobais.xiaobai.model.BlogConfig;
import net.xiaobais.xiaobai.model.BlogConfigExample;
import org.apache.ibatis.annotations.Param;

public interface BlogConfigMapper {
    long countByExample(BlogConfigExample example);

    int deleteByExample(BlogConfigExample example);

    int deleteByPrimaryKey(Integer configId);

    int insert(BlogConfig record);

    int insertSelective(BlogConfig record);

    List<BlogConfig> selectByExample(BlogConfigExample example);

    BlogConfig selectByPrimaryKey(Integer configId);

    int updateByExampleSelective(@Param("record") BlogConfig record, @Param("example") BlogConfigExample example);

    int updateByExample(@Param("record") BlogConfig record, @Param("example") BlogConfigExample example);

    int updateByPrimaryKeySelective(BlogConfig record);

    int updateByPrimaryKey(BlogConfig record);
}