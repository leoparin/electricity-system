package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.domain.User;
import com.leo.electricitysystem.domain.dto.StatisticTransfer;
import com.leo.electricitysystem.domain.request.StatisticRequest;
import com.leo.electricitysystem.domain.response.ResponseResult;
import com.leo.electricitysystem.mapper.TicketMapper;
import com.leo.electricitysystem.mapper.UniformErrorMapper;
import com.leo.electricitysystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        //查用户身份
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        q.select(User::getUserType)
                .eq(User::getId,request.getWorkerId());
        String userType = userMapper.selectOne(q).getUserType();
        //构建传输对象
        StatisticTransfer transfer = new StatisticTransfer(request.getWorkerId(), request.getMonth(), userType);
        //查询所有操作票
        Integer totalTicketAmount = ticketMapper.getTicketAmountByWorkerIdAndTime(transfer);
        //
        Map<String,Integer> map = new HashMap<>();
        map.put("total_tickets",totalTicketAmount);
        //get error tickets
        transfer.setStatus("error");
        Integer errorTicketAmount = ticketMapper.getTicketAmountByWorkerIdAndTime(transfer);
        map.put("error_tickets",errorTicketAmount);

        //get uniform error

    }
//        Map<String,Integer> map = new HashMap<>();
//        //所有操作票数
//        int totalTicketAmount  = ticketMapper.getTicketAmount(request);
//
//        map.put("total_tickets",totalTicketAmount);
//        LambdaQueryWrapper<OperationTicket> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(OperationTicket::getCompleteStatus,"error")
//                .eq(OperationTicket::getWorkerId,request.getWorker_id());
//        Integer errorTicketAmount  = Math.toIntExact(ticketMapper.selectCount(queryWrapper));
//        map.put("error_tickets",errorTicketAmount);
//        //get all Uniform error of specify worker
////        List<UniformError> uniformErrorList = uniformErrorMapper.getUniformErrorByWorkerId(user.getId());
//        Integer helmetError = 0;
//        Integer uniformError = 0;
//        Integer  gloversError = 0;
//
////        for(UniformError i:uniformErrorList){
////            if(Objects.equals(i.getUniform(), "False"))uniformError++;
////            if(Objects.equals(i.getHelmet(), "False"))helmetError++;
////            if(Objects.equals(i.getGloves(), "False"))gloversError++;
////        }
//        map.put("helmet_error",helmetError);
//        map.put("dress_error",uniformError);
//        map.put("gloves_error",gloversError);
//        return new ResponseResult(200,"get Uniform statistics success",map);
//
//    }


}
