package com.example.net.net.dto;

import com.example.net.net.entity.Product;
import com.example.net.net.entity.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProductDTO {
    private Service service; // trong đây có sessionId
    private Product product;
    private Integer quantity;
}