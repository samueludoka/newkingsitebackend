package org.example.controller;

import org.example.dtos.request.*;
import org.example.dtos.response.*;
import org.example.exception.*;
import org.example.model.Country;
import org.example.model.Customer;
import org.example.model.Transaction;
import org.example.model.Wallet;
import org.example.repositories.CustomerRepository;
import org.example.services.CustomerService;
import org.example.services.TransactionService;
import org.example.services.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WalletService walletService;

    @PostMapping("/updateCustomer")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest) throws CustomerException {

        CustomerResponse updatedCustomer = customerService.updateCustomer(updateCustomerRequest);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @PostMapping("/invest")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<TransactionResponse> buyStockByName(@RequestBody InvestmentRequest investmentRequest) throws CustomerNotFoundException, StockException, ResourceNotFoundException {
        try {
            TransactionResponse transaction = transactionService.invest(investmentRequest);
            return new ResponseEntity<>(transaction, HttpStatus.ACCEPTED);
        }catch(StockException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(CustomerNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/transactions/{customerId}")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<List<Transaction>> getCustomerTransactions(@PathVariable Long customerId) throws CustomerException, ResourceNotFoundException {
        Customer customer = customerService.findCustomerById(customerId);

        List<Transaction> transactions= transactionService.findByCustomer(customer);


        return new ResponseEntity<>(transactions,HttpStatus.ACCEPTED);
    }

//    @RequestMapping(value = "/addFunds", method = RequestMethod.POST)
    @PostMapping("/addFunds")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<DepositResponse> deposit(@RequestBody DepositRequest depositRequest) throws CustomerNotFoundException, InsufficientFundsException {
        log.info("{}",depositRequest);
        DepositResponse customer = transactionService.fundWallet(depositRequest);;
//        log.info("{}",customer);
        return new ResponseEntity<DepositResponse>(customer,HttpStatus.CREATED);


    }
    @PostMapping("/withdrawFunds")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<WithdrawResponse> withdrawFunds(@RequestBody WithdrawRequest withdrawRequest) throws CustomerNotFoundException, InsufficientFundsException{
        try {
            WithdrawResponse withdrawResponse = transactionService.withdraw(withdrawRequest);
            return new ResponseEntity<>(withdrawResponse,HttpStatus.OK);
        } catch (InsufficientFundsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllCustomers")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity <List<Customer>> getAllCustomers() throws CustomerNotFoundException {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok(id);

   }
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @GetMapping("/viewCustomerWallet/{id}")
    public ResponseEntity<WalletResponse> viewCustomerWallet(@PathVariable Long id) {
        WalletResponse newWallet = walletService.viewWallet(id);
        return ResponseEntity.ok(newWallet);

    }
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @GetMapping("/getUser/{id}")
    public ResponseEntity<Customer> getUser(@PathVariable Long id) {
        Customer customer = customerService.getUser(id);
        return ResponseEntity.ok(customer);

    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @RequestMapping("/countries")
    public List<CountryRequest> getCountries(){
        return Arrays.stream(Country.values())
                .map(country -> new CountryRequest(country.getCountryName(), country.getPhoneCode()))
                .collect(Collectors.toList());
    }
}
