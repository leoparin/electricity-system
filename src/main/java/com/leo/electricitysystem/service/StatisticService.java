package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.DTO.GetBatchStatisticsDTO;
import com.leo.electricitysystem.domain.User;
import com.leo.electricitysystem.DTO.StatisticTransfer;
import com.leo.electricitysystem.DTO.StatisticRequest;
import com.leo.electricitysystem.VO.ResponseResult;
import com.leo.electricitysystem.VO.OperationErrorResult;
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
    /**
     *查询某个工人某段时间内的具体着装错误总数
     * @param request
     * @return
     */
    public ResponseResult getUniformErrorNum(StatisticRequest request) {
        Integer totalTicketAmount = ticketAmountHelper(request.getWorkerId(), request.getMonth());

        Map<String, Integer> map = new HashMap<>();
        map.put("total_tickets", totalTicketAmount);
        //get error ticket
        Integer errorTicketAmount = uniformErrorMapper.getUniformErrorTicketNum(request.getWorkerId(), request.getMonth());
        map.put("error_tickets", errorTicketAmount);

        //get uniform error list
        List<UniformErrorResult> uniformErrorResults = uniformErrorMapper.
                getUniformErrorByWorkerIdAndTime(request.getWorkerId(), request.getMonth());

        Integer helmetError = 0;
        Integer uniformError = 0;
        Integer glovesError = 0;

        for (UniformErrorResult i : uniformErrorResults) {
            if (Objects.equals(i.getUniform(), "False")) uniformError++;
            if (Objects.equals(i.getHelmet(), "False")) helmetError++;
            if (Objects.equals(i.getGloves(), "False")) glovesError++;
        }
        map.put("helmet_error", helmetError);
        map.put("dress_error", uniformError);
        map.put("gloves_error", glovesError);

        return new ResponseResult(200, "get Uniform statistics success", map);
    }

    String getUserTypeHelper(Long id) {
        //查用户身份
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        q.select(User::getUserType)
                .eq(User::getId, id);
        return userMapper.selectOne(q).getUserType();
    }


    @Autowired
    CabinetErrorMapper cabinetErrorMapper;

    public ResponseResult getCabinetErrorNum(StatisticRequest request) {
//        //查询所有操作票
//        String userType = getUserTypeHelper(request.getWorkerId());
//        StatisticTransfer transfer = new StatisticTransfer(request.getWorkerId(), request.getMonth(), userType);
        Integer totalTicketAmount = ticketAmountHelper(request.getWorkerId(), request.getMonth());

        //查询所有有操作柜错误的操作票数目
        Integer amount = cabinetErrorMapper.getCabinetErrorTicketAmount(request.getMonth(), request.getWorkerId());
        Map<String, Integer> map = new HashMap<>();
        map.put("total_tickets", totalTicketAmount);
        map.put("error_tickets", amount);

        return new ResponseResult(200, "get cabinet error amount success", map);
    }

    /*
     * get ticket amount according to worker id
     */
    Integer ticketAmountHelper(Long id , Integer month) {
        //查询所有操作票
        String userType = getUserTypeHelper(id);
        StatisticTransfer transfer = new StatisticTransfer(id, month, userType);
        return ticketMapper.getTicketAmountByWorkerIdAndTime(transfer);

    }

    @Autowired
    OperationErrorMapper operationErrorMapper;

    public ResponseResult getOperationErrorNum(StatisticRequest request) {
        Integer ticketAmount = ticketAmountHelper(request.getWorkerId(),request.getMonth());

        // ticket num which has operation error
        Integer errorTicketAmount = operationErrorMapper.
                getOperationErrorAmount(request.getWorkerId(), request.getMonth());
        Map<String, Integer> map = new HashMap<>();
        List<OperationErrorResult> list = operationErrorMapper.
                getOperationErrorList(request.getWorkerId(), request.getMonth());

        map.put("total_tickets", ticketAmount);
        map.put("error_tickets", errorTicketAmount);
        Integer operation1 = 0;
        Integer operation2 = 0;
        Integer operation3 = 0;
        for (OperationErrorResult i : list) {
            if (i.getStepId() == 1L) operation1++;
            if (i.getStepId() == 2L) operation2++;
            if (i.getStepId() == 3L) operation3++;
        }
        map.put("operation1", operation1);
        map.put("operation2", operation2);
        map.put("operation3", operation3);

        return new ResponseResult(200, "get operation statistics success",map);

    }

    public ResponseResult getBatchUniformError(GetBatchStatisticsDTO dto) {
        Integer n = dto.getWorkerId().size();
        List<Long> idList = dto.getWorkerId();
        Integer totalAmount = 0 ;
        Integer errorAmount = 0 ;
        Integer month = dto.getMonth();
        Integer helmetError = 0;
        Integer uniformError = 0;
        Integer glovesError = 0;
        //get ticket amount
        Map<String,Integer> map = new HashMap<>();
        for (long id : idList){
            totalAmount+=ticketAmountHelper(id, month);
            //map key:workerId value: uniformError list
            errorAmount+=uniformErrorMapper.getUniformErrorTicketNum(id,month);
            List<UniformErrorResult> uniformErrorResults = uniformErrorMapper.
                    getUniformErrorByWorkerIdAndTime(id, month);

            for (UniformErrorResult i : uniformErrorResults) {
                if (Objects.equals(i.getUniform(), "False")) uniformError++;
                if (Objects.equals(i.getHelmet(), "False")) helmetError++;
                if (Objects.equals(i.getGloves(), "False")) glovesError++;
            }
        }
        map.put("total_tickets", totalAmount);
        map.put("error_tickets", errorAmount);
        map.put("helmet_error", helmetError);
        map.put("dress_error", uniformError);
        map.put("gloves_error", glovesError);
        //get error ticket amount
        return new ResponseResult(200,"get batch worker uniform error statistics result success",map);
    }

    public ResponseResult getBatchCabinetError(GetBatchStatisticsDTO dto){
        Integer n = dto.getWorkerId().size();
        List<Long> idList = dto.getWorkerId();
        Integer totalAmount = 0 ;
        Integer errorAmount = 0 ;
        Integer month = dto.getMonth();
        Map<String,Integer> map = new HashMap<>();
        for (long id : idList) {
            totalAmount += ticketAmountHelper(id, month);
            //map key:workerId value: uniformError list
            errorAmount += cabinetErrorMapper.getCabinetErrorTicketAmount( month,id);
        }
        map.put("total_tickets", totalAmount);
        map.put("error_tickets", errorAmount);
        return new ResponseResult(200,"get batch worker cabinet error statistics result success",map);
    }

    public ResponseResult getBatchOperationError(GetBatchStatisticsDTO dto){
        Integer n = dto.getWorkerId().size();
        List<Long> idList = dto.getWorkerId();
        Integer totalAmount = 0 ;
        Integer errorAmount = 0 ;
        Integer month = dto.getMonth();
        Map<String,Integer> map = new HashMap<>();
        Integer operation1 = 0;
        Integer operation2 = 0;
        Integer operation3 = 0;
        //遍历整个工人list
        for (long id : idList) {
            totalAmount += ticketAmountHelper(id, month);
            //map key:workerId value: uniformError list
            errorAmount += operationErrorMapper.getOperationErrorAmount(id, month);

            List<OperationErrorResult> list = operationErrorMapper.
                    getOperationErrorList(id,month);

            for (OperationErrorResult i : list) {
                //步骤order
                if (i.getStepOrder() == 1) operation1++;
                if (i.getStepOrder() == 2) operation2++;
                if (i.getStepOrder() == 3) operation3++;
            }
        }
        map.put("total_tickets", totalAmount);
        map.put("error_tickets", errorAmount);
        map.put("operation1", operation1);
        map.put("operation2", operation2);
        map.put("operation3", operation3);

        return new ResponseResult(200, "get operation statistics success",map);
    }

}
