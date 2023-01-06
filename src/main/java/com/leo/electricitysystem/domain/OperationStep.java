package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName:OperationSteps
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/4 14:46
 * @Author leo
 **/
@Data
@TableName("operation_steps")
public class OperationStep {

    @TableId
    private Long id;

    /**
     * 具体描述
     */
    private String description;

    /*
     * 所属操作票id
     */
    private Long ticketId;

    /*
     * 步骤序号
     */
    private int stepOrder;

    /*
     * 操作状态：未完成，错误，已完成
     */
    private String completeStatus;
}
