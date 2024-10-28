package org.example.services;

import org.example.dtos.request.*;
import org.example.dtos.response.*;
import org.example.exception.*;
import org.example.model.Customer;


import java.util.List;

public interface CustomerService {
    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) throws CustomerNotFoundException;
    CustomerLoginResponse loginCustomer(CustomerLoginRequest customerLoginRequest);
    CustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest)throws CustomerException;

    List<Customer> getAllCustomers()throws CustomerException;

    Customer findCustomerById(Long id)throws CustomerException;

    Customer getUser(Long id);

    String generateVerificationCode();

    void sendVerificationCode(String email, String verificationCode);


    Customer save(Customer customer)throws CustomerException;

    void delete(Long id)throws CustomerNotFoundException;

}
