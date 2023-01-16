package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.domain.*;
import com.leo.electricitysystem.exception.IdNotFoundException;
import com.leo.electricitysystem.mapper.*;
import com.leo.electricitysystem.request.FullTicket;
import com.leo.electricitysystem.response.ResponseResult;
import lombok.extern.java.Log;
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

    public ResponseResult getAllSwitch(){
        List<SwitchOrLight> switches;
        LambdaQueryWrapper<SwitchOrLight> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SwitchOrLight::getId,SwitchOrLight::getSwitchName);
        switches =switchMapper.selectList(queryWrapper);
        if(Objects.isNull(switches)){
            throw new RuntimeException("数据库中无开关");
        }
        return new ResponseResult(HttpStatus.OK.value(),switches);
    }

    @Autowired
    private CabinetMapper cabinetMapper;
    public ResponseResult getAllCabinet() {
        Map<Long,String> cabinet;
        LambdaQueryWrapper<ElectricCabinet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ElectricCabinet::getId,ElectricCabinet::getCabinetName);
        cabinet =cabinetMapper.selectList(queryWrapper).stream()
                .collect(Collectors.toMap(ElectricCabinet::getId, ElectricCabinet::getCabinetName));
        if(Objects.isNull(cabinet)){
            throw new RuntimeException("数据库中无操作柜");
        }
        return new ResponseResult(HttpStatus.OK.value(),cabinet);
    }

    @Autowired
    private UserMapper userMapper;
    /*
     * @return workerName List
     */
    public ResponseResult getAllWorker(){
        Map<Long,String> worker;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getId,User::getUserName)
                .eq(User::getUserType,"工人");
        worker = userMapper.selectList(queryWrapper).stream()
                .collect(Collectors.toMap(User::getId, User::getUserName));
        if(Objects.isNull(worker)){
            throw new RuntimeException("数据库中无工人");
        }
        return new ResponseResult(HttpStatus.OK.value(),worker);
    }

    public ResponseResult getAllSupervisor(){
        Map<Long,String> supervisor;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getUserName).eq(User::getUserType,"3");
        supervisor = userMapper.selectList(queryWrapper).stream()
                .collect(Collectors.toMap(User::getId, User::getUserName));
        if(Objects.isNull(supervisor)){
            throw new RuntimeException("数据库中无监督员");
        }
        return new ResponseResult(HttpStatus.OK.value(),supervisor);
    }

    /*
     * 管理员写操作票
     */
    public ResponseResult saveTicket(FullTicket fullTicket){
        ticketMapper.insertTicket(fullTicket);

        List<String> steps = fullTicket.getSteps();
        for(int i = 1;i<=steps.size();i++){
            ticketMapper.insertSteps(i, steps.get(i - 1),fullTicket.getTicketId());
        }
        //TODO: 插入失败处理
        return new ResponseResult(HttpStatus.OK.value() , "write ticket success");
    }

    /*
     * 输入：currentPage
     * 根据id查看操作票，包括ticket表所有字段
     *
     */
    public ResponseResult getTicketListPage(int currentPage){
        //获取loginUser id
        //todo:page size metadata
        //TODO: 做登陆的时候改成从contextHolder里面取授权对象
        LoginUser user = new LoginUser(3L,"Josh" , "工人");
        //从数据库查询
//        LambdaQueryWrapper<OperationTicket> queryWrapper = new LambdaQueryWrapper<>();
//
//        queryWrapper.select(OperationTicket::getCreateTime,OperationTicket::getId,OperationTicket::getAdminId,
//                OperationTicket::getWorkerId);
//        List<OperationTicket> result = ticketMapper.selectList(queryWrapper);
        //todo 取current page做参数

        int offset = currentPage * 5;
        List<OperationTicket> result= ticketMapper.selectTicketPageByUserID(offset,user);
        if(result.size()==0){
            throw new IdNotFoundException("get page fail,do not have enough ticket");
        }//TODO: 修改throws
        return new ResponseResult(HttpStatus.OK.value(),"get page fail,do not have enough ticket",result);
    }

    @Autowired
    private StepMapper stepMapper;

    /*
     * 根据操作票id查寻详情，单表查询step单表，点进去则可以查看错误
     * @return: List<OperationSteps> ordered
     */
    public ResponseResult getTicketSteps(Long ticketId){
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
        //return list of step Map
        //根据ticket查step表
        if(steps.size()==0){
            throw new IdNotFoundException("get steps fail,ticket does not exist");
        }
        return new ResponseResult(HttpStatus.OK.value(),"get steps success",steps);
    }

    /*
     * 根据操作票状态查询
     */
    public ResponseResult getTicketByStatus(String status) {
        LambdaQueryWrapper<OperationTicket> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.select(
                        OperationTicket::getCreateTime, OperationTicket::getId,
                        OperationTicket::getAdminId, OperationTicket::getAdminName,
                        OperationTicket::getWorkerId, OperationTicket::getWorkerName, OperationTicket::getSupervisorName,
                        OperationTicket::getCompleteStatus, OperationTicket::getTaskName)
                .eq(OperationTicket::getCompleteStatus, status);

        List<OperationTicket> result =  ticketMapper.selectList(queryWrapper);

        return new ResponseResult(200,result);

    }

    public ResponseResult delete(Long id) {
        int flag =  ticketMapper.deleteById(id);
        if(flag==0){
            throw new IdNotFoundException("ticket id not found, delete fail");
        }
        return new ResponseResult(HttpStatus.OK.value(),"delete success");
    }

    public ResponseResult getTicketAmount() {
            //TODO:context holder
        LoginUser user = new LoginUser(10L,"Josh" , "工人");
        int result = ticketMapper.selectTicketAmount(user);
        if(result==0){
            throw new IdNotFoundException("no ticket in current account");
        }
        return new ResponseResult(HttpStatus.OK.value(),"get amount success",result);
    }
}
