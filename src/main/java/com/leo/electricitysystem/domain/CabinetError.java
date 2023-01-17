package com.leo.electricitysystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName:CabinetError
 * PackageName:com.leo.electricitysystem.domain
 * Description:
 *
 * @Date 2023/1/8 08:18
 * @Author leo
 **/
@Data
@AllArgsConstructor
@TableName("cabinet_error")
public class CabinetError {
    @TableId
    private Long id;

    private String result;

    private Long ticketId;

    private String needCabinet;

    private String actualCabinet;

    private String img;
}
//todo:数据库写入失败处理