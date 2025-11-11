package com.example.net.net.controller;

import com.example.net.net.Repository.ServiceProductRepository;
import com.example.net.net.Response.ServiceResponseDTO;
import com.example.net.net.Service.ServiceProduct.IServiceProductService;
import com.example.net.net.Service.ServiceProduct.ServiceProductService;
import com.example.net.net.Service.Session.ISessionService;
import com.example.net.net.dto.ServiceProductDTO;
import com.example.net.net.Response.ServiceProductResponseDTO;
import com.example.net.net.entity.ServiceProduct;
import com.example.net.net.request.ServiceProductRequest;
import com.example.net.net.request.UpdateServiceProductRequest;
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
    private ServiceProductRepository serviceProductRepository;
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
    public ResponseEntity<ServiceProductResponseDTO> getAllServiceProductResponseById(@PathVariable Integer id){
        try{
            ServiceProductResponseDTO serviceProductResponseDTO = serviceProductService.getServiceProductResponseById(id);
            return ResponseEntity.ok(serviceProductResponseDTO);
        }catch (Exception e){
            e.printStackTrace();
            return (ResponseEntity<ServiceProductResponseDTO>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET: Lấy products theo serviceId (ĐÂY LÀ API CHO REACT FRONTEND)
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ServiceProductResponseDTO>> getProductsByServiceId(
            @PathVariable Integer serviceId) {
        try {
            List<ServiceProductResponseDTO> products =
                    serviceProductService.getServiceProductsByServiceId(serviceId);

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
                    serviceProductService.getServiceProductsBySessionId(sessionId);

            if (products.isEmpty()) {
                System.out.println("⚠ No products found for session " + sessionId);
                return ResponseEntity.noContent().build(); // 204 No Content
            }

            System.out.println(" Found " + products.size() + " products for session " + sessionId);
            return ResponseEntity.ok(products); // 200 OK
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    // POST: Tạo mới ServiceProduct
    @PostMapping
    public ResponseEntity<String> createServiceProduct(@RequestBody ServiceProductRequest request) {
        try {
            serviceProductService.createServiceProduct(request);
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
            @PathVariable Integer id,
            @RequestBody UpdateServiceProductRequest request) {
        try {
            serviceProductService.updateServiceProduct(id, request);
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

    @DeleteMapping
    public ResponseEntity<String> deleteServiceProductBySessionIdAndProductId(@RequestParam Integer sessionId, @RequestParam Integer productId){
        try {
            serviceProductService.deleteServiceProductBySessionIdAndProductId(sessionId, productId);
            return ResponseEntity.ok("Delete Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred");
        }
    }

//     const result = await axios.delete(
//      `http://localhost:8080/api/serviceproducts?sessionId=${sessionId}&productId=${productId}`
//    )

}
