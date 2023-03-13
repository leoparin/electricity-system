package com.leo.electricitysystem.controller;

import com.leo.electricitysystem.DTO.StatisticRequest;
import com.leo.electricitysystem.VO.ResponseResult;
import com.leo.electricitysystem.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:StatisticController
 * PackageName:com.leo.electricitysystem.controller
 * Description:
 *
 * @Date 2023/3/8 18:18
 * @Author leo
 **/
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("uniformError")
    ResponseResult getUniformErrorNum(@RequestBody StatisticRequest request){
        return statisticService.getUniformErrorNum(request);
//        return null;
    }

    @GetMapping("cabinetError")
    ResponseResult getCabinetErrorNum(@RequestBody StatisticRequest request){
        return statisticService.getCabinetErrorNum(request);
    }

    @GetMapping("operationError")
    ResponseResult getOperationErrorNum(@RequestBody StatisticRequest request){
        return statisticService.getOperationErrorNum(request);
    }
}
