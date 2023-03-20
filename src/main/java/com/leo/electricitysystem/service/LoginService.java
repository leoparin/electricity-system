package com.leo.electricitysystem.service;

import com.leo.electricitysystem.DTO.RegistrationDTO;
import com.leo.electricitysystem.VO.ResponseResult;
import com.leo.electricitysystem.domain.User;

/**
 * ClassName:LoginService
 * PackageName:com.leo.electricitysystem.service
 * Description:
 *
 * @Date 2023/3/17 10:25
 * @Author leo
 **/
public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult registration(RegistrationDTO dto);
}
