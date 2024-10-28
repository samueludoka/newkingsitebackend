package org.example.services;

import org.example.dtos.request.*;
import org.example.dtos.response.*;
import org.example.exception.CustomerNotFoundException;
import org.example.exception.InvalidDetailsException;
import org.example.model.CoinType;
import org.example.model.Customer;
import org.example.model.Wallet;
import org.example.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void createCustomerTest() throws CustomerNotFoundException {
        Wallet wallet = new Wallet();
//        wallet.setId(1L);
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        createCustomerRequest.setId(1L);
        createCustomerRequest.setFirstName("Dominic1");
        createCustomerRequest.setLastName("Rotimi");
        createCustomerRequest.setEmail("dominicrotimi2@gmail1.com");
        createCustomerRequest.setPassword("password16");
        createCustomerRequest.setWallet(wallet);
        CreateCustomerResponse createCustomerResponse = customerService.createCustomer(createCustomerRequest);
        assertThat(createCustomerResponse).isNotNull();
        assertThat(createCustomerResponse.getMessage()).isNotNull();
    }
    
    @Test
    public void loginCustomerWithWrongPasswordTest() throws InvalidDetailsException, CustomerNotFoundException {
        CustomerLoginRequest customerLoginRequest = new CustomerLoginRequest();
        customerLoginRequest.setEmail("dominicrotimi@gmail.com");
        customerLoginRequest.setPassword("password16");
        CustomerLoginResponse customerLoginResponse = customerService.loginCustomer(customerLoginRequest);
        assertThat(customerLoginResponse).isNotNull();
    }

    @Test
    public void loginCustomerWithRightPasswordTest() throws InvalidDetailsException, CustomerNotFoundException {
        CustomerLoginRequest customerLoginRequest = new CustomerLoginRequest();
        customerLoginRequest.setEmail("dominicrotimi@gmail.com");
        customerLoginRequest.setPassword("password164");
        assertThrows(InvalidDetailsException.class, () -> customerService.loginCustomer(customerLoginRequest));
    }
    @Test
    public void getCustomerTest() throws CustomerNotFoundException {
        Customer foundCustomer =  customerService.getUser(1L);
        assertThat(foundCustomer).isNotNull();
    }

}