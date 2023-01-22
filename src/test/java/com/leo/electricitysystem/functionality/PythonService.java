package com.leo.electricitysystem.functionality;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ClassName:PythonService
 * PackageName:com.leo.electricitysystem.functionality
 * Description:
 *
 * @Date 2023/1/21 08:31
 * @Author leo
 **/
@SpringBootTest
@AutoConfigureMockMvc
public class PythonService {
    @Autowired
    MockMvc mockMvc;
    /**
     * 算法根据操作柜id查看对应监控的全部操作柜
     */
    @Test
    @DisplayName("查看监控对应全部操作柜")
    void getCabinetListByMonitor() throws Exception {
        mockMvc.perform(get("/ticket/cabinetList/1"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.msg").value("get cabinet and monitor success")
                );
    }
}
