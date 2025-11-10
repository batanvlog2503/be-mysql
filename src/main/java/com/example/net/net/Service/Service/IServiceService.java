package com.example.net.net.Service.Service;

import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.dto.ServiceDTO;
import com.example.net.net.entity.Service;
import com.example.net.net.request.ServiceRequest;
import com.example.net.net.request.UpdateServiceRequest;

import java.util.List;

public interface IServiceService {


    //List<Service> getServicesByCustomerId(Integer customerId);
    //List<Service> getServicesBySessionId(Integer sessionId);

    List<ServiceResponseDTO> getServicesByCustomerId(Integer customerId);
    List<ServiceResponseDTO> getAllServiceResponseDTO();
    List<ServiceResponseDTO> getServicesBySessionId(Integer sessionId);
    ServiceResponseDTO getServiceResponseById(Integer id);
    Service createService(ServiceRequest request);
    void deleteService(Integer id);
    void updateService(Integer serviceId, UpdateServiceRequest request);
}