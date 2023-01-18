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

    /**
     * 通过ticketId获取所有制服错误
     * @param id
     * @return
     */
    @GetMapping("/uniform/{id}")
    public ResponseResult getUniformError(@PathVariable Long id){
        return errorService.getUniformErrorByTicketId(id);
    }

    /**
     * 痛过operationStepId获取操作错误
     * @param id
     * @return
     */
    @GetMapping("/operation/{id}")
    public ResponseResult getOperationError(@PathVariable Long id){
        return errorService.getOperationErrorByStepId(id);
    }

    /**
     * 通过ticketId获取所有操作柜错误
     * @param id
     * @return
     */
    @GetMapping("/cabinet/{id}")
    public ResponseResult getCabinetError(@PathVariable Long id){
        return errorService.getCabinetErrorByTicketId(id);
    }

    /**
     * 上传制服错误
     * @param uniformError
     * @return
     */
    @PostMapping("/uniform")
    public ResponseResult saveUniformError(@RequestBody UniformError uniformError){
        return errorService.saveUniformError(uniformError);
    }

    /**
     * 上传操作柜错误
     * @param cabinetError
     * @return
     */
    @PostMapping("/cabinet")
    public ResponseResult saveCabinetError(@RequestBody CabinetError cabinetError){
        return errorService.saveCabinetError(cabinetError);
    }

    /**
     * 上传操作错误
     * @param operationError
     * @return
     */
    @PostMapping("/operation")
    public ResponseResult saveOperationError(@RequestBody OperationError operationError){
        return errorService.saveOperationError(operationError);
    }


}
