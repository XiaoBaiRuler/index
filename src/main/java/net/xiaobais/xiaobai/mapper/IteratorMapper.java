package net.xiaobais.xiaobai.mapper;

import java.util.List;
import net.xiaobais.xiaobai.model.Iterator;
import net.xiaobais.xiaobai.model.IteratorExample;
import org.apache.ibatis.annotations.Param;

public interface IteratorMapper {
    long countByExample(IteratorExample example);

    int deleteByExample(IteratorExample example);

    int insert(Iterator record);

    int insertSelective(Iterator record);

    List<Iterator> selectByExample(IteratorExample example);

    int updateByExampleSelective(@Param("record") Iterator record, @Param("example") IteratorExample example);

    int updateByExample(@Param("record") Iterator record, @Param("example") IteratorExample example);
}