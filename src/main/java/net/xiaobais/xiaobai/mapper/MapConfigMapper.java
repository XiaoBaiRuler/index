package net.xiaobais.xiaobai.mapper;

import java.util.List;
import net.xiaobais.xiaobai.model.MapConfig;
import net.xiaobais.xiaobai.model.MapConfigExample;
import org.apache.ibatis.annotations.Param;

public interface MapConfigMapper {
    long countByExample(MapConfigExample example);

    int deleteByExample(MapConfigExample example);

    int deleteByPrimaryKey(Integer configId);

    int insert(MapConfig record);

    int insertSelective(MapConfig record);

    List<MapConfig> selectByExample(MapConfigExample example);

    MapConfig selectByPrimaryKey(Integer configId);

    int updateByExampleSelective(@Param("record") MapConfig record, @Param("example") MapConfigExample example);

    int updateByExample(@Param("record") MapConfig record, @Param("example") MapConfigExample example);

    int updateByPrimaryKeySelective(MapConfig record);

    int updateByPrimaryKey(MapConfig record);
}