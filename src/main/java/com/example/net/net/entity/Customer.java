package com.example.net.net.entity;


import com.example.net.net.Enum.Type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private int id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('Vip', 'Normal') DEFAULT 'Normal')")
    private Type type;

    @Column(name = "balance", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int balance;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<Session> sessions;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<AuditLog> auditLogs;
}
