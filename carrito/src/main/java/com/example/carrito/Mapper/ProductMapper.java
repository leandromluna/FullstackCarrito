package com.example.carrito.Mapper;

import com.example.carrito.DTO.ProductDTO;
import com.example.carrito.Domain.Model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setNameProduct(product.getNameProduct());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        productDTO.setImageUrl(product.getImageUrl());
        return productDTO;
    }

    public Product toModel(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setNameProduct(productDTO.getNameProduct());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setImageUrl(productDTO.getImageUrl());
        return product;
    }
}
