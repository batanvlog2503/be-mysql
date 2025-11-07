package com.example.net.net.controller;


import com.example.net.net.Service.Computer.IComputerService;
import com.example.net.net.Service.Customer.ICustomerService;
import com.example.net.net.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable(name = "id") int id){
        return customerService.getCustomerById(id);
    }

    @PutMapping("/update/{id}")
    public void updateCustomer(@PathVariable(name = "id") int id, @RequestBody Customer customer){
        customerService.updateCustomer(customer);
    }
    @PostMapping
    public void createCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
    }
}
