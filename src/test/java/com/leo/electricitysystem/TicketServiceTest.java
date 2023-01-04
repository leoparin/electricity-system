package com.leo.electricitysystem;

import com.leo.electricitysystem.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName:TicketServiceTest
 * PackageName:com.leo.electricitysystem
 * Description:
 *
 * @Date 2023/1/6 10:14
 * @Author leo
 **/
@SpringBootTest
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Test
    void getSwitchTest() {
     System.out.println(ticketService.getAllSwitch());
    }

    @Test
    void getCabinetTest(){
        System.out.println(ticketService.getAllCabinet());
    }

    @Test
    void getWorkerTest(){
        System.out.println(ticketService.getAllWorker());
    }

    @Test
    void getSupervisorTest(){
        System.out.println(ticketService.getAllSupervisor());
    }


}
