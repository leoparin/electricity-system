package com.leo.electricitysystem.functionality;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.electricitysystem.domain.StepSwitch;
import com.leo.electricitysystem.mapper.TicketMapper;
import com.leo.electricitysystem.domain.request.FullTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ClassName:WriteTicketUnitTest
 * PackageName:com.leo.electricitysystem
 * Description:
 *
 * @Date 2023/1/9 11:25
 * @Author leo
 **/
@SpringBootTest
@AutoConfigureMockMvc
public class AdminTicketTest {

    @Autowired
    private MockMvc mockMvc;

    /*
     * 管理员查看操作票分页的list
     * @API /ticket/write
     * @return responseResult status 200 msg: "write success"  data:  null
     */

    @Test
    @DisplayName("管理员写入操作票")
    void writeTicketHappyFlow() throws Exception {
        //mock a fullTicket
        List<String> steps = List.of(
                "将10kV××线***开关的 “远方/就地” 切换开关切换至就地位置，查确己在就地位置",
                "断开 10kV××线***开关。",
                "查10kV××线***开关机械位置指示及开关分合闸指示确在断开位置，电流表指示无电流，带电显示器指示确无带电");
        List<StepSwitch> switches = List.of(
                new StepSwitch(null,null,1L,1,"就地"),
                new StepSwitch(null,null,2L,1,"无带电"),
                new StepSwitch(null,null,3L,2,"balabala")
                );
        FullTicket ticket = new FullTicket(null,"10kV××线***开关由运行转检修",
                "leo","Josh",1L,3L,"王武",
                "2023-01-10 21:06:28","2023-01-10 21:06:28",1L,steps,switches);

        mockMvc.perform(
                post("/ticket/")
                .content(new ObjectMapper().writeValueAsString(ticket))
                        .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON)
                )
                .andExpectAll(
                                status().isOk(),
                                jsonPath("$.msg").value("write ticket success")
                );
    }

    @Test
    @DisplayName("管理员写入操作票失败")
    void writeTicketFail() throws Exception {
        List<String> steps = List.of(
                "将10kV××线***开关的 “远方/就地” 切换开关切换至就地位置，查确己在就地位置",
                "断开 10kV××线***开关。",
                "查10kV××线***开关机械位置指示及开关分合闸指示确在断开位置，电流表指示无电流，带电显示器指示确无带电");
        List<StepSwitch> switches = List.of(
                new StepSwitch(null,null,1L,1,"就地"),
                new StepSwitch(null,null,2L,1,"无带电"),
                new StepSwitch(null,null,3L,2,"balabala")
                );
        FullTicket ticket = new FullTicket(null,"10kV××线***开关由运行转检修",
                "leo","Josh",1L,3L,"王武",
                "2023-01-10 21:06:28","2023-01-10 21:06:28",2L,steps,switches);
    }


    @Test
    void getWorker() throws Exception {
        mockMvc.perform(get("/ticket/worker"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get worker success")
                );
    }

    /*
     * 根据操作票状态查询操作票
     */
    @Test
    @DisplayName("根据操作票状态查询")
    void selectTicketByStatus() throws Exception {
        mockMvc.perform(get("/ticket/status/未完成"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.data").isArray()
                );
    }

    /*
     * 根据ticketId查询操作票所有信息，包括着装错误操作错误以及操作柜错误
     *
     */
//    @Test
//    @DisplayName("选择ticket详细信息")
//    void getTicketDetailById() throws Exception {
//        mockMvc.perform(get("/ticket/all/1"))
//                .andDo(print())
//                .andExpectAll(
//                        status().isOk(),
//                        jsonPath("$.data").isMap()
//                );
//    }



    @Autowired
    TicketMapper ticketMapper;

    @Test
    @DisplayName("根据id删除操作票")
    void deleteTicketById() throws Exception {
        mockMvc.perform(delete("/ticket/17"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.code").value(200),
                        jsonPath("$.msg").value("delete success")
                );//r对象没把code封装到response code里面，照样是200
           assertNull(ticketMapper.selectById(10),"ticketID=10的记录不为空");
    }
//todo：删除操作票同时删除stepSwitch
    @Test
    @DisplayName("根据id删除操作票")
    void deleteTicketFail() throws Exception {
        mockMvc.perform(delete("/ticket/12"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("ticket id not found, delete fail")
                );
    }


    @Test
    @DisplayName("根据操作票id查询操作票步骤")
    void getTicketStepsById() throws Exception {

        mockMvc.perform(get("/ticket/steps/1"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get steps success")
                );
    }

    @Test
    @DisplayName("根据操作票id查询操作票步骤失败")
    void getTicketStepsFail() throws Exception {

        mockMvc.perform(get("/ticket/steps/12"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get steps fail,ticket does not exist")
                );
    }


    @Test
    @DisplayName("分页查询ticket")
    void getTicketPage() throws Exception {
        mockMvc.perform(get("/ticket/page/0"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get page success")
                );
    }


    @Test
    @DisplayName("分页查询ticket失败")
    void getTicketPageFail() throws Exception {
        mockMvc.perform(get("/ticket/page/1"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get page fail,do not have enough ticket")
                );
    }

    @Test
    @DisplayName("查询总共有多少ticket")
    void getTicketAmount() throws Exception {
        mockMvc.perform(get("/ticket/amount"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get amount success"),
                        jsonPath("$.data").isNumber()
                );
    }

//    @Test
//    @DisplayName("查询总共有多少ticket失败")
//    void getTicketAmountFail() throws Exception {
//        mockMvc.perform(get("/ticket/amount"))
//                .andExpectAll(
//                        status().isOk(),
//                        jsonPath("$.msg").value("no ticket in current account")
//                );
//
//    }


    @Test
    @DisplayName("更新操作票状态")
    void updateStatus() throws Exception {
        mockMvc.perform(put("/ticket/2/进行中"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("update ticket status success")
                );
    }

    @Test
    @DisplayName("根据ticketId查ticket")
    void getTicketById() throws Exception {
        mockMvc.perform(get("/ticket/1"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get ticket success")
                );
    }

}
