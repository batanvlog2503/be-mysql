package com.example.net.net.Service.ServiceProduct;

import com.example.net.net.Repository.ProductRepository;
import com.example.net.net.Repository.ServiceProductRepository;
import com.example.net.net.Repository.ServiceRepository;
import com.example.net.net.converter.ServiceProductConverter;
import com.example.net.net.dto.ServiceProductDTO;
import com.example.net.net.Response.ServiceProductResponseDTO;
import com.example.net.net.entity.Product;
import com.example.net.net.entity.ServiceProduct;
import com.example.net.net.request.ServiceProductRequest;
import com.example.net.net.request.UpdateServiceProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceProductService implements IServiceProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceProductRepository serviceProductRepository;

    @Autowired
    private ServiceProductConverter serviceProductConverter;

    @Override
    public List<ServiceProductResponseDTO> getAllServiceProducts() {
        List<ServiceProduct> serviceProducts = serviceProductRepository.findAll();
        return serviceProducts.stream().map(serviceProductConverter::convertServiceProductToServiceProductResponseDTO).collect(Collectors.toList());


    }

    @Override
    public ServiceProductResponseDTO getServiceProductResponseById(Integer id) {
        ServiceProduct serviceProduct = serviceProductRepository.findById(id).orElseThrow(()-> new RuntimeException("ServiceProduct not found" + id));

        return serviceProductConverter.convertServiceProductToServiceProductResponseDTO(serviceProduct);
    }


    //  Lấy ServiceProduct theo sessionId
    @Override
    public List<ServiceProductResponseDTO> getServiceProductsBySessionId(Integer sessionId) {
        List<ServiceProduct> serviceProducts = serviceProductRepository.findBySessionIdWithProduct(sessionId);

        return serviceProducts.stream()
                .map(serviceProductConverter::convertServiceProductToServiceProductResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteServiceProductBySessionIdAndProductId(Integer sessionId, Integer productId) {
        if(sessionId == null || productId == null){
            throw new IllegalArgumentException("SessionID or ProductId can be not null");
        }

        ServiceProduct serviceProduct = serviceProductRepository.findBySessionIdAndProductId(sessionId, productId).orElseThrow(() -> new RuntimeException("Service Product not found" + productId + " and" + sessionId));
        serviceProductRepository.delete(serviceProduct);
    }


    @Override
    public List<ServiceProductResponseDTO> getServiceProductsByServiceId(Integer serviceId) {
        List<ServiceProduct> serviceProducts = serviceProductRepository.findByServiceIdWithProduct(serviceId);

        return serviceProducts.stream()
                .map(serviceProductConverter::convertServiceProductToServiceProductResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteServiceProduct(Integer id){
        ServiceProduct serviceProduct = serviceProductRepository.findById(id).orElseThrow(()-> new RuntimeException("Service Product not found" + id));
        serviceProductRepository.delete(serviceProduct);
    }

    @Override
    public void createServiceProduct(ServiceProductRequest request) {
        if(request.getService() == null){
            throw new IllegalArgumentException("Customer can be not null");
        }

        if(request.getProduct() ==null){
            throw  new IllegalArgumentException("Product can be not null");
        }

        ServiceProduct serviceProduct = serviceProductConverter.convertServiceProductRequestToEntity(request);
        serviceProductRepository.save(serviceProduct);
    }

    @Override
    public void updateServiceProduct(Integer id, UpdateServiceProductRequest request) {

        ServiceProduct serviceProduct = serviceProductRepository.findById(id).orElseThrow(()->new RuntimeException("Service Product not found" + id));

        serviceProduct.setProduct(request.getProduct());
        serviceProduct.setService(request.getService());
        serviceProduct.setQuantity(request.getQuantity());
        serviceProductRepository.save(serviceProduct);
    }
}