package com.example.net.net.Service.Customer;

import com.example.net.net.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerService  {

    List<Customer> getAllCustomers();

    Customer getCustomerById(int id);

    void createCustomer(Customer customer);

    void updateCustomer(Customer customer);

}
