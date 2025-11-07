package com.example.net.net.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

//    @ManyToOne(fetch = FetchType.LAZY)le = false)
    /// /    private Product product;
//    @JoinColumn(name = "product_id", nullab
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "session_id", nullable = false)
//    @JsonBackReference  // tránh vòng lặp JSON
//    private Session session;
    // Thêm quan hệ với Session
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id", nullable = true)
    private Session session;

    // 1 service có thể có nhiểu ServiceProducts
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ServiceProduct> serviceProducts;
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

}
