package com.leo.electricitysystem.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.leo.electricitysystem.domain.OperationStep;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

/**
 * ClassName:ResponseResult
 * PackageName:com.leo.electricitysystem.response
 * Description:
 *
 * @Date 2023/1/7 07:51
 * @Author leo
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult {

        /**
         * 状态码
         */

        private Integer code;

        /**
         * 提示信息，如果有错误时，前端可以获取该字段进行提示
         */

        private String msg;

        /**
         * 查询到的结果数据，
         */

        private Object data;


        public ResponseResult(Integer code, String msg) {

            this.code = code;

            this.msg = msg;

        }


        public ResponseResult(Integer code, Object data) {

            this.code = code;

            this.data = data;

        }


        public ResponseResult(Integer code, String msg, Object data) {

            this.code = code;

            this.msg = msg;

            this.data = data;

        }

    public ResponseResult( String msg) {
            this.msg = msg;
    }

    public Integer getCode() {

            return code;

        }

        public void setCode(Integer code) {

            this.code = code;

        }

        public String getMsg() {

            return msg;

        }

        public void setMsg(String msg) {

            this.msg = msg;

        }

        public Object getData() {

            return data;

        }

        public void setData(Object data) {

            this.data = data;

        }

}
