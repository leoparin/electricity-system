package com.leo.electricitysystem.controller;

import com.leo.electricitysystem.domain.CabinetError;
import com.leo.electricitysystem.domain.OperationError;
import com.leo.electricitysystem.domain.UniformError;
import com.leo.electricitysystem.response.ResponseResult;
import com.leo.electricitysystem.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //get Uniform error by ticket id
    @GetMapping("/uniform/{id}")
    public ResponseResult getUniformError(@PathVariable Long id){
        return errorService.getUniformErrorByTicketId(id);
    }

    //get Operation error by step id
    @GetMapping("/operation/{id}")
    public ResponseResult getOperationError(@PathVariable Long id){
        return errorService.getOperationErrorByStepId(id);
    }

    //get Cabinet error by ticket id
    @GetMapping("/cabinet/{id}")
    public ResponseResult getCabinetError(@PathVariable Long id){
        return errorService.getCabinetErrorByTicketId(id);
    }

    @PostMapping("/uniform")
    public ResponseResult saveUniformError(@RequestBody UniformError uniformError){
        return errorService.saveUniformError(uniformError);
    }

    //关联操作票id在requestBody中
    @PostMapping("/cabinet")
    public ResponseResult saveCabinetError(@RequestBody CabinetError cabinetError){
        return errorService.saveCabinetError(cabinetError);
    }
    @PostMapping("/operation")
    public ResponseResult saveOperationError(@RequestBody OperationError operationError){
        return errorService.saveOperationError(operationError);
    }


}
