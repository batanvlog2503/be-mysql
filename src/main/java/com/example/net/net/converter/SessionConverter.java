package com.example.net.net.converter;

import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.Response.SessionResponseDTO;
import com.example.net.net.entity.Customer;
import com.example.net.net.entity.Session;
import com.example.net.net.request.SessionRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionConverter {

    public SessionResponseDTO convertSessionToSessionResponseDTO(Session session){
        if(session == null){
            return null;
        }

        SessionResponseDTO dto = new SessionResponseDTO();
        dto.setSessionId(session.getSessionId());
        dto.setComputerId(session.getComputerId());
        dto.setCustomerId(session.getCustomerId());
        dto.setStartTime(session.getStartTime());
        dto.setEndTime(session.getEndTime());
        dto.setTotal(session.getTotal());

        if(session.getServices() != null && !session.getServices().isEmpty()){
            List<SessionResponseDTO.ServiceDTO> serviceDTOs = session.getServices()
                    .stream()
                    .map(service -> {
                        SessionResponseDTO.ServiceDTO serviceDTO = new SessionResponseDTO.ServiceDTO();
                        serviceDTO.setServiceId(service.getServiceId());
                        serviceDTO.setCustomer(service.getCustomer());
                        return serviceDTO;
                    })
                    .collect(Collectors.toList());
            dto.setServices(serviceDTOs);
        }

        return dto;
    }

//    public Session convertSessionRequestToEntity(SessionRequest request){
//        if(request == null){
//            return null;
//        }
//
//        Session session = new Session();
//        if(request.getCustomerId()!=null){
//            session.setCustomerId(request.getCustomerId());
//        }
//
//    }
}


//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "session")
//public class Session {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "session_id")
//    private int sessionId;
//
//    @Column(name = "customer_id", nullable = false)
//
//    private int customerId;
//
//
//    @Column(name = "computer_id", nullable = false)
//    private int computerId;
//
//    @Column(name = "start_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = true, nullable = false)
//    private LocalDateTime startTime;
//
//    @Column(name = "end_time", columnDefinition = "TIMESTAMP NULL DEFAULT NULL", nullable = true)
//    private LocalDateTime endTime;
//    @Column(name = "total", nullable = true)
//    private Integer total;
//    // 1 Session có nhiều Service
//    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
//    private List<Service> services;
//    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
//    private List<Invoice> invoices;
//    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
//    private List<AuditLog> auditLogs;
//}
