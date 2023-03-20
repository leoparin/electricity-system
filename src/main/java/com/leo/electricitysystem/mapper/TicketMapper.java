package com.leo.electricitysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.electricitysystem.DTO.OptionDTO;
import com.leo.electricitysystem.domain.LoginUser;
import com.leo.electricitysystem.domain.OperationStep;
import com.leo.electricitysystem.domain.OperationTicket;
import com.leo.electricitysystem.domain.StepSwitch;
import com.leo.electricitysystem.DTO.StatisticTransfer;
import com.leo.electricitysystem.DTO.FullTicket;
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

    //List<OperationStep> findAllStepsByAdminId(Long id);

    void insertTicket(FullTicket fullTicket);

    void insertSteps(OperationStep step);

    List<OperationTicket> selectTicketPageByUserID(int offset,LoginUser loginUser,int pageSize);

    int selectTicketAmount(@Param("loginUser") LoginUser loginUser);

    Integer getTicketAmountByWorkerIdAndTime(@Param("statisticTransfer")StatisticTransfer statisticTransfer);

    List<OperationTicket> optionalSelect(@Param("dto") OptionDTO dto);
}
