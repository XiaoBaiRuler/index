package net.xiaobais.xiaobai.mapper;

import java.util.List;
import net.xiaobais.xiaobai.model.Suggest;
import net.xiaobais.xiaobai.model.SuggestExample;
import net.xiaobais.xiaobai.model.SuggestWithBLOBs;
import org.apache.ibatis.annotations.Param;

public interface SuggestMapper {
    long countByExample(SuggestExample example);

    int deleteByExample(SuggestExample example);

    int deleteByPrimaryKey(Integer suggestId);

    int insert(SuggestWithBLOBs record);

    int insertSelective(SuggestWithBLOBs record);

    List<SuggestWithBLOBs> selectByExampleWithBLOBs(SuggestExample example);

    List<Suggest> selectByExample(SuggestExample example);

    SuggestWithBLOBs selectByPrimaryKey(Integer suggestId);

    int updateByExampleSelective(@Param("record") SuggestWithBLOBs record, @Param("example") SuggestExample example);

    int updateByExampleWithBLOBs(@Param("record") SuggestWithBLOBs record, @Param("example") SuggestExample example);

    int updateByExample(@Param("record") Suggest record, @Param("example") SuggestExample example);

    int updateByPrimaryKeySelective(SuggestWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SuggestWithBLOBs record);

    int updateByPrimaryKey(Suggest record);
}