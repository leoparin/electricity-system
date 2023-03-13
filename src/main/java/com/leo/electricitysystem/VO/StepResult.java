package com.leo.electricitysystem.VO;

import lombok.Data;

/**
 * ClassName:StepResult
 * PackageName:com.leo.electricitysystem.domain.result
 * Description:
 *
 * @Date 2023/1/17 15:58
 * @Author leo
 **/
@Data
public class StepResult {
   // os.id,os.description, os.step_order,ss.switch_status,sl.switch_name,sl.switch_type
    private Long id;
    private int stepOrder;
    private String description;
    private String switchStatus;
    private String switchName;
    private String switchType;

}
