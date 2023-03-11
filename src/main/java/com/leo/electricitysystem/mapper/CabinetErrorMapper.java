package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.domain.CabinetError;
import org.springframework.stereotype.Repository;

/**
 * ClassName:CabinetErrorMapper
 * PackageName:com.leo.electricitysystem.mapper
 * Description:
 *
 * @Date 2023/1/8 08:37
 * @Author leo
 **/
@Repository
public interface CabinetErrorMapper extends BaseMapper<CabinetError> {
    public int insertCabinetError(CabinetError cabinetError);
}
