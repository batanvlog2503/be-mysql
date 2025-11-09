package com.example.net.net.request;

import com.example.net.net.entity.Product;
import com.example.net.net.entity.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateServiceProductRequest {
    private Service service;
    private Product product;
    private Integer quantity;

}
