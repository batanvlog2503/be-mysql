package com.example.net.net.Service.ServiceProduct;

import com.example.net.net.dto.ServiceProductDTO;
import com.example.net.net.Response.ServiceProductResponseDTO;
import com.example.net.net.entity.ServiceProduct;

import java.util.List;

public interface IServiceProductService {

    List<ServiceProductResponseDTO> getAllServiceProducts();

    ServiceProduct getServiceProductById(int id);

    void createServiceProduct(ServiceProductDTO dto);

    void updateServiceProduct(ServiceProduct serviceProduct);

    List<ServiceProduct> getServiceProductsByServiceId(Integer serviceId);

    List<ServiceProductResponseDTO> getProductsBySessionId(Integer SessionId);


    List<ServiceProductResponseDTO> getProductsByServiceId(Integer serviceId);

}
