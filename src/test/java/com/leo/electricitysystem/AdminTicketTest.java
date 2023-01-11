package com.leo.electricitysystem;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.electricitysystem.mapper.TicketMapper;
import com.leo.electricitysystem.request.FullTicket;
import com.leo.electricitysystem.service.TicketService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
     * @return responseResult status 200 msg:"write success"  data:  null
     */

    @Test
    @DisplayName("管理员写入操作票")
    void writeTicketHappyFlow() throws Exception {
        //mock a fullTicket
        List<String> steps = List.of(
                "将10kV××线***开关的 “远方/就地” 切换开关切换至就地位置，查确己在就地位置",
                "断开 10kV××线***开关。",
                "查10kV××线***开关机械位置指示及开关分合闸指示确在断开位置，电流表指示无电流，带电显示器指示确无带电");
        FullTicket ticket = new FullTicket(null,"10kV××线***开关由运行转检修",
                "leo","Josh",1L,3L,"王武",
                "2023-01-10 21:06:28",steps);

        mockMvc.perform(
                post("/ticket/write")
                .content(new ObjectMapper().writeValueAsString(ticket))
                        .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON)
                )
                .andExpectAll(
                                status().isOk(),
                                jsonPath("$.msg").value("write ticket success")
                        );
    }

    /*
     * delete a ticket from db
     */

    @Test
    void getWorker() throws Exception {
        mockMvc.perform(get("/ticket/worker"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("hello")
                );
    }

    /*
     * 根据操作票状态查询操作票
     */
    @Test
    @DisplayName("根据操作票状态查询")
    void selectTicketByStatus() throws Exception {
        mockMvc.perform(get("/ticket/conditionSelect/未完成"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.data").isArray()
                );
    }

    @Autowired
    TicketMapper ticketMapper;

    @Test
    @DisplayName("根据id删除操作票")
    void deleteTicketById() throws Exception {
        mockMvc.perform(delete("/ticket/10"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("delete success")
                );


           assertNull(ticketMapper.selectById(10),"ticketID=10的记录不为空");
    }



}
