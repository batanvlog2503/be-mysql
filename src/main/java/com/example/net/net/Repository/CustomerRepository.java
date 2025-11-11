package com.example.net.net.Repository;

import com.example.net.net.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByUsername(String username);
}
