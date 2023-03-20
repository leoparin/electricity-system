package com.leo.electricitysystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class StatisticRequest {

    Integer month;

    Long  workerId;


}
