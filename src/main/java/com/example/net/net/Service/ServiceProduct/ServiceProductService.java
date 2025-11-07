package com.example.net.net.Service.ServiceProduct;

import com.example.net.net.Repository.ProductRepository;
import com.example.net.net.Repository.ServiceProductRepository;
import com.example.net.net.Repository.ServiceRepository;
import com.example.net.net.dto.ServiceProductDTO;
import com.example.net.net.Response.ServiceProductResponseDTO;
import com.example.net.net.entity.Product;
import com.example.net.net.entity.ServiceProduct;
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

    @Override
    @Transactional(readOnly = true)
    public List<ServiceProductResponseDTO> getAllServiceProducts() {
        List<ServiceProduct> serviceProducts = serviceProductRepository.findAll();

        // Sắp xếp theo serviceId, sau đó productId
        serviceProducts.sort(Comparator
                .comparing((ServiceProduct sp) ->
                        sp.getService() != null ? sp.getService().getServiceId() : Integer.MAX_VALUE)
                .thenComparing(sp ->
                        sp.getProduct() != null ? sp.getProduct().getProductId() : Integer.MAX_VALUE));

        return serviceProducts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceProduct getServiceProductById(int id) {
        return serviceProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceProduct not found with id: " + id));
    }

    @Override
    @Transactional
    public void createServiceProduct(ServiceProductDTO dto) {
        // Kiểm tra DTO
        if (dto.getService() == null || dto.getService().getServiceId() == null) {
            throw new RuntimeException("Service is required");
        }
        if (dto.getProduct() == null || dto.getProduct().getProductId() == null) {
            throw new RuntimeException("Product is required");
        }

        // Lấy Service từ database
        com.example.net.net.entity.Service service = serviceRepository
                .findById(dto.getService().getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + dto.getService().getServiceId()));

        // Lấy Product từ database
        Product product = productRepository
                .findById(dto.getProduct().getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProduct().getProductId()));

        // Tạo ServiceProduct mới
        ServiceProduct serviceProduct = new ServiceProduct();
        serviceProduct.setService(service);
        serviceProduct.setProduct(product);
        serviceProduct.setQuantity(dto.getQuantity());

        serviceProductRepository.save(serviceProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceProductResponseDTO> getProductsByServiceId(Integer serviceId) {
        List<ServiceProduct> serviceProducts =
                serviceProductRepository.findByServiceIdWithProduct(serviceId);

        // Sắp xếp theo serviceId
        serviceProducts.sort(Comparator.comparing(sp ->
                sp.getService() != null ? sp.getService().getServiceId() : Integer.MAX_VALUE));

        return serviceProducts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateServiceProduct(ServiceProduct serviceProduct) {
        if (!serviceProductRepository.existsById(serviceProduct.getServiceProductId())) {
            throw new RuntimeException("ServiceProduct not found");
        }
        serviceProductRepository.save(serviceProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceProduct> getServiceProductsByServiceId(Integer serviceId) {
        return serviceProductRepository.findAllByService_ServiceId(serviceId);
    }

    @Override
    public List<ServiceProductResponseDTO> getProductsBySessionId(Integer sessionId) {
        List<ServiceProduct> serviceProducts =
                serviceProductRepository.findBySessionIdWithProduct(sessionId);

        if (serviceProducts.isEmpty()) {
            System.out.println("⚠️ No ServiceProducts found for sessionId: " + sessionId);
        }

        return serviceProducts.stream()
                .map(sp -> {
                    var service = sp.getService();
                    var product = sp.getProduct();

                    Integer serviceIdVal = service != null ? service.getServiceId() : null;
                    Integer customerIdVal = (service != null && service.getCustomer() != null)
                            ? service.getCustomer().getId() : null;
                    Integer sessionIdVal = (service != null && service.getSession() != null)
                            ? service.getSession().getSessionId() : null;

                    return new ServiceProductResponseDTO(
                            product != null ? product.getProductId() : null,
                            product != null ? product.getProductName() : null,
                            product != null && product.getCategory() != null
                                    ? product.getCategory().name() : null,
                            sp.getQuantity(),
                            product != null ? product.getPrice() : null,
                            new ServiceProductResponseDTO.ServiceInfo(
                                    serviceIdVal,
                                    customerIdVal,
                                    sessionIdVal
                            )
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * ✅ Helper method: Convert ServiceProduct entity sang DTO với null safety
     */
    private ServiceProductResponseDTO convertToDTO(ServiceProduct sp) {
        // Null safety cho Product
        Integer productId = null;
        String productName = "Unknown";
        String category = "Unknown";
        Integer price = 0;

        if (sp.getProduct() != null) {
            productId = sp.getProduct().getProductId();
            productName = sp.getProduct().getProductName();
            price = sp.getProduct().getPrice();

            if (sp.getProduct().getCategory() != null) {
                category = sp.getProduct().getCategory().name();
            }
        }

        // Null safety cho Service
        Integer serviceId = null;
        Integer customerId = null;
        Integer sessionId = null;

        if (sp.getService() != null) {
            serviceId = sp.getService().getServiceId();

            // Null safety cho Customer
            if (sp.getService().getCustomer() != null) {
                customerId = sp.getService().getCustomer().getId();
            }

            // ✅ QUAN TRỌNG: Null safety cho Session
            if (sp.getService().getSession() != null) {
                sessionId = sp.getService().getSession().getSessionId();
            }
        }

        return new ServiceProductResponseDTO(
                productId,
                productName,
                category,
                sp.getQuantity(),
                price,
                new ServiceProductResponseDTO.ServiceInfo(
                        serviceId,
                        customerId,
                        sessionId  // ✅ Có thể null nhưng không crash
                )
        );
    }
}