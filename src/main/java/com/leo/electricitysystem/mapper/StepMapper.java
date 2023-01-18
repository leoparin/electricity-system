package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.domain.OperationStep;
import com.leo.electricitysystem.domain.StepSwitch;
import com.leo.electricitysystem.domain.result.StepResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName:StepMapper
 * PackageName:com.leo.electricitysystem.mapper
 * Description:
 *
 * @Date 2023/1/4 15:01
 * @Author leo
 **/
@Repository
public interface StepMapper extends BaseMapper<OperationStep> {
    List<StepResult> selectStepList(Long ticketId);
}
