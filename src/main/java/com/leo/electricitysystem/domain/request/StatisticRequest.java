package com.leo.electricitysystem.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName:StatisticRequest
 * PackageName:com.leo.electricitysystem.domain.dto
 * Description:
 *
 * @Date 2023/3/8 18:33
 * @Author leo
 **/
@Data
@AllArgsConstructor
public class StatisticRequest {

    Integer month;

    Long  workerId;

}
