package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName:OperationError
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/4 16:04
 * @Author leo
 **/
@Data
@TableName("operation_error")
@AllArgsConstructor
public class OperationError {

    @TableId
    private Long id;

    /*
     * 错误描述
     */
    private String description;

    /*
     * 错误工人ID
     */
    private Long stepId;




}
