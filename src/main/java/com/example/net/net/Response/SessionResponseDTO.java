package com.example.net.net.Response;

import com.example.net.net.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponseDTO {
    private Integer sessionId;
    private Integer customerId;
    private Integer computerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer total;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class ServiceDTO{
        private Integer serviceId;
        private Customer customer;
    }
    private List<ServiceDTO> services;
}
