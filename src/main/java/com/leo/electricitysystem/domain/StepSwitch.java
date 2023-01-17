package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName:StepSwitch
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/17 08:27
 * @Author leo
 **/
@Data
@AllArgsConstructor
@TableName("step_switch")
public class StepSwitch {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long stepId;

    private Long switchId;

    private int stepOrder;

    private String switchStatus;


}
