package net.xiaobais.xiaobai.mapper;

import java.util.List;
import net.xiaobais.xiaobai.model.NodeConfig;
import net.xiaobais.xiaobai.model.NodeConfigExample;
import org.apache.ibatis.annotations.Param;

public interface NodeConfigMapper {
    long countByExample(NodeConfigExample example);

    int deleteByExample(NodeConfigExample example);

    int deleteByPrimaryKey(Integer configId);

    int insert(NodeConfig record);

    int insertSelective(NodeConfig record);

    List<NodeConfig> selectByExample(NodeConfigExample example);

    NodeConfig selectByPrimaryKey(Integer configId);

    int updateByExampleSelective(@Param("record") NodeConfig record, @Param("example") NodeConfigExample example);

    int updateByExample(@Param("record") NodeConfig record, @Param("example") NodeConfigExample example);

    int updateByPrimaryKeySelective(NodeConfig record);

    int updateByPrimaryKey(NodeConfig record);
}