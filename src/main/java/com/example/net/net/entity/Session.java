package com.example.net.net.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Integer sessionId;

    @Column(name = "customer_id", nullable = false)

    private Integer customerId;


    @Column(name = "computer_id", nullable = false)
    private Integer computerId;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = true, nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP NULL DEFAULT NULL", nullable = true)
    private LocalDateTime endTime;
    @Column(name = "total", nullable = true)
    private Integer total;
    // 1 Session có nhiều Service
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Service> services;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Invoice> invoices;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<AuditLog> auditLogs;
}
