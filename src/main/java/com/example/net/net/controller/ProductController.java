package com.example.net.net.controller;

import com.example.net.net.Service.Customer.ICustomerService;
import com.example.net.net.Service.Product.IProductService;
import com.example.net.net.dto.ProductDTO;
import com.example.net.net.dto.ServiceDTO;
import com.example.net.net.entity.Product;
import com.example.net.net.entity.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Product> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return modelMapper.map(products, new TypeToken<List<ProductDTO>>(){}.getType());
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable(name = "id") int id){
        return productService.getProductById(id);
    }

//    @PutMapping("/update/{id}")
//    public void updateProduct(@PathVariable(name = "id") int id, @RequestBody Product
//                              product){
//        product.setProductId(id);
//        productService.updateProduct(product);
//    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable(name = "id") int id, @RequestBody Product product){
//        product.setProductId(id);
//        Product updateProduct = productService.updateProduct(product);
//        return ResponseEntity.ok(updateProduct);
//    }

    @PutMapping("/update/{id}")

    public void updateProduct(@PathVariable(name = "id") int id, @RequestBody Product product)
    {
        product.setProductId(id);
        productService.updateProduct(product);
    }
}
