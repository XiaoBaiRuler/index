package net.xiaobais.xiaobai.mapper;

import java.util.List;
import net.xiaobais.xiaobai.model.Previous;
import net.xiaobais.xiaobai.model.PreviousExample;
import org.apache.ibatis.annotations.Param;

public interface PreviousMapper {
    long countByExample(PreviousExample example);

    int deleteByExample(PreviousExample example);

    int insert(Previous record);

    int insertSelective(Previous record);

    List<Previous> selectByExample(PreviousExample example);

    int updateByExampleSelective(@Param("record") Previous record, @Param("example") PreviousExample example);

    int updateByExample(@Param("record") Previous record, @Param("example") PreviousExample example);
}