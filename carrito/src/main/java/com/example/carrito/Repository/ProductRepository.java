package com.example.carrito.Repository;

import com.example.carrito.Domain.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameProduct(String nameProduct);
}
