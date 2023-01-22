package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName:OperationTickets
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/4 11:23
 * @Author leo
 **/
@Data
@TableName("operation_ticket")
public class OperationTicket {

    @TableId
    private Long id;

    /*
     * 创建日期
     */
    private String createTime;

    private String completeTime;
    /*
     * 管理员id
     */
    private Long adminId;

    private String adminName;

    private String workerName;

    /*
     * 工人id
     */
    private Long workerId;

    /*
     * 监督员id
     */
    private String supervisorName;

    private String completeStatus;

    private String taskName;

    private Long cabinetId;

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }
}

