package com.leo.electricitysystem.domain.result;

import com.leo.electricitysystem.domain.ElectricCabinet;
import com.leo.electricitysystem.domain.Moniter;
import lombok.Data;

import java.util.List;

/**
 * ClassName:CabinetList
 * PackageName:com.leo.electricitysystem.domain.result
 * Description:
 *
 * @Date 2023/1/21 09:00
 * @Author leo
 **/
@Data
public class CabinetResult {

    Moniter moniter;

    List<ElectricCabinet> cabinets;
}
