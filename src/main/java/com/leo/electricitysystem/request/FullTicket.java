package com.leo.electricitysystem.request;

import com.leo.electricitysystem.domain.OperationStep;
import com.leo.electricitysystem.domain.StepSwitch;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ClassName:FullTicket
 * PackageName:com.leo.electricitysystem.request
 * Description:
 *
 * @Date 2023/1/6 18:55
 * @Author leo
 **/
@Data
@AllArgsConstructor
public class FullTicket {

    Long ticketId;

    String taskName;

    String adminName;

    String workerName;

    Long workerId;

    Long adminId;

    String supervisorName;

    //TODO:记录自动过期
    String createTime;

    String completeTime;

    List<String> Steps;

    //写一个switchstep表对象，List map
    List<StepSwitch> stepSwitch;


}
