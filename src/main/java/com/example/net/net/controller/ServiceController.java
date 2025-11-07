package com.example.net.net.controller;

import com.example.net.net.Service.Service.IServiceService;
import com.example.net.net.dto.ServiceDTO;
import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.entity.Service;
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
    public ResponseEntity<List<ServiceResponseDTO>> getAllServices() {
        try {
            List<Service> services = serviceService.getAllServices();

            List<ServiceResponseDTO> response = services.stream()
                    .sorted(Comparator.comparing(Service::getServiceId))
                    .map(service -> new ServiceResponseDTO(
                            service.getServiceId(),
                            service.getCustomer() != null ? service.getCustomer().getId() : null,
                            service.getSession() != null ? service.getSession().getSessionId(): null
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET: Lấy service theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> getServiceById(@PathVariable int id) {
        try {
            Service service = serviceService.getServiceById(id);

            ServiceResponseDTO response = new ServiceResponseDTO(
                    service.getServiceId(),
                    service.getCustomer() != null ? service.getCustomer().getId() : null,
                    service.getSession() != null ? service.getSession().getSessionId() : null
            );

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
            List<Service> services = serviceService.getServicesByCustomerId(customerId);

            List<ServiceResponseDTO> response = services.stream()
                    .map(service -> new ServiceResponseDTO(
                            service.getServiceId(),
                            service.getCustomer().getId(),
                            service.getSession() != null ? service.getSession().getSessionId() : null
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST: Tạo mới service
    @PostMapping
    public ResponseEntity<ServiceResponseDTO> createService(@RequestBody ServiceDTO dto) {
        try {
            ServiceResponseDTO response = serviceService.createService(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
            @PathVariable int id,
            @RequestBody Service service) {
        try {
            service.setServiceId(id);
            serviceService.updateService(service);
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
    public ResponseEntity<String> deleteService(@PathVariable int id) {
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
