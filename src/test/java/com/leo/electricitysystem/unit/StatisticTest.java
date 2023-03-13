package com.leo.electricitysystem.unit;

import com.alibaba.fastjson.JSON;
import com.leo.electricitysystem.DTO.StatisticTransfer;
import com.leo.electricitysystem.DTO.StatisticRequest;
import com.leo.electricitysystem.VO.ResponseResult;
import com.leo.electricitysystem.VO.UniformErrorResult;
import com.leo.electricitysystem.mapper.TicketMapper;
import com.leo.electricitysystem.mapper.UniformErrorMapper;
import com.leo.electricitysystem.service.StatisticService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName:StatisticTest
 * PackageName:com.leo.electricitysystem.unit
 * Description:
 *
 * @Date 2023/3/9 16:25
 * @Author leo
 **/
@SpringBootTest
public class StatisticTest {

    @Autowired
    TicketMapper ticketMapper;
    @Test
    void getUniformErrorAmountTest(){
        StatisticRequest request = new StatisticRequest(3,1L);

        StatisticTransfer transfer = new StatisticTransfer(request.getWorkerId(),request.getMonth(),"工人","error");
        Integer result = ticketMapper.getTicketAmountByWorkerIdAndTime(transfer);
        System.out.println(result);
    }

    @Autowired
    UniformErrorMapper uniformErrorMapper;
    @Test
    void selectUniformErrorTest(){
        Long workerId = 3L;
        Integer month = 3;

        List<UniformErrorResult> list = uniformErrorMapper.getUniformErrorByWorkerIdAndTime(workerId,month);

        System.out.println(list);
    }


    @Autowired
    StatisticService statisticService;
    @Test
    void getUniformErrorNumTest(){
        StatisticRequest request = new StatisticRequest(3,3L);

        ResponseResult result = statisticService.getUniformErrorNum(request);
        String json = JSON.toJSONString(result);
        System.out.println(json);
    }

    @Test
    void getUniformErrorTicketNum(){
        Long workerId = 3L;
        Integer month = 3;

        Integer result = uniformErrorMapper.getUniformErrorTicketNum(workerId,month);
        System.out.println(result);
    }

    @Test
    void getCabinetErrorTicketNum(){
        StatisticRequest request = new StatisticRequest(3,3L);

        ResponseResult result = statisticService.getCabinetErrorNum(request);
        String json = JSON.toJSONString(result);
        System.out.println(json);
    }

    @Test
    void getOperationErrorStatistics(){
        StatisticRequest request = new StatisticRequest(3,1L);

        ResponseResult result = statisticService.getOperationErrorNum(request);
        String json = JSON.toJSONString(result);
        System.out.println(json);
    }
}
