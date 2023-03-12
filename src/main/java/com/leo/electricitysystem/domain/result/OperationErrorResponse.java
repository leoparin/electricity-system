package com.leo.electricitysystem.domain.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * ClassName:OperationErrorResponse
 * PackageName:com.leo.electricitysystem.domain.result
 * Description:
 *
 * @Date 2023/3/12 10:32
 * @Author leo
 **/
@Data
@AllArgsConstructor
public class OperationErrorResponse {

    Integer total_amount;

    Integer error_mount;

    List<OperationStatistics> statistics;


}
