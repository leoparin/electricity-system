package com.leo.electricitysystem.unit;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.electricitysystem.domain.OperationStep;
import com.leo.electricitysystem.domain.StepSwitch;
import com.leo.electricitysystem.DTO.FullTicket;
import com.leo.electricitysystem.VO.ResponseResult;
import com.leo.electricitysystem.domain.User;
import com.leo.electricitysystem.service.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void writeTicketTest(){
        FullTicket ticket = new FullTicket();
        ticket.setTicketId(1001L);
        ticket.setTaskName("给水泵开启");
        ticket.setAdminName("admin");
        ticket.setAdminId(100L);
        ticket.setWorkerName("张三");
        ticket.setWorkerId(101L);
        ticket.setSupervisorName("李四");
        ticket.setCreateTime("2021-10-01 09:00:00");
        ticket.setCompleteTime("2021-10-01 10:00:00");
        ticket.setCabinetId(201L);

        List<OperationStep> stepList = new ArrayList<>();

        OperationStep step1 = new OperationStep(1, "打开进水阀", 1001L);
        step1.setSwitchId(101L);
        step1.setSwitchStatus("开");
        step1.setCompleteStatus("已完成");
        stepList.add(step1);

        OperationStep step2 = new OperationStep(2, "打开泵开关", 1001L);
        step2.setSwitchId(102L);
        step2.setSwitchStatus("开");
        step2.setCompleteStatus("已完成");
        stepList.add(step2);

        OperationStep step3 = new OperationStep(3, "调节出水量", 1001L);
        step3.setSwitchId(103L);
        step3.setSwitchStatus("开");
        step3.setCompleteStatus("已完成");
        stepList.add(step3);

        ticket.setSteps(stepList);

        ticketService.saveTicket(ticket);



    }

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


//    @Test
//    void getTicketPageByUserId() throws Exception {
//        ResponseResult result = ticketService.getTicketListPage(0);
//        String json = JSON.toJSONString(result);
//        System.out.println(json);
//    }


//    @Test
//    @DisplayName("save ticket unit test")
//    void saveTicket(){
//        List<String> steps = List.of(
//                "将10kV××线***开关的 “远方/就地” 切换开关切换至就地位置，查确己在就地位置",
//                "断开 10kV××线***开关。");
//        List<StepSwitch> switches = List.of(
//                new StepSwitch(null,null,1L,1,"就地"),
//                new StepSwitch(null,null,2L,1,"无带电"),
//                new StepSwitch(null,null,3L,2,"balabala")
//        );
//        FullTicket ticket = new FullTicket(null,"10kV××线***开关由运行转检修",
//                "leo","Josh",1L,3L,"王武",
//                "2023-01-10 21:06:28","2023-01-10 21:06:28",1L,steps,switches);
//        ticketService.saveTicket(ticket);
//    }


    @Test
    void getTicketByStatus(){
        String json = JSON.toJSONString(ticketService.getTicketByStatus("未完成"));
        System.out.println(json);
    }

    @Test
    void updateTicketStatus(){
        String status = "进行中";
        Long ticketId = 2L;

        ResponseResult result = ticketService.updateTicketStatus(status,ticketId);
        String json = JSON.toJSONString(result);
        System.out.println(json);

    }
}
