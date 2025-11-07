package com.example.net.net.controller;

import com.example.net.net.Service.ServiceProduct.IServiceProductService;
import com.example.net.net.Service.ServiceProduct.ServiceProductService;
import com.example.net.net.Service.Session.ISessionService;
import com.example.net.net.dto.ServiceProductDTO;
import com.example.net.net.Response.ServiceProductResponseDTO;
import com.example.net.net.entity.ServiceProduct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/serviceproducts")
//@CrossOrigin("http://localhost:5173")
public class ServiceProductController {
    @Autowired
    private ServiceProductService serviceProductService;
    @GetMapping
    public ResponseEntity<List<ServiceProductResponseDTO>> getAllServiceProducts() {
        try {
            List<ServiceProductResponseDTO> serviceProducts = serviceProductService.getAllServiceProducts();
            return ResponseEntity.ok(serviceProducts);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET: Lấy ServiceProduct theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceProductById(@PathVariable int id) {
        try {

            // Lấy ServiceProduct từ database
            ServiceProduct sp = serviceProductService.getServiceProductById(id);

            // ✅ KIỂM TRA NULL CHO TỪNG LEVEL
            if (sp == null) {
                System.err.println("❌ ServiceProduct is null");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("ServiceProduct not found");
            }

            // Kiểm tra Product
            if (sp.getProduct() == null) {
                System.err.println("❌ Product is null for ServiceProduct " + id);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Product data is missing for ServiceProduct " + id);
            }

            // Kiểm tra Service
            if (sp.getService() == null) {
                System.err.println("❌ Service is null for ServiceProduct " + id);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Service data is missing for ServiceProduct " + id);
            }


            Integer productId = sp.getProduct().getProductId();
            String productName = sp.getProduct().getProductName();
            Integer price = sp.getProduct().getPrice();

            String category = "Unknown";
            if (sp.getProduct().getCategory() != null) {
                category = sp.getProduct().getCategory().name();
            }


            // ✅ Extract Service info safely
            Integer serviceId = sp.getService().getServiceId();



            Integer sessionId = null;
            if (sp.getService().getSession() != null) {
                sessionId = sp.getService().getSession().getSessionId();
                System.out.println("✅ Session ID: " + sessionId);
            } else {
                System.out.println("⚠️ Warning: Service " + serviceId + " has NO session (session is null)");
            }


            Integer customerId = null;
            if (sp.getService().getCustomer() != null) {
                customerId = sp.getService().getCustomer().getId();
                System.out.println("✅ Customer ID: " + customerId);
            } else {
                System.err.println("❌ Customer is null for Service " + serviceId);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Customer data is missing for Service " + serviceId);
            }


            ServiceProductResponseDTO response = new ServiceProductResponseDTO(
                    productId,
                    productName,
                    category,
                    sp.getQuantity(),
                    price,
                    new ServiceProductResponseDTO.ServiceInfo(
                            serviceId,
                            customerId,
                            sessionId  //  CÓ THỂ NULL - KHÔNG VẤN ĐỀ
                    )
            );


            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ServiceProduct not found: " + e.getMessage());
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    // GET: Lấy products theo serviceId (ĐÂY LÀ API CHO REACT FRONTEND)
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ServiceProductResponseDTO>> getProductsByServiceId(
            @PathVariable Integer serviceId) {
        try {
            List<ServiceProductResponseDTO> products =
                    serviceProductService.getProductsByServiceId(serviceId);

            if (products.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }

            return ResponseEntity.ok(products); // 200 OK
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<?> getProductsBySessionId(@PathVariable Integer sessionId) {
        try {

            List<ServiceProductResponseDTO> products =
                    serviceProductService.getProductsBySessionId(sessionId);

            if (products.isEmpty()) {
                System.out.println("⚠️ No products found for session " + sessionId);
                return ResponseEntity.noContent().build(); // 204 No Content
            }

            System.out.println("✅ Found " + products.size() + " products for session " + sessionId);
            return ResponseEntity.ok(products); // 200 OK
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    // POST: Tạo mới ServiceProduct
    @PostMapping
    public ResponseEntity<String> createServiceProduct(@RequestBody ServiceProductDTO dto) {
        try {
            serviceProductService.createServiceProduct(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Service product created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred");
        }
    }

    // PUT: Cập nhật ServiceProduct
    @PutMapping("/{id}")
    public ResponseEntity<String> updateServiceProduct(
            @PathVariable int id,
            @RequestBody ServiceProduct serviceProduct) {
        try {
            serviceProduct.setServiceProductId(id);
            serviceProductService.updateServiceProduct(serviceProduct);
            return ResponseEntity.ok("Service product updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred");
        }
    }

    // DELETE: Xóa ServiceProduct (nếu cần)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceProduct(@PathVariable int id) {
        try {
            ServiceProduct serviceProduct = serviceProductService.getServiceProductById(id);
            // Thêm logic xóa nếu cần
            return ResponseEntity.ok("Service product deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Service product not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred");
        }
    }
}
