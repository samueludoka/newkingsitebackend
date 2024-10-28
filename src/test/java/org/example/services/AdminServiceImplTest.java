package org.example.services;

import org.example.dtos.request.AdminRegisterRequest;
import org.example.dtos.response.AdminLoginRequest;
import org.example.dtos.response.AdminRegisterResponse;
import org.example.dtos.response.WithdrawResponse;
import org.example.model.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.LoginException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void adminRegisterTest(){
        AdminRegisterRequest adminRegisterRequest = new AdminRegisterRequest();
        adminRegisterRequest.setId(1L);
        adminRegisterRequest.setEmail("kingsline@gmail.com");
        adminRegisterRequest.setPassword("##1234");
        adminRegisterRequest.setFullName("Kings Oman");
        AdminRegisterResponse adminRegisterResponse = adminService.registerAdmin(adminRegisterRequest);
        assertNotNull(adminRegisterResponse);
    }

    @Test
    public void adminLoginTest() throws LoginException {
        AdminLoginRequest adminLoginRequest = new AdminLoginRequest();
        adminLoginRequest.setEmail("kingsline@gmail.com");
        adminLoginRequest.setPassword("##1234");
        String savedAdmin = adminService.adminLogin(adminLoginRequest);
        assertNotNull(savedAdmin);
    }
    @Test
    public void testAmountToWithdrawResponse(){
        WithdrawResponse withdrawResponse = adminService.approveWithdrawFund(802L);
        assertNotNull(withdrawResponse);
    }


}