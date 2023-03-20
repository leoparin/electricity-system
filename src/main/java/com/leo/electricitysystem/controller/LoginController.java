package com.leo.electricitysystem.controller;

import com.leo.electricitysystem.DTO.RegistrationDTO;
import com.leo.electricitysystem.VO.ResponseResult;
import com.leo.electricitysystem.domain.User;
import com.leo.electricitysystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:LoginController
 * PackageName:com.leo.electricitysystem.controller
 * Description:
 *
 * @Date 2023/3/17 10:55
 * @Author leo
 **/
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @PostMapping("/registration")
    public ResponseResult Registration(@RequestBody RegistrationDTO dto){
        return loginService.registration(dto);
    }


    @RequestMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

}