package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName:ElectricCabinet
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/4 11:01
 * @Author leo
 **/
@Data
@TableName("electric_cabinet")
public class ElectricCabinet {

    /*
     * 开关id
     */
    @TableId
    private Long id;
    /*
     * 开关名称
     */
    private String cabinetName;

    private String platenOrder;

    private String switchOrder;

    private String lightOrder;

    private Long monitorId;
}
