package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.domain.OperationTickets;
import org.springframework.stereotype.Repository;

/**
 * ClassName:TicketMapper
 * PackageName:com.leo.electricitysystem.mapper
 * Description:
 *
 * @Date 2023/1/4 11:31
 * @Author leo
 **/
@Repository
public interface TicketMapper extends BaseMapper<OperationTickets> {
}
