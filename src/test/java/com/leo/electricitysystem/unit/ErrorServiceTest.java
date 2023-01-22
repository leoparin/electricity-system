package com.leo.electricitysystem.unit;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.domain.OperationError;
import com.leo.electricitysystem.domain.OperationStep;
import com.leo.electricitysystem.mapper.StepMapper;
import com.leo.electricitysystem.response.ResponseResult;
import com.leo.electricitysystem.service.ErrorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Long id = 2L;
        ResponseResult result = errorService.getUniformErrorByTicketId(id);
        String json = JSON.toJSONString(result);
        System.out.println(json);

    }

    @Test
    void selectOperationErrorByStepIdTest(){
        Long id = 2L;
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
        Long id = 2L;
        ResponseResult result = errorService.getCabinetErrorByTicketId(id);
    }

//    @Test
//    @DisplayName("update ticket unit test")
//    void updateTicketStatus(){
//        //create a uniform error
//        //

    @Autowired
    private StepMapper stepMapper;
    @Test
    @DisplayName("save operation error test")
    void saveOperationError(){
        OperationError error = new OperationError(null,"True",40L,
                "远方接地开关切换至远方","远方接地开关切换至远方","abcdg");

        ResponseResult result = errorService.saveOperationError(error);
        String json = JSON.toJSONString(result);
        System.out.println(json);

        LambdaQueryWrapper<OperationStep> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OperationStep::getId,40L);
        assertEquals(stepMapper.selectOne(queryWrapper).getCompleteStatus(), "错误");
    }

}
