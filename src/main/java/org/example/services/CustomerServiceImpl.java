package org.example.services;

import org.example.dtos.request.*;
import org.example.dtos.response.*;
import org.example.exception.*;
import org.example.model.*;
import org.example.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) throws CustomerNotFoundException {
        if (userExist(createCustomerRequest.getEmail()))
            throw new CustomerNotFoundException("customer with email " + createCustomerRequest.getEmail() + " already exist");

        CreateCustomerResponse createCustomerResponse;
        Customer customer = new Customer();
        customer.setId(createCustomerRequest.getId());
        customer.setFirstName(createCustomerRequest.getFirstName());
        customer.setLastName(createCustomerRequest.getLastName());
        customer.setUsername(createCustomerRequest.getUsername());
        customer.setEmail(createCustomerRequest.getEmail());
        customer.setPassword(createCustomerRequest.getPassword());
        customer.setWallet(createCustomerRequest.getWallet());

        Country foundCountry = Country.fromName(createCustomerRequest.getCountry());
        customer.setCountry(foundCountry);
        customer.setPhoneNumber(createCustomerRequest.getPhoneNumber());


        customerRepository.save(customer);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nextcentss@gmail.com");
        message.setTo(createCustomerRequest.getEmail());
        message.setSubject("Registration Message");
        message.setText(("Welcome to Nextcents – Registration Successful!\n" +
                "\n" +
                "Hi " + createCustomerRequest.getFirstName() + ",\n" +
                "Thank you for joining Nextcents! We're excited to have you with us.\n" +
                "To get started, simply log in to your account and explore our features:\n" +
                "http://localhost:8086/api/v1/customer/login\n" +  // Ensure "http://" is correct here
                "\n" +
                "If you have any questions or need help, our support team is ready to assist you at support.Nextcent@gmail.com.\n" +
                "\n" +
                "Best regards,\n" +
                "The Nextcent Team\n" +
                "\n" +
                "Nextcents | 55 East 10th Street, New York, NY 10003, United States\n" +
                "| +(646)555-5779)"));
        mailSender.send(message);
//        Optional<Wallet> foundWallet = walletRepository.findById(createCustomerRequest.getWallet().getId());
        createCustomerResponse = new CreateCustomerResponse();

//        createCustomerResponse.setWalletId(foundWallet.get().getId());
        createCustomerResponse.setMessage("Customer created successfully");

        return createCustomerResponse;

    }
    private boolean userExist(String email) {
        Customer foundCustomer = customerRepository.findByEmail(email);
        return foundCustomer != null;
    }

    @Override
    public String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);  // Generates a random number between 100000 and 999999
        return String.valueOf(code);
    }

    @Override
    public void sendVerificationCode(String email, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Password Reset Verification Code");
        message.setText("Your password reset code is: " + verificationCode);
        mailSender.send(message);
    }

    @Override
    public CustomerLoginResponse loginCustomer(CustomerLoginRequest customerLoginRequest) {
        Customer foundCustomer = customerRepository.findByEmail(customerLoginRequest.getEmail());
        if (!userExist(customerLoginRequest.getEmail()))
            throw new InvalidDetailsException("Invalid username or password");

        Wallet wallet = foundCustomer.getWallet();
        if (wallet == null) {
            wallet = new Wallet();
            foundCustomer.setWallet(wallet);
            walletRepository.save(wallet);
        }
        if (!foundCustomer.getPassword().equals(customerLoginRequest.getPassword()))
            throw new InvalidDetailsException("Invalid username or password");
        Customer savedUser = customerRepository.save(foundCustomer);
        CustomerLoginResponse customerLoginResponse = new CustomerLoginResponse();
        customerLoginResponse.setId(savedUser.getId());
        customerLoginResponse.setWalletId(savedUser.getWallet().getId());
        foundCustomer.setActive(true);
        customerRepository.save(foundCustomer);
        return customerLoginResponse;
    }


    @Override
    public CustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest) throws CustomerException {
        Customer foundCustomer = customerRepository.findById(updateCustomerRequest.getId()).orElseThrow(
                () -> new CustomerNotFoundException("customer with id " + updateCustomerRequest.getId() + " not found"));
        foundCustomer.setId(updateCustomerRequest.getId());
        foundCustomer.setFirstName(updateCustomerRequest.getFirstName());
        foundCustomer.setLastName(updateCustomerRequest.getLastName());
        foundCustomer.setEmail(updateCustomerRequest.getEmail());
        foundCustomer.setPassword(updateCustomerRequest.getPassword());
        foundCustomer.setActive(updateCustomerRequest.isActive());
        customerRepository.save(foundCustomer);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setMessage("customer updated successfully");

        return customerResponse;
    }

    @Override
    public List<Customer> getAllCustomers() throws CustomerException {
        List<Customer> foundCustomers = customerRepository.findAll();
        if (foundCustomers.size() == 0) {
            throw new CustomerException("No customers found");
        }
        return foundCustomers;
    }

    @Override
    public Customer findCustomerById(Long id) throws CustomerException {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
         if (!foundCustomer.isPresent()) throw new CustomerException("Customer not found");
         return foundCustomer.get();
    }

    @Override
    public Customer getUser(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer with id " + id + " not found"));
    }


    @Override
    public Customer save(Customer customer) throws CustomerException {
        Customer cust = customerRepository.save(customer);
        if(cust == null){
            throw new CustomerException("Customer not saved");
        }
        return cust;
    }

    @Override
    public void delete(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(
                "customer with ID " + id + " not found"));
        customerRepository.delete(customer);

    }

}