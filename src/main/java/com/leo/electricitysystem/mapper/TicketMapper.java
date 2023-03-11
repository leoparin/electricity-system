package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.domain.LoginUser;
import com.leo.electricitysystem.domain.OperationStep;
import com.leo.electricitysystem.domain.OperationTicket;
import com.leo.electricitysystem.domain.StepSwitch;
import com.leo.electricitysystem.domain.dto.StatisticTransfer;
import com.leo.electricitysystem.domain.request.FullTicket;
import com.leo.electricitysystem.domain.request.StatisticRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

    void insertSteps(OperationStep step);

    void insertSwitch(StepSwitch stepSwitch);

    //todo: 修改分页查询的当前页码
    List<OperationTicket> selectTicketPageByUserID(int offset,LoginUser loginUser,int pageSize);

    int selectTicketAmount(@Param("loginUser") LoginUser loginUser);

    Integer getTicketAmountByWorkerIdAndTime(@Param("statisticTransfer")StatisticTransfer statisticTransfer);
}
