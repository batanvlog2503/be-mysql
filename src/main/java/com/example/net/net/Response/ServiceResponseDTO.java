package com.example.net.net.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseDTO {
    private Integer serviceId;
    private Integer customerId;
    private Integer sessionId;
}
