package com.leo.electricitysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.electricitysystem.domain.CabinetError;
import com.leo.electricitysystem.domain.OperationError;
import com.leo.electricitysystem.domain.UniformError;
import com.leo.electricitysystem.exception.IdNotFoundException;
import com.leo.electricitysystem.mapper.CabinetErrorMapper;
import com.leo.electricitysystem.mapper.OperationErrorMapper;
import com.leo.electricitysystem.mapper.UniformErrorMapper;
import com.leo.electricitysystem.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public ResponseResult getUniformErrorByTicketId(Long ticketId) {
        LambdaQueryWrapper<UniformError> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UniformError::getTicketId,ticketId);
        UniformError result = uniformErrorMapper.selectOne(queryWrapper);
        if(Objects.isNull(result)){
            throw new IdNotFoundException("无服装错误");
        }
        return new ResponseResult(HttpStatus.OK.value(),"get uniform error success",result);
    }


    @Autowired
    private OperationErrorMapper operationErrorMapper;
    public ResponseResult getOperationErrorByStepId(Long stepId) {
        LambdaQueryWrapper<OperationError> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OperationError::getStepId,stepId);
        OperationError result = operationErrorMapper.selectOne(queryWrapper);
        return new ResponseResult(HttpStatus.OK.value(),"get operation error success", result);
    }


    @Autowired
    private CabinetErrorMapper cabinetErrorMapper;
    public ResponseResult getCabinetErrorByTicketId(Long ticketId) {
        LambdaQueryWrapper<CabinetError> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(CabinetError::getTicketId,ticketId);
        CabinetError result = cabinetErrorMapper.selectOne(queryWrapper);
        if(Objects.isNull(result)){
            throw new IdNotFoundException("无操作柜选择错误");
        }
        return new ResponseResult(HttpStatus.OK.value(), "get cabinet error success",result);
    }

    public ResponseResult saveUniformError(UniformError uniformError) {
        int flag = uniformErrorMapper.insert(uniformError);
        if(flag==0){
            throw new IdNotFoundException("write uniform error fail");
        }
        return new ResponseResult(HttpStatus.OK.value(),"write uniform error success");
    }

    public ResponseResult saveCabinetError(CabinetError cabinetError) {
        int flag = cabinetErrorMapper.insert(cabinetError);
        if(flag==0){
            throw new IdNotFoundException("write cabinet error fail");
        }
        return new ResponseResult(HttpStatus.OK.value(),"write cabinet error success");
    }

    public ResponseResult saveOperationError(OperationError operationError) {
        int flag = operationErrorMapper.insert(operationError);
        if(flag==0){
            throw new IdNotFoundException("write operation error fail");
        }
        return new ResponseResult(HttpStatus.OK.value(),"write operation error success");
    }
}
