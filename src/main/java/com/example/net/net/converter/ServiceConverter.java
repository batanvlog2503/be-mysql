package com.example.net.net.converter;

import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.entity.Customer;
import com.example.net.net.entity.Service;
import com.example.net.net.entity.Session;
import com.example.net.net.request.ServiceRequest;
import org.springframework.stereotype.Component;

@Component

public class ServiceConverter {


    public ServiceResponseDTO convertServiceToServiceResponseDTO(Service service) {
        if (service == null) {
            return null;
        }

        ServiceResponseDTO dto = new ServiceResponseDTO();
        dto.setServiceId(service.getServiceId());
        dto.setCustomer(service.getCustomer());

        // Lấy sessionId từ Session entity
        if (service.getSession() != null) {
            dto.setSessionId(service.getSession().getSessionId());
        }

        return dto;
    }

    public Service convertServiceRequestToEntity(ServiceRequest request){
        if (request == null) {
            return null;
        }

        Service service = new Service();

        if (request.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setId(request.getCustomerId());
            service.setCustomer(customer); // add customer;
        }

        if (request.getSessionId() != null) {
            Session session = new Session();
            session.setSessionId(request.getSessionId());
            service.setSession(session); // add session
        }

        return service;
    }

}
