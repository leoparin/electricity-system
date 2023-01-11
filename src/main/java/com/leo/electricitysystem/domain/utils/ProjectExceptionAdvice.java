package com.leo.electricitysystem.domain.utils;

import com.leo.electricitysystem.response.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName:ProjectExceptionAdvice
 * PackageName:com.leo.electricitysystem.domain.utils
 * Description:
 *
 * @Date 2023/1/11 10:29
 * @Author leo
 **/
@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler
    public ResponseResult doException(Exception e){
        return new ResponseResult<>("服务器故障");
    }
}
