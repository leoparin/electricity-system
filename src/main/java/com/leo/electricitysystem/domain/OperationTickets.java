package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * ClassName:OperationTickets
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/4 11:23
 * @Author leo
 **/
@Data
@TableName("operation_ticket")
public class OperationTickets {

    @TableId
    private Long id;

    /*
     * 创建日期
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss”,timezone=“GMT+8")
    private Date createTime;

    /*
     * 管理员id
     */
    private Long adminId;

    /*
     * 工人id
     */
    private Long workerId;

    /*
     * 监督员id
     */
    private Long supervisorId;
}

