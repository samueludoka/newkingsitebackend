package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dtos.request.CreateCustomerRequest;
import org.example.dtos.request.CustomerLoginRequest;
import org.example.dtos.response.AdminLoginRequest;
import org.example.dtos.response.CreateCustomerResponse;
import org.example.dtos.response.CustomerLoginResponse;
import org.example.exception.CustomerNotFoundException;
import org.example.exception.InvalidDetailsException;
import org.example.model.Admin;
import org.example.services.AdminService;
import org.example.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class AuthController {
    private final CustomerService customerService;
    private final AdminService adminService;

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/register")
    public ResponseEntity<CreateCustomerResponse> registerCustomer(@RequestBody CreateCustomerRequest request) {
        CreateCustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.ok(response);
    }


    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/login")
    public ResponseEntity<CustomerLoginResponse> login(@RequestBody CustomerLoginRequest customerLoginRequest) throws InvalidDetailsException {
        CustomerLoginResponse loginUser = customerService.loginCustomer(customerLoginRequest);
        return new ResponseEntity<>(loginUser, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/adminLogin")
    public ResponseEntity<String> logInAdmin(@RequestBody AdminLoginRequest adminLoginRequest) throws LoginException {
        String result = adminService.adminLogin(adminLoginRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }


}
