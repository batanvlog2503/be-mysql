package com.example.net.net.Service.ServiceProduct;

import com.example.net.net.dto.ServiceProductDTO;
import com.example.net.net.Response.ServiceProductResponseDTO;
import com.example.net.net.entity.ServiceProduct;
import com.example.net.net.request.ServiceProductRequest;
import com.example.net.net.request.UpdateServiceProductRequest;

import java.util.List;

public interface IServiceProductService {

    List<ServiceProductResponseDTO> getAllServiceProducts();

    ServiceProductResponseDTO getServiceProductResponseById(Integer id);
    void deleteServiceProduct(Integer id);
    void createServiceProduct(ServiceProductRequest request);
    void updateServiceProduct(Integer id, UpdateServiceProductRequest request);
    List<ServiceProductResponseDTO> getServiceProductsBySessionId(Integer SessionId);

    void deleteServiceProductBySessionIdAndProductId(Integer sessionId, Integer productId);

    List<ServiceProductResponseDTO> getServiceProductsByServiceId(Integer serviceId);

}
