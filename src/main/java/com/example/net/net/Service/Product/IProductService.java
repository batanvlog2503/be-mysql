package com.example.net.net.Service.Product;

import com.example.net.net.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();
    Product getProductById(Integer id);

    void updateProduct(Product product);
}
