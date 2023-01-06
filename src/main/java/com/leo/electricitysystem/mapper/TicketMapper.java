package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.domain.OperationSteps;
import com.leo.electricitysystem.domain.OperationTickets;
import com.leo.electricitysystem.request.FullTicket;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:TicketMapper.xml
 * PackageName:com.leo.electricitysystem.mapper
 * Description:
 *
 * @Date 2023/1/4 11:31
 * @Author leo
 **/
@Repository
public interface TicketMapper extends BaseMapper<OperationTickets> {

    List<OperationSteps> findAllStepsByAdminId(Long id);

    //TODO: 写操作票，需要同时更新steps表和ticket表
    void insertTicket(FullTicket fullTicket);

    void insertSteps(int stepOrder,String description,Long ticketId);
}
