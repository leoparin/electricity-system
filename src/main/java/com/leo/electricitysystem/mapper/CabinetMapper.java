package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.domain.ElectricCabinet;
import org.springframework.stereotype.Repository;

/**
 * ClassName:CabinetMapper
 * PackageName:com.leo.electricitysystem.mapper
 * Description:
 *
 * @Date 2023/1/4 11:03
 * @Author leo
 **/
@Repository
public interface CabinetMapper extends BaseMapper<ElectricCabinet> {
}