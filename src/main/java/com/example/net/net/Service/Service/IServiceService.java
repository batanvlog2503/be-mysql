package com.example.net.net.Service.Service;

import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.dto.ServiceDTO;
import com.example.net.net.entity.Service;

import java.util.List;

public interface IServiceService {

    List<Service> getAllServices();

    Service getServiceById(int id);

    ServiceResponseDTO createService(ServiceDTO dto);

    void updateService(Service service);

    void deleteService(int id);

    List<Service> getServicesByCustomerId(Integer customerId);
    List<Service> getServicesBySessionId(Integer sessionId);
}