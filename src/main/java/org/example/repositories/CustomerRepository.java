package org.example.repositories;

import jakarta.transaction.Transactional;
import org.example.model.Admin;
import org.example.model.Customer;
import org.hibernate.query.Page;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.StackManipulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Customer  c set c.password = ?2 where c.email = ?1")
    void updatePassword(String email,String password);

}
