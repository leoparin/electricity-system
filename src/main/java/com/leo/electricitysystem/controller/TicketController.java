package com.leo.electricitysystem.controller;

import com.alibaba.fastjson.JSON;
import com.leo.electricitysystem.request.FullTicket;
import com.leo.electricitysystem.response.ResponseResult;
import com.leo.electricitysystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

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
     * 查看所有开关
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

    @PostMapping
    public ResponseResult saveTicket(@RequestBody FullTicket fullTicket){
        return ticketService.saveTicket(fullTicket);
    }

    /*
     * pageSize: 5
     */
    @GetMapping("/page/{currentPage}")
    public ResponseResult getTicketByUserId(@PathVariable int currentPage){
        //使用mp根据id查
        return ticketService.getTicketListPage(currentPage);
    }

    @GetMapping("/status/{status}")
    public ResponseResult getTicketByStatus(@PathVariable  String status){
        return ticketService.getTicketByStatus(status);//todo:分页
    }

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



    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        return ticketService.delete(id);
    }

//    @GetMapping("/all/{id}")
//    public ResponseResult getAllInfo(@PathVariable Long id){
//        return ticketService.getTicketAllInfo(id);
//    }
}
