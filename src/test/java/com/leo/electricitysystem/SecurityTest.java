package com.leo.electricitysystem;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.electricitysystem.DTO.RegistrationDTO;
import com.leo.electricitysystem.VO.ResponseResult;
import com.leo.electricitysystem.domain.User;
import com.leo.electricitysystem.service.UserDetailServiceImpl;
import com.leo.electricitysystem.util.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ClassName:SecurityTest
 * PackageName:com.leo.electricitysystem
 * Description:
 *
 * @Date 2023/3/17 11:21
 * @Author leo
 **/
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RedisCache redisCache;
    @Test
    public void testAuthenticatingWithValidUser() throws Exception {
        User user = new User();
        user.setUserName("leo");
        user.setPassword("123456");
        mockMvc.perform(post("/user/login")
                        .content(new ObjectMapper().writeValueAsString(user))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void RedisTest(){
        redisCache.setCacheObject("test","fuck");
        String a =  redisCache.getCacheObject("test");
        System.out.println(a);
    }

    @Test
    public void testRegistration() throws Exception {
        RegistrationDTO registrationDTO = new RegistrationDTO("JohnFuck", "password123", "North America", "Customer");
        mockMvc.perform(post("/user/registration")
                        .content(new ObjectMapper().writeValueAsString(registrationDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Autowired
    UserDetailServiceImpl userDetailService;
    @Test
    public void testGetWorkerByRegion() {
        // Arrange
        String region = "North America";
//        List<User> users = Arrays.asList(
//                new User(1L, "John", "Worker", region),
//                new User(2L, "Jane", "Worker", region)
//        );
//        Mockito.when(userMapper.selectList(Mockito.any())).thenReturn(users);
        // Act
        ResponseResult result = userDetailService.getWorkerByRegion(region);

        // Assert
        assertEquals(200, result.getCode());
        assertEquals("get worker list success", result.getMsg());
        String json = JSON.toJSONString(result);
        System.out.println(json);

//        UserIDNameVO user1 = (UserIDNameVO) result.getData().get(0);
//        Assert.assertEquals(1L, user1.getId().longValue());
//        Assert.assertEquals("John", user1.getName());
        
    }

}
