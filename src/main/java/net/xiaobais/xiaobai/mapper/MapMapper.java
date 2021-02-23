package net.xiaobais.xiaobai.mapper;

import java.util.List;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.MapExample;
import org.apache.ibatis.annotations.Param;

public interface MapMapper {
    long countByExample(MapExample example);

    int deleteByExample(MapExample example);

    int deleteByPrimaryKey(Integer mapId);

    int insert(Map record);

    int insertSelective(Map record);

    List<Map> selectByExampleWithBLOBs(MapExample example);

    List<Map> selectByExample(MapExample example);

    Map selectByPrimaryKey(Integer mapId);

    int updateByExampleSelective(@Param("record") Map record, @Param("example") MapExample example);

    int updateByExampleWithBLOBs(@Param("record") Map record, @Param("example") MapExample example);

    int updateByExample(@Param("record") Map record, @Param("example") MapExample example);

    int updateByPrimaryKeySelective(Map record);

    int updateByPrimaryKeyWithBLOBs(Map record);

    int updateByPrimaryKey(Map record);
}