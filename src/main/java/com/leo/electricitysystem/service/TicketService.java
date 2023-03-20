package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.DTO.OptionDTO;
import com.leo.electricitysystem.VO.WorkerInfoVO;
import com.leo.electricitysystem.domain.*;
import com.leo.electricitysystem.VO.CabinetVO;
import com.leo.electricitysystem.exception.IdNotFoundException;
import com.leo.electricitysystem.mapper.*;
import com.leo.electricitysystem.DTO.FullTicket;
import com.leo.electricitysystem.VO.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        List<OperationStep> steps = fullTicket.getSteps();
        Long ticketId = fullTicket.getTicketId();
        for(int i = 0;i<steps.size();i++){
            OperationStep step = steps.get(i);
            step.setTicketId(ticketId);
            ticketMapper.insertSteps(step);
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        int pageSize = 3;
        int offset = currentPage * 5;
        List<OperationTicket> result= ticketMapper.selectTicketPageByUserID(offset,loginUser,pageSize);
        if(result.size()==0){
            throw new IdNotFoundException("get page fail,do not have enough ticket");
        }
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
        LambdaQueryWrapper<OperationStep> queryWrapper = new LambdaQueryWrapper<>();
        //select stepOrder,description,step id,complete status from step table
        queryWrapper.eq(OperationStep::getTicketId,ticketId)
                .orderByAsc(OperationStep::getStepOrder);

//        Map<Integer, OperationStep> steps = stepMapper.selectList(queryWrapper)
//                .stream().collect(Collectors.toMap(OperationStep::getStepOrder, Function.identity()));
        List<OperationStep> steps = stepMapper.selectList(queryWrapper);
        //return list of step
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
        User user = new User(1L,"leo" , "管理员");
        LoginUser loginUser = new LoginUser(user);
        int result = ticketMapper.selectTicketAmount(loginUser);
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

        CabinetVO result = new CabinetVO();
        result.setCabinets(cabinets);
        result.setMoniter(moniter);

        if(Objects.isNull(result)){
            throw new IdNotFoundException("监控和操作柜不存在");
        }
        return new ResponseResult(HttpStatus.OK.value(), "get cabinet and monitor success",result);
    }

    public ResponseResult getWorkerInfo(Long id) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getRegion,User::getUserName)
                .eq(User::getId,id);
        User info = userMapper.selectOne(queryWrapper);
        WorkerInfoVO vo = new WorkerInfoVO(info.getUserName(), info.getRegion());
        return new ResponseResult(200,"get WorkerInfo success",vo);
    }

    public ResponseResult optionalSelect(OptionDTO dto) {
        List<OperationTicket> ticketList = ticketMapper.optionalSelect(dto);

        return new ResponseResult(200,"get ticket success",ticketList);
    }

    /**
     * 批量删除
     * @param ticketList
     * @return msg
     */
    public ResponseResult deleteBatchTicket(List<Long> ticketList) {
        int flag = ticketMapper.deleteBatchIds(ticketList);
        if(flag == 0){
            throw new IdNotFoundException("删除失败");
        }
        return new ResponseResult(200,"批量删除成功");
    }
}
