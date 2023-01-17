package com.leo.electricitysystem.unit;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.domain.*;
import com.leo.electricitysystem.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName:MapperTest
 * PackageName:com.leo.electricitysystem
 * Description:
 *
 * @Date 2023/1/4 09:12
 * @Author leo
 **/
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void selectUserById(){
        String username = "Lily";
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user  = userMapper.selectOne(queryWrapper);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名不存在");
        }
        System.out.println(user);
    }

    @Test
    void selectAllWorkers(){
        List<User> workers ;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserType,"2");
        workers = userMapper.selectList(queryWrapper);
        if(Objects.isNull(workers)){
            throw new RuntimeException("暂时没有工人存在");
        }
        System.out.println(workers);
    }

    @Test
    void insertNewUser(){
        User user = new User("Lily","1234","2");
        //create worker Lily
        userMapper.insert(user);
        selectUserById();
    }

    @Autowired
    private SwitchMapper switchMapper;

    @Test
    void selectAllSwitches(){
        List<SwitchOrLight> switches ;
        LambdaQueryWrapper<SwitchOrLight> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SwitchOrLight::getId, SwitchOrLight::getSwitchName);
        switches = switchMapper.selectList(queryWrapper);
        if(Objects.isNull(switches)){
            throw new RuntimeException("数据库中无开关");
        }
        System.out.println(switches);
    }

    @Autowired
    private CabinetMapper cabinetMapper;

    @Test
    void selectAllCabinets() {
        List<ElectricCabinet> cabinets ;
        LambdaQueryWrapper<ElectricCabinet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ElectricCabinet::getId,ElectricCabinet::getCabinetName);
        cabinets = cabinetMapper.selectList(queryWrapper);
        if(Objects.isNull(cabinets)){
            throw new RuntimeException("数据库中无操作柜");
        }
        System.out.println(cabinets);
    }

    @Autowired
    private TicketMapper ticketMapper;

    @Test
    void selectAllTickets() {
        List<OperationTicket> tickets;
        LambdaQueryWrapper<OperationTicket> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(OperationTicket::getId, OperationTicket::getCreateTime);
        tickets = ticketMapper.selectList(queryWrapper);
        if(Objects.isNull(tickets)){
            throw new RuntimeException("数据库中无操作票");
        }
        System.out.println(tickets);
    }

    @Test
    void selectOngoingTicket(){
        List<OperationTicket> tickets;
        LambdaQueryWrapper<OperationTicket> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(OperationTicket::getId, OperationTicket::getCreateTime);
        tickets = ticketMapper.selectList(queryWrapper);
        if(Objects.isNull(tickets)){
            throw new RuntimeException("数据库中无操作票");
        }
        System.out.println(tickets);

    }


    @Autowired
    private StepMapper stepMapper;

    /*
     * 从数据库中取操作票id=1的所有步骤
     */
    @Test
    void selectAllStepsByTicketId(){
        List<OperationStep> steps;
        LambdaQueryWrapper<OperationStep> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(OperationStep::getId, OperationStep::getDescription)
                .eq(OperationStep::getTicketId,"1");
        steps = stepMapper.selectList(queryWrapper);
        if(Objects.isNull(steps)){
            throw new RuntimeException("操作票中无数据");
        }
        System.out.println(steps);
    }

//    @Test
//    void selectAllStepsByAdminId(){
//        List<OperationStep> steps;
//        steps = ticketMapper.findAllStepsByAdminId(1L);
//        System.out.println(steps);
//        //返回类型是Map的List，Map里面全部用String封装
//    }

    /*
     * 查询工人ID=3的所有着装错误
     */
    @Autowired
    private UniformErrorMapper uniformErrorMapper;

    @Test
    void selectUniformErrorById(){
        UniformError error;
        LambdaQueryWrapper<UniformError> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UniformError::getTicketId,"3");
        error = uniformErrorMapper.selectOne(queryWrapper);
        if(Objects.isNull(error)){
            throw new RuntimeException("无着装错误");
        }
        System.out.println(error);
    }

    @Test
    void selectUniformErrorAndNameById(){
        Map<String,String> error;
        error = uniformErrorMapper.selectUniformErrorAndNameByUserId(3L);
//        if(Objects.isNull(error)){
//            throw new RuntimeException("无着装错误");
//        }
        System.out.println(error);
    }

    /*
     * 查询工人step id = 3的所有操作错误描述
     */
    @Autowired
    private OperationErrorMapper operationErrorMapper;

    @Test
    void selectOperationErrorById(){
        OperationError error;
        LambdaQueryWrapper<OperationError> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OperationError::getStepId,"3");
        error = operationErrorMapper.selectOne(queryWrapper);
        if(Objects.isNull(error)){
            throw new RuntimeException("无操作错误");
        }
        System.out.println(error);
    }


}
