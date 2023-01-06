package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.domain.LoginUser;
import com.leo.electricitysystem.domain.OperationStep;
import com.leo.electricitysystem.domain.OperationTicket;
import com.leo.electricitysystem.request.FullTicket;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * ClassName:TicketMapper.xml
 * PackageName:com.leo.electricitysystem.mapper
 * Description:
 *
 * @Date 2023/1/4 11:31
 * @Author leo
 **/
@Repository
public interface TicketMapper extends BaseMapper<OperationTicket> {

    /*
     * TODO : 根据操作票id查看操作票详情
     */
    //List<OperationStep> findAllStepsByAdminId(Long id);

    void insertTicket(FullTicket fullTicket);

    void insertSteps(int stepOrder,String description,Long ticketId);

    List<OperationTicket> selectTicketPageByUserID(int offset,LoginUser loginUser);
}
