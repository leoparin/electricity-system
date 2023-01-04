package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName:UniformError
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/4 15:40
 * @Author leo
 **/
@Data
@TableName("uniform_error")
public class UniformError {

    @TableId
    private Long id;

    /*
     * 错误描述
     */
    private String description;

    /*
     * 错误工人ID
     */
    private Long workerId;

}
