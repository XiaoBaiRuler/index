package net.xiaobais.xiaobai.mapper;

import java.util.List;
import net.xiaobais.xiaobai.model.RoleAuthority;
import net.xiaobais.xiaobai.model.RoleAuthorityExample;
import org.apache.ibatis.annotations.Param;

public interface RoleAuthorityMapper {
    long countByExample(RoleAuthorityExample example);

    int deleteByExample(RoleAuthorityExample example);

    int insert(RoleAuthority record);

    int insertSelective(RoleAuthority record);

    List<RoleAuthority> selectByExample(RoleAuthorityExample example);

    int updateByExampleSelective(@Param("record") RoleAuthority record, @Param("example") RoleAuthorityExample example);

    int updateByExample(@Param("record") RoleAuthority record, @Param("example") RoleAuthorityExample example);
}