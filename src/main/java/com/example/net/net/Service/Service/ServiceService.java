package com.example.net.net.Service.Service;

import com.example.net.net.Repository.CustomerRepository;
import com.example.net.net.Repository.ServiceRepository;
import com.example.net.net.Repository.SessionRepository;
import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.dto.ServiceDTO;
import com.example.net.net.entity.Customer;
import com.example.net.net.entity.Service;
import com.example.net.net.entity.Session;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@org.springframework.stereotype.Service
public class ServiceService implements IServiceService{

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Service getServiceById(int id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
    }

    @Override
    @Transactional
    public ServiceResponseDTO createService(ServiceDTO dto) {
        // Validate
        if (dto.getCustomerId() == null) {
            throw new RuntimeException("Customer ID is required");
        }

        // Tìm customer
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + dto.getCustomerId()));
        Session session = null;
        if (dto.getSessionId() != null) {
            session = sessionRepository.findById(dto.getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session not found"));
        }
        // Tạo service mới
        Service service = new Service();
        service.setCustomer(customer);
        service.setSession(session);
        // Lưu vào DB
        Service savedService = serviceRepository.save(service);

        // Trả về DTO
        return new ServiceResponseDTO(
                savedService.getServiceId(),
                savedService.getCustomer().getId(),
                savedService.getSession() != null ? savedService.getSession().getSessionId() : null
        );
    }

    @Override
    @Transactional
    public void updateService(Service service) {
        // Kiểm tra service có tồn tại không
        if (!serviceRepository.existsById(service.getServiceId())) {
            throw new RuntimeException("Service not found with id: " + service.getServiceId());
        }
        serviceRepository.save(service);
    }

    @Override
    @Transactional
    public void deleteService(int id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
        serviceRepository.delete(service);
    }

    @Override
    public List<Service> getServicesByCustomerId(Integer customerId) {
        return serviceRepository.findByCustomer_Id(customerId);
    }

    @Override
    public List<Service> getServicesBySessionId(Integer sessionId) {
        return serviceRepository.findBySession_SessionId(sessionId);
    }



}
