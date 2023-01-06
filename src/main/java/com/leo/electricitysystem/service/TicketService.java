package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.domain.*;
import com.leo.electricitysystem.mapper.*;
import com.leo.electricitysystem.request.FullTicket;
import com.leo.electricitysystem.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
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
        LambdaQueryWrapper<SwitchOrLight> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SwitchOrLight::getSwitchName);
        switches =switchMapper.selectList(queryWrapper).stream()
                .map(SwitchOrLight::getSwitchName).collect(Collectors.toList());
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
        supervisor = userMapper.selectList(queryWrapper).stream()
                .map(User::getUserName).collect(Collectors.toList());
        if(Objects.isNull(supervisor)){
            throw new RuntimeException("数据库中无监督员");
        }
        return supervisor;
    }

    /*
     * 管理员写操作票
     */
    //TODO：fullTicket 去userid，name等信息
    public ResponseResult writeTicket(FullTicket fullTicket){
        ticketMapper.insertTicket(fullTicket);

        List<String> steps = fullTicket.getSteps();
        for(int i = 1;i<=steps.size();i++){
            ticketMapper.insertSteps(i, steps.get(i - 1),fullTicket.getTicketId());
        }
        //TODO: 插入失败处理
        return new ResponseResult<>(200 , "write ticket success");
    }

    /*
     * 输入：currentPage
     * 根据id查看操作票，包括ticket表所有字段
     */
    public ResponseResult getTicketRecord(){
        //获取loginUser id
        //TODO: 做登陆的时候改成从contextHolder里面取授权对象
        LoginUser user = new LoginUser(3L,"Josh" , "工人");
        //从数据库查询
//        LambdaQueryWrapper<OperationTicket> queryWrapper = new LambdaQueryWrapper<>();
//
//        queryWrapper.select(OperationTicket::getCreateTime,OperationTicket::getId,OperationTicket::getAdminId,
//                OperationTicket::getWorkerId);
//        List<OperationTicket> result = ticketMapper.selectList(queryWrapper);
        //todo 取current page做参数
        List<OperationTicket> result= ticketMapper.selectTicketPageByUserID(0,user);

        return new ResponseResult<>(HttpStatus.OK.value(),result);
    }

    @Autowired
    private StepMapper stepMapper;

    /*
     * 根据操作票id查寻详情，单表查询step单表，点进去则可以查看错误
     * @return: List<Map<column_name,value>>
     */
    public ResponseResult getTicketInfoById(Long ticketId){
        //前端返回选择了哪张ticket
        LambdaQueryWrapper<OperationStep> queryWrapper = new LambdaQueryWrapper<>();

        //select stepOrder,description,step id,complete status from step table
        queryWrapper.select(OperationStep::getStepOrder,OperationStep::getDescription,
                OperationStep::getId,OperationStep::getCompleteStatus)
                .eq(OperationStep::getTicketId,ticketId)
                .orderByAsc(OperationStep::getStepOrder);

//        Map<Integer, OperationStep> steps = stepMapper.selectList(queryWrapper)
//                .stream().collect(Collectors.toMap(OperationStep::getStepOrder, Function.identity()));
        List<OperationStep> steps = stepMapper.selectList(queryWrapper);
        //根据ticket查step表
        return new ResponseResult(HttpStatus.OK.value(),steps);
    }

}
