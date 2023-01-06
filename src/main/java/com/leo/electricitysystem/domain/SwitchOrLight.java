package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName:SwitchesAndLights
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/4 10:36
 * @Author leo
 **/
@Data
@TableName("switches_light")
public class SwitchOrLight {


    /*
     * 开关id
     */
    @TableId
    private Long id;
    /*
    * 开关名称
     */
    private String switchName;
}
