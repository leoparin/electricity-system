package com.leo.electricitysystem.domain.dto;

import com.leo.electricitysystem.domain.request.StatisticRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName:StatisticTransfer
 * PackageName:com.leo.electricitysystem.domain.dto
 * Description:
 *
 * @Date 2023/3/11 20:32
 * @Author leo
 **/
@Data
@AllArgsConstructor
public class StatisticTransfer {

    Long workerId;

    Integer month;

    String userType;
    //查询的操作票状态
    String status;

    public StatisticTransfer(Long workerId, Integer month, String userType) {
        this.workerId = workerId;
        this.month = month;
        this.userType = userType;
    }
}
