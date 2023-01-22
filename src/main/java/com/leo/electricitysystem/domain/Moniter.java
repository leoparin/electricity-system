package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName:Moniter
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/21 08:55
 * @Author leo
 **/
@Data
@TableName("sys_monitor")
public class Moniter {

    @TableId
    private Long id;

    /**
     * 后方监控对应的视频地址
     */
    String videoAddress;
}
