package com.example.net.net.Service.Customer;

import com.example.net.net.Repository.CustomerRepository;
import com.example.net.net.Service.Computer.IComputerService;
import com.example.net.net.entity.Computer;
import com.example.net.net.entity.Customer;
import com.example.net.net.request.UpdateCustomerByBalanceRequest;
import com.example.net.net.request.UpdateCustomerRequest;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
       return customerRepository.findById(id).get();
    }

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Integer id, UpdateCustomerRequest request){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer Not Found" + id));

        customer.setUsername(request.getUsername());
        customer.setPassword(request.getPassword());
        customer.setType(request.getType());
        customer.setBalance(request.getBalance());

        Customer saveCustomer = customerRepository.save(customer);
        return saveCustomer;
    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not found with id: " + id));

        try {
            customerRepository.delete(customer);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Không thể xóa khách hàng vì đang có dữ liệu liên quan");
        }
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if(customer == null){
            throw new RuntimeException("Customer not found with username: " + username);

        }
        return customer;
    }

    @Override
    public Customer updateCustomerByBalance(String username, UpdateCustomerByBalanceRequest request) {
        Customer customer = customerRepository.findByUsername(username);

        if(customer == null){
            throw new RuntimeException("Customer not found with username: " + username);

        }

        customer.setBalance(request.getBalance());

        Customer saveCustomer = customerRepository.save(customer);
        return saveCustomer;

    }
}
