package com.leo.electricitysystem.controller;

import com.leo.electricitysystem.response.ResponseResult;
import com.leo.electricitysystem.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:ErrorController
 * PackageName:com.leo.electricitysystem.controller
 * Description:
 *
 * @Date 2023/1/7 17:18
 * @Author leo
 **/
@RestController
@RequestMapping("/error")
public class ErrorController {

    @Autowired
    private ErrorService errorService;

    //get Uniform error by id
    @GetMapping("/uniformError/{id}")
    public ResponseResult getUniformError(@PathVariable Long id){
        return errorService.getUniformErrorById(id);
    }

    //get Operation error by step id
    @GetMapping("/operationError/{id}")
    public ResponseResult getOperationError(@PathVariable Long id){
        return errorService.getOperationErrorByStepId(id);
    }

    //get Cabinet error by worker id
    @GetMapping("/cabinetError/{id}")
    public ResponseResult getCabinetError(@PathVariable Long id){
        return errorService.getCabinetErrorByWokerId(id);
    }

}
