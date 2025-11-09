package com.example.net.net.dto;

import com.example.net.net.entity.AuditLog;
import com.example.net.net.entity.Invoice;
import com.example.net.net.entity.Service;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class SessionDTO {

    private Integer customerId;
    private Integer computerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer total;
//    private List<Service> services;
//    private List<Invoice> invoices;
//    private List<AuditLog> auditLogs;
}
