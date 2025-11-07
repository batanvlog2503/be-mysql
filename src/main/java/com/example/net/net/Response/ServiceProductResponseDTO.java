package com.example.net.net.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProductResponseDTO {
    private Integer productId;
    private String productName;
    private String category;
    private Integer quantity;
    private Integer price;

    private ServiceInfo service; //  thêm phần thông tin service

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServiceInfo {
        private Integer serviceId;
        private Integer customerId;
        private Integer sessionId;
    }
}