package com.example.net.net.Service.Service;

import com.example.net.net.Repository.CustomerRepository;
import com.example.net.net.Repository.ServiceRepository;
import com.example.net.net.Repository.SessionRepository;
import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.converter.ServiceConverter;
import com.example.net.net.dto.ServiceDTO;
import com.example.net.net.entity.Customer;
import com.example.net.net.entity.Service;
import com.example.net.net.entity.Session;
import com.example.net.net.request.ServiceRequest;
import com.example.net.net.request.UpdateServiceRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class ServiceService implements IServiceService{

    @Autowired
    private ServiceConverter serviceConverter;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override // trả về List Response
    public List<ServiceResponseDTO> getAllServiceResponseDTO() { // trả về Service Response
        List<Service> services = serviceRepository.findAll();
        return services.stream().map(serviceConverter::convertServiceToServiceResponseDTO).collect(Collectors.toList());
    }

    @Override
    public ServiceResponseDTO getServiceResponseById(Integer id) {
        Service service = serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Service not found with id: " + id));

        return serviceConverter.convertServiceToServiceResponseDTO(service);
    }

    @Override
    @Transactional
    public Service createService(ServiceRequest request) {
        // Validate input
        if (request.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (request.getSessionId() == null) {
            throw new IllegalArgumentException("Session ID cannot be null");
        }
        Service service = serviceConverter.convertServiceRequestToEntity(request);



        // Convert và trả về DTO
        Service savedService = serviceRepository.save(service);
        return savedService;
    }

//    @Override
//    public List<Service> getServicesByCustomerId(Integer customerId) {
//        return serviceRepository.findByCustomer_Id(customerId);
//    }

    @Transactional
    public void updateService(Integer serviceId, UpdateServiceRequest request) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        if (request.getCustomerId() != null) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            service.setCustomer(customer);
        }

        if (request.getSessionId() != null) {
            Session session = sessionRepository.findById(request.getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session not found"));
            service.setSession(session);
        }

      serviceRepository.save(service);

    }
    @Override
    @Transactional
    public void deleteService(Integer serviceId) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + serviceId));

        serviceRepository.delete(service);
    }
@Override
public List<ServiceResponseDTO> getServicesByCustomerId(Integer customerId) {
    List<Service> services = serviceRepository.findByCustomer_Id(customerId);
    return services.stream()
            .map(serviceConverter::convertServiceToServiceResponseDTO)
            .collect(Collectors.toList());
}

    @Override
    public List<ServiceResponseDTO> getServicesBySessionId(Integer sessionId) {
        List<Service> services = serviceRepository.findBySession_SessionId(sessionId);

        return services.stream()
                .map(serviceConverter::convertServiceToServiceResponseDTO)
                .collect(Collectors.toList());
    }



}
