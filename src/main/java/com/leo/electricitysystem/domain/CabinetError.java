package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName:CabinetError
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/8 08:18
 * @Author leo
 **/
@Data
@AllArgsConstructor
@TableName("cabinet_error")
public class CabinetError {
//TODO: cabinetError 注释
    @TableId
    private Long id;

    private String description;

    private String errorStatus;

//    private Long workerId;
//
//    private String workerName;

    private Long ticketId;

    private String createTime;
}
