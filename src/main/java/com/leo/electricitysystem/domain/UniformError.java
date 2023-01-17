package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UniformError {

    @TableId
    private Long id;
    /*
     * 错误描述
     */
//    private String description;

    private Long ticketId;

    private String result;


    private String helmet;

    private String  gloves;

    private String  uniform;

    private String img;
}
