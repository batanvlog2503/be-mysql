package com.example.net.net.dto;

import com.example.net.net.Enum.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Integer productId;
    private String productName;
    private Category category;
    private int price;
    private int quantity;
}
