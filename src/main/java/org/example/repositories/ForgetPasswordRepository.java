package org.example.repositories;

import org.example.model.Customer;
import org.example.model.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgetPasswordRepository extends JpaRepository<ForgotPassword, Long> {

    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.customer = ?2")
    Optional<ForgotPassword> findByOtpAndCustomer(Integer otp, Customer customer);
}
