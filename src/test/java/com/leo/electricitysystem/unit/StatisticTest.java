package com.leo.electricitysystem.unit;

import com.leo.electricitysystem.domain.OperationTicket;
import com.leo.electricitysystem.domain.dto.StatisticTransfer;
import com.leo.electricitysystem.domain.request.StatisticRequest;
import com.leo.electricitysystem.mapper.TicketMapper;
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

}
