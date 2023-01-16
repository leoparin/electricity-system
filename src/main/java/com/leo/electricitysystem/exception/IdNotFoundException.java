package com.leo.electricitysystem.exception;

import lombok.Data;
import lombok.Getter;

/**
 * ClassName:idNotFoundException
 * PackageName:com.leo.electricitysystem.exception
 * Description:
 *
 * @Date 2023/1/11 15:51
 * @Author leo
 **/
public class IdNotFoundException extends RuntimeException{
    String msg;
    public IdNotFoundException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
