package com.example.net.net.controller;

import com.example.net.net.Service.Service.IServiceService;
import com.example.net.net.dto.ServiceDTO;
import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.entity.Service;
import com.example.net.net.entity.Session;
import com.example.net.net.request.ServiceRequest;
import com.example.net.net.request.UpdateServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private IServiceService serviceService;

    // GET: Lấy tất cả services
    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> getAllServiceResponseDTO() {
        try {
            List<ServiceResponseDTO> response = serviceService.getAllServiceResponseDTO();

            // Nếu muốn sắp xếp theo serviceId tăng dần
            response.sort(Comparator.comparing(ServiceResponseDTO::getServiceId));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET: Lấy service theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> getServiceResponseById(@PathVariable Integer id) {
        try {
            ServiceResponseDTO response = serviceService.getServiceResponseById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET: Lấy services theo customerId
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ServiceResponseDTO>> getServicesByCustomerId(
            @PathVariable Integer customerId) {
        try {
            List<ServiceResponseDTO> response = serviceService.getServicesByCustomerId(customerId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<ServiceResponseDTO>> getSessionsBySessionId(
            @PathVariable Integer sessionId) {
        try {
//            List<Service> services = serviceService.getServicesByCustomerId(customerId);
//
//            List<ServiceResponseDTO> response = services.stream()
//                    .map(service -> new ServiceResponseDTO(
//                            service.getServiceId(),
//                            service.getCustomer().getId(),
//                            service.getSession() != null ? service.getSession().getSessionId() : null
//                    ))
//                    .collect(Collectors.toList());
//
//            return ResponseEntity.ok(response);
            List<ServiceResponseDTO> response = serviceService.getServicesBySessionId(sessionId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST: Tạo mới service
    @PostMapping
    public ResponseEntity<?> createService(@RequestBody ServiceRequest request) {
        try {
            Service service = serviceService.createService(request);
            return new ResponseEntity<>(service, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT: Cập nhật service
    @PutMapping("/{id}")
    public ResponseEntity<String> updateService(
            @PathVariable Integer id,
            @RequestBody UpdateServiceRequest request) {
        try {

            serviceService.updateService(id, request);
            return ResponseEntity.ok("Service updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred");
        }
    }

    // DELETE: Xóa service
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Integer id) {
        try {
            serviceService.deleteService(id);
            return ResponseEntity.ok("Service deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred");
        }
    }
}
