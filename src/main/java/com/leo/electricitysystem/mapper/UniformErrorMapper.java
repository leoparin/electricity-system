package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.domain.UniformError;
import com.leo.electricitysystem.domain.result.UniformErrorResult;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UnifromErrorMapper
 * PackageName:com.leo.electricitysystem.mapper
 * Description:
 *
 * @Date 2023/1/4 15:44
 * @Author leo
 **/
@Repository
public interface UniformErrorMapper extends BaseMapper<UniformError> {
    @MapKey("user_name")
    public Map<String,String> selectUniformErrorAndNameByUserId(Long id);

    public List<UniformErrorResult> getUniformErrorByWorkerIdAndTime(Long id,Integer month);

    public int insertUniformError(UniformError uniformError);

    //查询有服装错误的操作票数目
    public int getUniformErrorTicketNum(Long id,Integer month);

}
