package com.leo.electricitysystem.exception;

import com.leo.electricitysystem.VO.ResponseResult;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(value = {
            IdNotFoundException.class
    })
    public ResponseResult doException(Exception e){
        e.printStackTrace();
        return new ResponseResult(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
}
