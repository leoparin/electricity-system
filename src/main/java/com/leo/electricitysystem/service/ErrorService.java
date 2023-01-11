package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.domain.CabinetError;
import com.leo.electricitysystem.domain.OperationError;
import com.leo.electricitysystem.domain.UniformError;
import com.leo.electricitysystem.mapper.CabinetErrorMapper;
import com.leo.electricitysystem.mapper.OperationErrorMapper;
import com.leo.electricitysystem.mapper.UniformErrorMapper;
import com.leo.electricitysystem.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName:ErrorService
 * PackageName:com.leo.electricitysystem.service
 * Description:
 *
 * @Date 2023/1/7 17:16
 * @Author leo
 **/

@Service
public class ErrorService {

    /*
     *
     */
    @Autowired
    private UniformErrorMapper uniformErrorMapper;
    public ResponseResult getUniformErrorById(Long workerId) {
        LambdaQueryWrapper<UniformError> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(UniformError::getDescription,UniformError::getId,UniformError::getWorkerId)
                .eq(UniformError::getWorkerId,workerId);
        UniformError result = uniformErrorMapper.selectOne(queryWrapper);
        return new ResponseResult(HttpStatus.OK.value(), result);
    }


    @Autowired
    private OperationErrorMapper operationErrorMapper;
    public ResponseResult getOperationErrorByStepId(Long stepId) {
        LambdaQueryWrapper<OperationError> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(OperationError::getDescription)
                .eq(OperationError::getStepId,stepId);
        List<String> result = operationErrorMapper.selectList(queryWrapper).stream().
                map(OperationError::getDescription).collect(Collectors.toList());
        return new ResponseResult(HttpStatus.OK.value(), result);
    }


    @Autowired
    private CabinetErrorMapper cabinetErrorMapper;
    public ResponseResult getCabinetErrorByWorkerId(Long workerId) {
        LambdaQueryWrapper<CabinetError> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(CabinetError::getDescription,CabinetError::getErrorStatus,CabinetError::getWorkerName,CabinetError::getWorkerId)
                .eq(CabinetError::getWorkerId,workerId);
        CabinetError result = cabinetErrorMapper.selectOne(queryWrapper);
        return new ResponseResult(HttpStatus.OK.value(), result);
    }
}
