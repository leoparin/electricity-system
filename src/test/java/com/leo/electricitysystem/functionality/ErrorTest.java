package com.leo.electricitysystem.functionality;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.electricitysystem.domain.CabinetError;
import com.leo.electricitysystem.domain.OperationError;
import com.leo.electricitysystem.domain.OperationTicket;
import com.leo.electricitysystem.domain.UniformError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ClassName:ErrorTest
 * PackageName:com.leo.electricitysystem.functionality
 * Description:
 *
 * @Date 2023/1/11 12:01
 * @Author leo
 **/
@SpringBootTest
@AutoConfigureMockMvc
class ErrorTest {

    @Autowired
    MockMvc mockMvc;

    /*
     * get cabinet error and uniform error by ticketId
     */
    @Test
    @DisplayName("根据ticketId查询服装错误")
    void getUniformErrorByTicketId() throws Exception {
        mockMvc.perform(get("/error/uniform/1"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get uniform error success")
                );
    }

    @Test
    @DisplayName("根据ticketId查询操作柜错误")
    void getCabinetErrorByTicketId() throws Exception {
        mockMvc.perform(get("/error/cabinet/1"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get cabinet error success")
                );
    }

    @Test
    @DisplayName("根据stepId查询操作错误")
    void getOperationErrorByTicketId() throws Exception {
        mockMvc.perform(get("/error/operation/2"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get operation error success")
                );
    }

    @Test
    @DisplayName("写入穿着错误")
    void saveUniformError() throws Exception {
        UniformError error = new UniformError(null,3L,	"False",
                "True","True","True","abcdg");

        mockMvc.perform(
                post("/error/uniform")
                .content(new ObjectMapper().writeValueAsString(error))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("write uniform error success")
                );
    }

    @Test
    @DisplayName("写入操作柜错误")
    //写入操作柜错误，
    void saveCabinetError() throws Exception {
        CabinetError error = new CabinetError(null,"True",1L,
                "10kV站用变6018柜","10kV安居2线611开关柜",
                    "abcdefg");

        mockMvc.perform(
                        post("/error/cabinet")
                                .content(new ObjectMapper().writeValueAsString(error))
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("write cabinet error success")
                );
    }

    //写入某张操作票的某个步骤，需要提供stepOrder，和ticketID
    @Test
    @DisplayName("写入操作错误")
    void saveOperationError() throws Exception {
        OperationError error = new OperationError(null,"True",1L,
                "远方接地开关切换至远方","远方接地开关切换至远方","abcdg");

        mockMvc.perform(
                        post("/error/operation")
                                .content(new ObjectMapper().writeValueAsString(error))
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("write operation error success")
                );
        //todo:更新操作票状态
    }




}
