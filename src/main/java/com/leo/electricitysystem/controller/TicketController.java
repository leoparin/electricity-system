package com.leo.electricitysystem.controller;

import com.leo.electricitysystem.domain.request.FullTicket;
import com.leo.electricitysystem.domain.response.ResponseResult;
import com.leo.electricitysystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:TicketController
 * PackageName:com.leo.electricitysystem.controller
 * Description:
 *
 * @Date 2023/1/6 09:39
 * @Author leo
 **/
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /*
     * 查看所有开关，操作柜，工人，监督员
     * @return 开关List
     */
    @GetMapping("/switch")
    public ResponseResult getSwitch(){
       return  ticketService.getAllSwitch();
    }

    @GetMapping("/cabinet")
    public ResponseResult getCabinet(){
        return  ticketService.getAllCabinet();
    }

    @GetMapping("/worker")
    public ResponseResult getWorker(){
        return  ticketService.getAllWorker();
    }

    @GetMapping("/supervisor")
    public ResponseResult getSupervisor(){
        return  ticketService.getAllSupervisor();
    }

    /*
     * 写操作票
     */
    @PostMapping
    public ResponseResult saveTicket(@RequestBody FullTicket fullTicket){
        return ticketService.saveTicket(fullTicket);
    }

    /*
     * 通过userid获取ticketPage
     * metadata:pageSize: 5
     * input:currentPage
     * output:
     */
    @GetMapping("/page/{currentPage}")
    public ResponseResult getTicketByUserId(@PathVariable int currentPage){
        //使用mp根据id查
        return ticketService.getTicketListPage(currentPage);
    }

    /**
     *
     * @param status
     * @return R
     */
    @GetMapping("/status/{status}")
    public ResponseResult getTicketByStatus(@PathVariable  String status){
        return ticketService.getTicketByStatus(status);//todo:分页
    }

    /**
     * 通过ticketId获取所有步骤
     * @param id
     * @return
     */
    @GetMapping("/steps/{id}")
    public ResponseResult getStepsById(@PathVariable Long id){
        //使用mp根据id查
        return ticketService.getTicketSteps(id);
    }

    /*
     * 根据当前用户查看共有多少ticket
     */
    @GetMapping("/amount")
    public ResponseResult getTicketAmount(){
        return ticketService.getTicketAmount();
    }

    /**
     * 通过ticketid删除ticket
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        return ticketService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getAllInfo(@PathVariable Long id){
        return ticketService.getTicketById(id);
    }

    /**
     * 通过ticketId更新ticket状态
     * @param status
     * @param ticketId
     * @return
     */
    @PutMapping("/{ticketId}/{status}")
    public ResponseResult putStatus(@PathVariable String status,@PathVariable Long ticketId){
        return ticketService.updateTicketStatus(status,ticketId);
    }

    /**
     * 查看监控对应的cabinet List
     */
    @GetMapping("/cabinetList/{cabinetId}")
    public ResponseResult getCabinetList(@PathVariable Long cabinetId){
        return ticketService.getCabinetAndMoniter(cabinetId);
    }
}
