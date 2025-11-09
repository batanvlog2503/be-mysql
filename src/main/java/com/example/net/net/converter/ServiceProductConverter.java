package com.example.net.net.converter;

import com.example.net.net.Response.ServiceProductResponseDTO;
import com.example.net.net.entity.Product;
import com.example.net.net.entity.ServiceProduct;
import com.example.net.net.request.ServiceProductRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class ServiceProductConverter {

    public ServiceProductResponseDTO convertServiceProductToServiceProductResponseDTO(ServiceProduct serviceProduct){
        if (serviceProduct == null) {
            return null;
        }

        ServiceProductResponseDTO dto = new ServiceProductResponseDTO();

        // Lấy thông tin product
        if (serviceProduct.getProduct() != null) {
            dto.setProductId(serviceProduct.getProduct().getProductId());
            dto.setProductName(serviceProduct.getProduct().getProductName());
            dto.setCategory(serviceProduct.getProduct().getCategory().name()); // chuyển thành String
            dto.setPrice(serviceProduct.getProduct().getPrice());
        }

        // Lấy thông tin service
        if (serviceProduct.getService() != null) {
            ServiceProductResponseDTO.ServiceInfo serviceInfo = new ServiceProductResponseDTO.ServiceInfo();
            serviceInfo.setServiceId(serviceProduct.getService().getServiceId());

            if (serviceProduct.getService().getCustomer() != null) {
                serviceInfo.setCustomerId(serviceProduct.getService().getCustomer().getId());
            }
            if (serviceProduct.getService().getSession() != null) {
                serviceInfo.setSessionId(serviceProduct.getService().getSession().getSessionId());
            }

            dto.setService(serviceInfo);
        }

        // Gán số lượng
        dto.setQuantity(serviceProduct.getQuantity());

        return dto;
    }

    public ServiceProduct convertServiceProductRequestToEntity(ServiceProductRequest request){
        if(request == null){
            return null;
        }

        ServiceProduct serviceProduct = new ServiceProduct();

        if(request.getProduct() !=null){
           serviceProduct.setProduct(request.getProduct());
        }

        if(request.getService() != null){
            serviceProduct.setService(request.getService());
        }

        serviceProduct.setQuantity(request.getQuantity());
        return serviceProduct;
    }


}
//private Integer productId;
//private String productName;
//private String category;
//private Integer quantity;
//private Integer price;
//
//
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public static class ServiceInfo {
//    private Integer serviceId;
//    private Integer customerId;
//    private Integer sessionId;
//}
//private ServiceProductResponseDTO.ServiceInfo service; //
//}
// service, product, quantity;
