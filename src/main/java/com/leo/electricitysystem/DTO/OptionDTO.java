package com.leo.electricitysystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:OptionDTO
 * PackageName:com.leo.electricitysystem.DTO
 * Description:
 *
 * @Date 2023/3/14 08:11
 * @Author leo
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionDTO {
    Long cabinetId;

    String createTime;

    String completeTime;

    String taskName;

    String workContent;

    Long workerId;

    String supervisorName;

}
