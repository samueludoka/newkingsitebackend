package org.example.controller;

import org.example.dtos.request.ChangePassword;
import org.example.dtos.request.EmailBody;
import org.example.dtos.response.VerifyEmailResponse;
import org.example.model.Customer;
import org.example.model.ForgotPassword;
import org.example.repositories.CustomerRepository;
import org.example.repositories.ForgetPasswordRepository;
import org.example.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/api/v1/customer")
public class ForgotPasswordController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;


    @PostMapping("/verify-email/{email}")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<VerifyEmailResponse> verifyEmail(@PathVariable String email) {
        Customer customer = customerRepository.findByEmail(email);

        int otp = otpGenerator();
        EmailBody emailBody = EmailBody.builder()
                .to(email)
                .text("this is the otp for your forgot password request : " + otp)
                .subject("otp for forgot password request")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 700 * 1000))
                .customer(customer)
                .build();

        emailService.sendSimpleMessage(emailBody);
        forgetPasswordRepository.save(fp);
        VerifyEmailResponse response = new VerifyEmailResponse();
        response.setMessage("OTP Sent");
        response.setEmail(email);
        return ResponseEntity.ok(response);
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000,999999);
    }
    @PostMapping("/verifyOtp/{otp}/{email}")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<String> verifyOtp(@PathVariable String email, @PathVariable Integer otp) {
        Customer customer = customerRepository.findByEmail(email);
        ForgotPassword fp = forgetPasswordRepository.findByOtpAndCustomer(otp, customer).orElseThrow(
                () -> new RuntimeException("Invalid OTP for email: " + email));

        if (fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgetPasswordRepository.deleteById(fp.getFpid());
            return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok("OTP verified");

    }
    @PostMapping("/changePassword/{email}")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword,
                                                        @PathVariable String email) {
        if(!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
            return new ResponseEntity<>("Please enter the same password again", HttpStatus.EXPECTATION_FAILED);
        }
        String encodedPassword = changePassword.password();
        customerRepository.updatePassword(email, encodedPassword);

        return ResponseEntity.ok("Password changed");

    }

}

