package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.domain.OperationError;
import com.leo.electricitysystem.domain.result.OperationErrorResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName:OperationErrorMapper
 * PackageName:com.leo.electricitysystem.mapper
 * Description:
 *
 * @Date 2023/1/4 16:08
 * @Author leo
 **/
@Repository
public interface OperationErrorMapper extends BaseMapper<OperationError> {
    int insertOperationError(OperationError operationError);

    List<OperationErrorResult> getOperationErrorList(Long workerId, int month);

    Integer getOperationErrorAmount(Long workerId, Integer month);
}
