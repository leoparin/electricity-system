package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.VO.ResponseResult;
import com.leo.electricitysystem.VO.UserIDNameVO;
import com.leo.electricitysystem.VO.WorkerInfoVO;
import com.leo.electricitysystem.domain.LoginUser;
import com.leo.electricitysystem.domain.User;
import com.leo.electricitysystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ClassName:LoginServiceImpl
 * PackageName:com.leo.electricitysystem.service
 * Description:
 *
 * @Date 2023/3/17 10:46
 * @Author leo
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从db查询用户
        //select * from sys_user where name = "leo"
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //不存在抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名不存在");
        }

        return new LoginUser(user);
    }

    public ResponseResult getWorkerByRegion(String region){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getUserName,User::getId).
                eq(User::getRegion,region).eq(User::getUserType,"工人");
        List<User> user = userMapper.selectList(queryWrapper);
        List<UserIDNameVO> list = user.stream().map(i ->
                new UserIDNameVO(i.getId(), i.getUserName())
        ).toList();

        return new ResponseResult(200,"get worker list success",list);
    }
}
