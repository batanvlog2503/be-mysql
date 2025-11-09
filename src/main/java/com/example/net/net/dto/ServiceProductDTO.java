package com.example.net.net.dto;

import com.example.net.net.entity.Product;
import com.example.net.net.entity.Service;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

//public class ServiceProduct {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "service_product_id")
//    private Integer serviceProductId;
//
//    // Liên kết tới Service (nhiều sản phẩm có thể thuộc một dịch vụ)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "service_id", nullable = false)
//    @JsonBackReference
//    private Service service;
//
//    // Liên kết tới Product (một sản phẩm có thể dùng trong nhiều dịch vụ)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
//
//    @Column(nullable = false)
//    private Integer quantity = 0;
//}
