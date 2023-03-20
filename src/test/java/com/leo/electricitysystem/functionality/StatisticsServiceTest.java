package com.leo.electricitysystem.functionality;

import com.leo.electricitysystem.DTO.GetBatchStatisticsDTO;
import com.leo.electricitysystem.DTO.StatisticRequest;
import com.leo.electricitysystem.VO.ResponseResult;
import com.leo.electricitysystem.mapper.CabinetErrorMapper;
import com.leo.electricitysystem.mapper.OperationErrorMapper;
import com.leo.electricitysystem.mapper.TicketMapper;
import com.leo.electricitysystem.mapper.UniformErrorMapper;
import com.leo.electricitysystem.service.StatisticService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * ClassName:StatisticsServiceTest
 * PackageName:com.leo.electricitysystem.functionality
 * Description:
 *
 * @Date 2023/3/16 21:00
 * @Author leo
 **/
@SpringBootTest
class StatisticServiceTest {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UniformErrorMapper uniformErrorMapper;

    @Autowired
    private CabinetErrorMapper cabinetErrorMapper;

    @Autowired
    private OperationErrorMapper operationErrorMapper;

    @Test
    public void testGetUniformErrorNum() {
        StatisticRequest request = new StatisticRequest();
        request.setWorkerId(3L);
        request.setMonth(3);
        ResponseResult response = statisticService.getUniformErrorNum(request);
        assertEquals(200, response.getCode());
        Map<String, Integer> data = (Map<String, Integer>) response.getData();
        assertNotNull(data.get("total_tickets"));
        assertNotNull(data.get("error_tickets"));
        assertNotNull(data.get("helmet_error"));
        assertNotNull(data.get("dress_error"));
        assertNotNull(data.get("gloves_error"));
    }

    @Test
    public void testGetCabinetErrorNum() {
        StatisticRequest request = new StatisticRequest();
        request.setWorkerId(1L);
        request.setMonth(3);
        ResponseResult response = statisticService.getCabinetErrorNum(request);
        assertEquals(200, response.getCode());
        Map<String, Integer> data = (Map<String, Integer>) response.getData();
        assertNotNull(data.get("total_tickets"));
        assertNotNull(data.get("error_tickets"));
    }

    @Test
    public void testGetOperationErrorNum() {
        StatisticRequest request = new StatisticRequest();
        request.setWorkerId(1L);
        request.setMonth(3);
        ResponseResult response = statisticService.getOperationErrorNum(request);
        assertEquals(200, response.getCode());
        Map<String, Integer> data = (Map<String, Integer>) response.getData();
        assertNotNull(data.get("total_tickets"));
        assertNotNull(data.get("error_tickets"));
        assertNotNull(data.get("operation1"));
        assertNotNull(data.get("operation2"));
        assertNotNull(data.get("operation3"));
    }

    @Test
    public void testGetBatchUniformError() {
        GetBatchStatisticsDTO dto = new GetBatchStatisticsDTO();
        dto.setMonth(3);
        List<Long> workerIds = new ArrayList<>();
        workerIds.add(1L);
        workerIds.add(2L);
        dto.setWorkerId(workerIds);
        ResponseResult response = statisticService.getBatchUniformError(dto);
        assertEquals(200, response.getCode());
        Map<String, Integer> data = (Map<String, Integer>) response.getData();
        assertNotNull(data.get("total_tickets"));
        assertNotNull(data.get("error_tickets"));
        assertNotNull(data.get("helmet_error"));
        assertNotNull(data.get("dress_error"));
        assertNotNull(data.get("gloves_error"));
    }
}

