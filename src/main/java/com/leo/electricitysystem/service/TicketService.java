package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.domain.ElectricCabinet;
import com.leo.electricitysystem.domain.SwitchesAndLights;
import com.leo.electricitysystem.domain.User;
import com.leo.electricitysystem.mapper.CabinetMapper;
import com.leo.electricitysystem.mapper.SwitchMapper;
import com.leo.electricitysystem.mapper.TicketMapper;
import com.leo.electricitysystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ClassName:TicketService
 * PackageName:com.leo.electricitysystem.service
 * Description:
 *
 * @Date 2023/1/6 08:52
 * @Author leo
 **/
@Service
public class TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    /*
     * 查看所有步骤
     */

    /*
     * 查看所有开关
     */

    @Autowired
    private SwitchMapper switchMapper;

    public List<String> getAllSwitch(){
        List<String> switches;
        LambdaQueryWrapper<SwitchesAndLights> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SwitchesAndLights::getSwitchName);
        switches =switchMapper.selectList(queryWrapper).stream()
                .map(SwitchesAndLights::getSwitchName).collect(Collectors.toList());
        if(Objects.isNull(switches)){
            throw new RuntimeException("数据库中无开关");
        }
       return switches;
    }

    @Autowired
    private CabinetMapper cabinetMapper;
    public List<String> getAllCabinet() {
        List<String> cabinet;
        LambdaQueryWrapper<ElectricCabinet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ElectricCabinet::getCabinetName);
        cabinet =cabinetMapper.selectList(queryWrapper).stream()
                .map(ElectricCabinet::getCabinetName).collect(Collectors.toList());
        if(Objects.isNull(cabinet)){
            throw new RuntimeException("数据库中无操作柜");
        }
        return cabinet;
    }

    @Autowired
    private UserMapper userMapper;
    /*
     * @return workerName List
     */
    public List<String>getAllWorker(){
        List<String> worker;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getUserName).eq(User::getUserType,"2");
        worker =userMapper.selectList(queryWrapper).stream()
                .map(User::getUserName).collect(Collectors.toList());
        if(Objects.isNull(worker)){
            throw new RuntimeException("数据库中无工人");
        }
        return worker;
    }

    public List<String>getAllSupervisor(){
        List<String> supervisor;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getUserName).eq(User::getUserType,"3");
        supervisor =userMapper.selectList(queryWrapper).stream()
                .map(User::getUserName).collect(Collectors.toList());
        if(Objects.isNull(supervisor)){
            throw new RuntimeException("数据库中无监督员");
        }
        return supervisor;
    }



}
