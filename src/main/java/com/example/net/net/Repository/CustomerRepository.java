package com.example.net.net.Repository;

import com.example.net.net.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByUsername(String username);
    List<Customer> findByUsernameIsContainingIgnoreCase(String username);
}
