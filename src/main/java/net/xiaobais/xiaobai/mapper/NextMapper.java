package net.xiaobais.xiaobai.mapper;

import java.util.List;
import net.xiaobais.xiaobai.model.Next;
import net.xiaobais.xiaobai.model.NextExample;
import org.apache.ibatis.annotations.Param;

public interface NextMapper {
    long countByExample(NextExample example);

    int deleteByExample(NextExample example);

    int insert(Next record);

    int insertSelective(Next record);

    List<Next> selectByExample(NextExample example);

    int updateByExampleSelective(@Param("record") Next record, @Param("example") NextExample example);

    int updateByExample(@Param("record") Next record, @Param("example") NextExample example);
}