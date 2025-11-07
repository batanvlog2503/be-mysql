package com.example.net.net.dto;

import com.example.net.net.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {

    private Integer customerId;
    private Integer sessionId;

}
