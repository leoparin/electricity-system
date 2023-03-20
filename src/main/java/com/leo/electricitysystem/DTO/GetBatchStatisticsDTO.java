package com.leo.electricitysystem.DTO;

import lombok.Data;

import java.util.List;

/**
 * ClassName:getBatchStatisticsDTO
 * PackageName:com.leo.electricitysystem.DTO
 * Description:
 *
 * @Date 2023/3/14 21:21
 * @Author leo
 **/
@Data
public class GetBatchStatisticsDTO {

    List<Long> workerId;

    Integer month;
}
