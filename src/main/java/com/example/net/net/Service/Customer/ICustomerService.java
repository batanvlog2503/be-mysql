package com.example.net.net.Service.Customer;

import com.example.net.net.entity.Customer;
import com.example.net.net.request.UpdateCustomerByBalanceRequest;
import com.example.net.net.request.UpdateCustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerService  {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    void createCustomer(Customer customer);

    Customer updateCustomer(Integer id, UpdateCustomerRequest request);

    List<Customer> getCustomersByUsername(String username);
    void deleteCustomer(Integer id);
    Customer getCustomerByUsername(String username);

    Customer updateCustomerByBalance(String username, UpdateCustomerByBalanceRequest request);

}
