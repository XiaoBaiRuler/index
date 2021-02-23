package net.xiaobais.xiaobai.mapper;

import java.util.List;
import net.xiaobais.xiaobai.model.Suggest;
import net.xiaobais.xiaobai.model.SuggestExample;
import org.apache.ibatis.annotations.Param;

public interface SuggestMapper {
    long countByExample(SuggestExample example);

    int deleteByExample(SuggestExample example);

    int deleteByPrimaryKey(Integer suggestId);

    int insert(Suggest record);

    int insertSelective(Suggest record);

    List<Suggest> selectByExampleWithBLOBs(SuggestExample example);

    List<Suggest> selectByExample(SuggestExample example);

    Suggest selectByPrimaryKey(Integer suggestId);

    int updateByExampleSelective(@Param("record") Suggest record, @Param("example") SuggestExample example);

    int updateByExampleWithBLOBs(@Param("record") Suggest record, @Param("example") SuggestExample example);

    int updateByExample(@Param("record") Suggest record, @Param("example") SuggestExample example);

    int updateByPrimaryKeySelective(Suggest record);

    int updateByPrimaryKeyWithBLOBs(Suggest record);

    int updateByPrimaryKey(Suggest record);
}