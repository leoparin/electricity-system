package com.leo.electricitysystem.unit;

import com.alibaba.fastjson.JSON;
import com.leo.electricitysystem.domain.StepSwitch;
import com.leo.electricitysystem.request.FullTicket;
import com.leo.electricitysystem.response.ResponseResult;
import com.leo.electricitysystem.service.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        String json = JSON.toJSONString(ticketService.getAllSwitch());
        System.out.println(json);
    }

    @Test
    void getCabinetTest(){
        String json = JSON.toJSONString(ticketService.getAllCabinet());
        System.out.println(json);
    }

    @Test
    void getWorkerTest(){
        String json = JSON.toJSONString(ticketService.getAllWorker());
        System.out.println(json);
    }

    @Test
    void getSupervisorTest(){
        String json = JSON.toJSONString(ticketService.getAllSupervisor());
        System.out.println(json);
    }

//    @Test
//    void writeTicketTest(){
//        List<String> steps = List.of(
//        "将10kV××线***开关的 “远方/就地” 切换开关切换至就地位置，查确己在就地位置",
//        "断开 10kV××线***开关。",
//        "查10kV××线***开关机械位置指示及开关分合闸指示确在断开位置，电流表指示无电流，带电显示器指示确无带电");
//        FullTicket fullTicket = new FullTicket("10kV××线***开关由运行转检修",
//                "leo","Josh","王武",steps);
//        ticketService.writeTicket(fullTicket);
//
//
//
//    }

    @Test
    void getTicketAmountByUserId(){
        String json = JSON.toJSONString(ticketService.getTicketAmount());
        System.out.println(json);
    }
    @Test
    void getStepsByTicketIdTest(){
        Long ticketId = 27L;
        ResponseResult result = ticketService.getTicketSteps(ticketId);
        String json = JSON.toJSONString(result);
        System.out.println(json);
    }

    @Test
    void getTicketPageByUserId(){
        ResponseResult result = ticketService.getTicketListPage(0);
        String json = JSON.toJSONString(result);
        System.out.println(json);
    }


    @Test
    @DisplayName("save ticket unit test")
    void saveTicket(){
        List<String> steps = List.of(
                "将10kV××线***开关的 “远方/就地” 切换开关切换至就地位置，查确己在就地位置",
                "断开 10kV××线***开关。");
        List<StepSwitch> switches = List.of(
                new StepSwitch(null,null,1L,1,"就地"),
                new StepSwitch(null,null,2L,1,"无带电"),
                new StepSwitch(null,null,3L,2,"balabala")
        );
        FullTicket ticket = new FullTicket(null,"10kV××线***开关由运行转检修",
                "leo","Josh",1L,3L,"王武",
                "2023-01-10 21:06:28","2023-01-10 21:06:28",1L,steps,switches);
        ticketService.saveTicket(ticket);
    }


    @Test
    void getTicketByStatus(){
        String json = JSON.toJSONString(ticketService.getTicketByStatus("未完成"));
        System.out.println(json);
    }
}
