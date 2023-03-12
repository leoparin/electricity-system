package com.leo.electricitysystem.domain.result;

import lombok.Data;

/**
 * ClassName:OperationErrorResult
 * PackageName:com.leo.electricitysystem.domain.result
 * Description:
 *
 * @Date 2023/3/12 10:13
 * @Author leo
 **/
@Data
public class OperationErrorResult {
    Long ticketId;

    Long workerId;

    Long stepId;

    String description;

    String errorCreateTime;

}
