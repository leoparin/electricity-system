package com.leo.electricitysystem.request;

import lombok.AllArgsConstructor;
import lombok.Data;

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
    public FullTicket(String taskName, String adminName,
                      String workerName, String supervisorName, List<String> steps) {
        this.taskName = taskName;
        this.adminName = adminName;
        this.workerName = workerName;
        this.supervisorName = supervisorName;
        Steps = steps;
    }

    //存入数据库的时候写时间
    Long ticketId;

    String taskName;

    String adminName;

    String workerName;

    String supervisorName;

    List<String> Steps;
}
