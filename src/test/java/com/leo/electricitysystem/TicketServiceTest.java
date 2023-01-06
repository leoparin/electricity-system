package com.leo.electricitysystem;

import com.alibaba.fastjson.JSON;
import com.leo.electricitysystem.request.FullTicket;
import com.leo.electricitysystem.response.ResponseResult;
import com.leo.electricitysystem.service.TicketService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:TicketServiceTest
 * PackageName:com.leo.electricitysystem
 * Description:
 *
 * @Date 2023/1/6 10:14
 * @Author leo
 **/
@SpringBootTest
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void getSwitchTest() {
     System.out.println(ticketService.getAllSwitch());
    }

    @Test
    void getCabinetTest(){
        System.out.println(ticketService.getAllCabinet());
    }

    @Test
    void getWorkerTest(){
        System.out.println(ticketService.getAllWorker());
    }

    @Test
    void getSupervisorTest(){
        System.out.println(ticketService.getAllSupervisor());
    }

    @Test
    void writeTicketTest(){
        List<String> steps = List.of(
        "将10kV××线***开关的 “远方/就地” 切换开关切换至就地位置，查确己在就地位置",
        "断开 10kV××线***开关。",
        "查10kV××线***开关机械位置指示及开关分合闸指示确在断开位置，电流表指示无电流，带电显示器指示确无带电");
        FullTicket fullTicket = new FullTicket("10kV××线***开关由运行转检修",
                "leo","Josh","王武",steps);
        ticketService.writeTicket(fullTicket);

    }

    @Test
    void getTicketInfoByIdTest(){
        Long ticketId = 1L;
        ResponseResult result = ticketService.getTicketInfoById(ticketId);
        String json = JSON.toJSONString(result);
        System.out.println(json);
    }

    @Test
    void getTicketPageByUserId(){
        ResponseResult result = ticketService.getTicketRecord();
        String json = JSON.toJSONString(result);
        System.out.println(json);
    }


}
