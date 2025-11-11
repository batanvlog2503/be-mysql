package com.example.net.net.controller;


import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.Service.Computer.IComputerService;
import com.example.net.net.Service.Customer.ICustomerService;
import com.example.net.net.entity.Customer;
import com.example.net.net.request.UpdateCustomerByBalanceRequest;
import com.example.net.net.request.UpdateCustomerRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Customer getCustomerById(@PathVariable(name = "id") Integer id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/name/{username}")
    public ResponseEntity<Customer> getCustomerByUsername(@PathVariable String username) {
        try {
            Customer customer = customerService.getCustomerByUsername(username);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/name/update/{username}")
    public ResponseEntity<?> updateCustomerByBalance(@PathVariable String username, @RequestBody UpdateCustomerByBalanceRequest request){
        try{
            Customer updateCustomer = customerService.updateCustomerByBalance(username, request);
            return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable(name = "id") Integer id, @RequestBody UpdateCustomerRequest request){
        try{
            Customer updateCustomer = customerService.updateCustomer(id,request);
            return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public void createCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") Integer id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("Customer deleted successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Không thể xóa khách hàng vì đang có dữ liệu liên quan (sessions, services, etc.)");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi xóa khách hàng: " + e.getMessage());
        }
    }
}
