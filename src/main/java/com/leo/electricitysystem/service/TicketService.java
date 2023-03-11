package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.domain.*;
import com.leo.electricitysystem.domain.result.CabinetResult;
import com.leo.electricitysystem.domain.result.StepResult;
import com.leo.electricitysystem.exception.IdNotFoundException;
import com.leo.electricitysystem.mapper.*;
import com.leo.electricitysystem.domain.request.FullTicket;
import com.leo.electricitysystem.domain.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
@ConfigurationProperties(prefix = "ticket.page")
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
        switches = switchMapper.selectList(queryWrapper);
        if(Objects.isNull(switches)){
            throw new RuntimeException("数据库中无开关");
        }
        return new ResponseResult(HttpStatus.OK.value(),"get switch success",switches);
    }

    @Autowired
    private CabinetMapper cabinetMapper;
    public ResponseResult getAllCabinet() {
        LambdaQueryWrapper<ElectricCabinet> queryWrapper = new LambdaQueryWrapper<>();
        List<ElectricCabinet> cabinet =cabinetMapper.selectList(queryWrapper);
        if(Objects.isNull(cabinet)){
            throw new RuntimeException("数据库中无操作柜");
        }
        return new ResponseResult(HttpStatus.OK.value(),"get cabinets success",cabinet);
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
        return new ResponseResult(HttpStatus.OK.value(),"get worker success",worker);
    }

    public ResponseResult getAllSupervisor(){
        Map<Long,String> supervisor;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getId,User::getUserName).eq(User::getUserType,"监督员");
        supervisor = userMapper.selectList(queryWrapper).stream()
                .collect(Collectors.toMap(User::getId, User::getUserName));
        if(Objects.isNull(supervisor)){
            throw new RuntimeException("数据库中无监督员");
        }
        return new ResponseResult(HttpStatus.OK.value(),"get supervisor success",supervisor);
    }

    /*
     * 管理员写操作票
     */
   // @Autowired
    public ResponseResult saveTicket(FullTicket fullTicket){
        ticketMapper.insertTicket(fullTicket);
        //select key 会把主键放在model的主键中
        List<String> steps = fullTicket.getSteps();
        List<StepSwitch> switches= fullTicket.getStepSwitch();
        for(int i = 1;i<=steps.size();i++){
            OperationStep step = new OperationStep(i, steps.get(i - 1),fullTicket.getTicketId());
            ticketMapper.insertSteps(step);
            //如果stepOrder = 当前stepOrder则继续循环
            for( int j = i; j<=switches.size();j++) {
                StepSwitch stepSwitch = switches.get(j-1);
                if (switches.get(j-1).getStepOrder() == i)
                    ticketMapper.insertSwitch(new StepSwitch(null, step.getId(), stepSwitch.getSwitchId(),
                            stepSwitch.getStepOrder(), stepSwitch.getSwitchStatus()));
                else break;
            }
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
        //todo：这里工人是硬编码的
        LoginUser user = new LoginUser(3L,"王三" , "工人");
        //从数据库查询
//        LambdaQueryWrapper<OperationTicket> queryWrapper = new LambdaQueryWrapper<>();
//
//        queryWrapper.select(OperationTicket::getCreateTime,OperationTicket::getId,OperationTicket::getAdminId,
//                OperationTicket::getWorkerId);
//        List<OperationTicket> result = ticketMapper.selectList(queryWrapper);

        int pageSize = 3;
        int offset = currentPage * 5;
        List<OperationTicket> result= ticketMapper.selectTicketPageByUserID(offset,user,pageSize);
        if(result.size()==0){
            throw new IdNotFoundException("get page fail,do not have enough ticket");
        }//TODO: 修改throws
        return new ResponseResult(HttpStatus.OK.value(),"get page success",result);
    }

    @Autowired
    private StepMapper stepMapper;

    /*
     * 根据操作票id查寻详情，单表查询step单表，点进去则可以查看错误
     * @return: List<OperationSteps> ordered
     */
    public ResponseResult getTicketSteps(Long ticketId){
        //前端返回选择了哪张ticket
//        LambdaQueryWrapper<OperationStep> queryWrapper = new LambdaQueryWrapper<>();
//
//        //select stepOrder,description,step id,complete status from step table
//        queryWrapper.select(OperationStep::getStepOrder,OperationStep::getDescription,
//                OperationStep::getId,OperationStep::getCompleteStatus)
//                .eq(OperationStep::getTicketId,ticketId)
//                .orderByAsc(OperationStep::getStepOrder);

//        Map<Integer, OperationStep> steps = stepMapper.selectList(queryWrapper)
//                .stream().collect(Collectors.toMap(OperationStep::getStepOrder, Function.identity()));
        List<StepResult> steps = stepMapper.selectStepList(ticketId);
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

        queryWrapper.eq(OperationTicket::getCompleteStatus, status);

        List<OperationTicket> result =  ticketMapper.selectList(queryWrapper);

        return new ResponseResult(200,"get ticket success",result);

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
        LoginUser user = new LoginUser(1L,"leo" , "管理员");
        int result = ticketMapper.selectTicketAmount(user);
        if(result==0){
            throw new IdNotFoundException("no ticket in current account");
        }
        return new ResponseResult(HttpStatus.OK.value(),"get amount success",result);
    }

    public ResponseResult updateTicketStatus(String status, Long ticketId) {
        LambdaQueryWrapper<OperationTicket> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OperationTicket::getId,ticketId);
        OperationTicket ticket = new OperationTicket();
        ticket.setCompleteStatus(status);
        int flag = ticketMapper.update(ticket,queryWrapper);
        if(flag==0){
            throw new IdNotFoundException("update fail");
        }
        return new ResponseResult(HttpStatus.OK.value(),"update ticket status success");
    }

    public ResponseResult getTicketById(Long id) {
//        LambdaQueryWrapper<OperationTicket> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(OperationTicket::getId,id);
        OperationTicket ticket = ticketMapper.selectById(id);
        if(Objects.isNull(ticket)){
            throw new IdNotFoundException("操作票不存在");
        }
        return new ResponseResult(HttpStatus.OK.value(),"get ticket success",ticket);
    }

    @Autowired
    MoniterMapper moniterMapper;
    public ResponseResult getCabinetAndMoniter(Long cabinetId) {
        ElectricCabinet cabinet = cabinetMapper.selectById(cabinetId);
        LambdaQueryWrapper<ElectricCabinet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ElectricCabinet::getMonitorId,cabinet.getMonitorId());
        List<ElectricCabinet> cabinets = cabinetMapper.selectList(queryWrapper);
        Moniter moniter = moniterMapper.selectById(cabinet.getMonitorId());

        CabinetResult result = new CabinetResult();
        result.setCabinets(cabinets);
        result.setMoniter(moniter);

        if(Objects.isNull(result)){
            throw new IdNotFoundException("监控和操作柜不存在");
        }
        return new ResponseResult(HttpStatus.OK.value(), "get cabinet and monitor success",result);
    }
}
