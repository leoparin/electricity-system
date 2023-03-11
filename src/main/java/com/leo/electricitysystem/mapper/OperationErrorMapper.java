package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.domain.OperationError;
import org.springframework.stereotype.Repository;

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
}
