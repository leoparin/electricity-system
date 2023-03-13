package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.domain.User;
import com.leo.electricitysystem.DTO.StatisticTransfer;
import com.leo.electricitysystem.DTO.StatisticRequest;
import com.leo.electricitysystem.VO.ResponseResult;
import com.leo.electricitysystem.VO.OperationErrorResponse;
import com.leo.electricitysystem.VO.OperationErrorResult;
import com.leo.electricitysystem.VO.OperationStatistics;
import com.leo.electricitysystem.VO.UniformErrorResult;
import com.leo.electricitysystem.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ClassName:StatisticService
 * PackageName:com.leo.electricitysystem.service
 * Description:
 *
 * @Date 2023/3/8 18:40
 * @Author leo
 **/
@Service
public class StatisticService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    UniformErrorMapper uniformErrorMapper;
    @Autowired
    UserMapper userMapper;
    //单个工人查询自己的统计结果
    public ResponseResult getUniformErrorNum(StatisticRequest request){
        Integer totalTicketAmount = ticketAmountHelper(request);

        Map<String,Integer> map = new HashMap<>();
        map.put("total_tickets",totalTicketAmount);
        //get error ticket
        Integer errorTicketAmount = uniformErrorMapper.getUniformErrorTicketNum(request.getWorkerId(),request.getMonth());
        map.put("error_tickets",errorTicketAmount);

        //get uniform error list
        List<UniformErrorResult> uniformErrorResults = uniformErrorMapper.
                getUniformErrorByWorkerIdAndTime(request.getWorkerId(), request.getMonth());

        Integer helmetError = 0;
        Integer uniformError = 0;
        Integer  glovesError = 0;

        for(UniformErrorResult i:uniformErrorResults){
            if(Objects.equals(i.getUniform(), "False"))uniformError++;
            if(Objects.equals(i.getHelmet(), "False"))helmetError++;
            if(Objects.equals(i.getGloves(), "False"))glovesError++;
        }
        map.put("helmet_error",helmetError);
        map.put("dress_error",uniformError);
        map.put("gloves_error",glovesError);

        return new ResponseResult(200,"get Uniform statistics success",map);
    }

    String getUserTypeHelper(Long id){
        //查用户身份
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        q.select(User::getUserType)
                .eq(User::getId,id);
        return userMapper.selectOne(q).getUserType();
    }


    @Autowired
    CabinetErrorMapper cabinetErrorMapper;
    public ResponseResult getCabinetErrorNum(StatisticRequest request) {
//        //查询所有操作票
//        String userType = getUserTypeHelper(request.getWorkerId());
//        StatisticTransfer transfer = new StatisticTransfer(request.getWorkerId(), request.getMonth(), userType);
        Integer totalTicketAmount = ticketAmountHelper(request);

        //查询所有有操作柜错误的操作票数目
        Integer amount = cabinetErrorMapper.getCabinetErrorTicketAmount(request.getMonth(),request.getWorkerId());
        Map<String,Integer> map = new HashMap<>();
        map.put("total_tickets",totalTicketAmount);
        map.put("error_tickets",amount);

        return new ResponseResult(200,"get cabinet error amount success",map);
    }

    Integer ticketAmountHelper(StatisticRequest request){
        //查询所有操作票
        String userType = getUserTypeHelper(request.getWorkerId());
        StatisticTransfer transfer = new StatisticTransfer(request.getWorkerId(), request.getMonth(), userType);
        return  ticketMapper.getTicketAmountByWorkerIdAndTime(transfer);

    }

    @Autowired
    OperationErrorMapper operationErrorMapper;
    public ResponseResult getOperationErrorNum(StatisticRequest request) {
        Integer ticketAmount = ticketAmountHelper(request);

        // ticket num which has operation error
        Integer errorTicketAmount = operationErrorMapper.getOperationErrorAmount(request.getWorkerId(), request.getMonth());
        Map<String,Integer> map = new HashMap<>();
        List<OperationErrorResult> list = operationErrorMapper.getOperationErrorList(request.getWorkerId(), request.getMonth());

        Integer operation1 = 0;
        Integer operation2 = 0;
        Integer operation3 = 0;
        for(OperationErrorResult i : list){
            if(i.getStepId() == 1L)operation1++;
            if(i.getStepId() == 2L)operation2++;
            if(i.getStepId() == 3L)operation3++;
        }

        List<OperationStatistics> resultList = new ArrayList<>();

        resultList.add(0,new OperationStatistics("操作一",operation1));
        resultList.add(1,new OperationStatistics("操作二",operation2));
        resultList.add(2,new OperationStatistics("操作三",operation3));

       return new ResponseResult(200,"get operation statistics success"
               ,new OperationErrorResponse(ticketAmount,errorTicketAmount,resultList));

    }
}
