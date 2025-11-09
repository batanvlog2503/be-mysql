package com.example.net.net.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "auditlog")
    public class AuditLog {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "log_id")
        private Integer logId;

        @ManyToOne
        @JoinColumn(name = "session_id", nullable = false)
        private Session session;

        @ManyToOne
        @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;

        @ManyToOne
        @JoinColumn(name = "computer_id", nullable = false)
        private Computer computer;
    }
