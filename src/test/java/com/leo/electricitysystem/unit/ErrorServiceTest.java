package com.leo.electricitysystem.unit;

import com.alibaba.fastjson.JSON;
import com.leo.electricitysystem.response.ResponseResult;
import com.leo.electricitysystem.service.ErrorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName:ErrorServiceTest
 * PackageName:com.leo.electricitysystem
 * Description:
 *
 * @Date 2023/1/7 20:34
 * @Author leo
 **/
@SpringBootTest
public class ErrorServiceTest {

    @Autowired
    private ErrorService errorService;

    @Test
    void selectUniformErrorByIdTest(){
        Long id = 3L;
        ResponseResult result = errorService.getUniformErrorByTicketId(id);
        String json = JSON.toJSONString(result);
        System.out.println(json);

    }

    @Test
    void selectOperationErrorByStepIdTest(){
        Long id = 3L;
        ResponseResult result = errorService.getOperationErrorByStepId(id);
        String json = JSON.toJSONString(result);
        System.out.println(json);
    }

    @Test
    void selectOperationByStepIdTest(){
        Long id = 3L;
        ResponseResult result = errorService.getOperationErrorByStepId(id);
        String json = JSON.toJSONString(result);
        System.out.println(json);
    }

    @Test
    void selectCabinetErrorByWorkerIdTest(){
        Long id = 3L;
        ResponseResult result = errorService.getCabinetErrorByTicketId(id);
        String json = JSON.toJSONString(result);
        System.out.println(json);
    }
}
