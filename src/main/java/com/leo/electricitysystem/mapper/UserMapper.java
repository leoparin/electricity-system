package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.DTO.RegistrationDTO;
import com.leo.electricitysystem.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName:userMapper
 * PackageName:com.leo.mapper
 * Description:
 * @Date 2023/1/4 09:09
 * @Author leo
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
    void saveUser(@Param("dto") RegistrationDTO dto);
}
