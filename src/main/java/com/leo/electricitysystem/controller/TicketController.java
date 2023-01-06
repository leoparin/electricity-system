package com.leo.electricitysystem.controller;

import com.leo.electricitysystem.request.FullTicket;
import com.leo.electricitysystem.response.ResponseResult;
import com.leo.electricitysystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    List<String> getSwitch(){
       return  ticketService.getAllSwitch();
    }

    @GetMapping("/cabinet")
    List<String> getCabinet(){
        return  ticketService.getAllCabinet();
    }

    @GetMapping("/worker")
    List<String> getWorker(){
        return  ticketService.getAllWorker();
    }

    @GetMapping("/supervisor")
    List<String> getSupervisor(){
        return  ticketService.getAllSupervisor();
    }

    @PostMapping("/write")
    public ResponseResult writeTicket(FullTicket fullTicket){
        return ticketService.writeTicket(fullTicket);
    }

    @GetMapping("/getAdminAllTicket/{adminName}")
    public ResponseResult getAdminTicket(@PathVariable String adminName){
        //使用mp根据id查
        return ticketService.getTicketRecord();
    }

}
