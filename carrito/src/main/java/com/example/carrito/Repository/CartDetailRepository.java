package com.example.carrito.Repository;

import com.example.carrito.Domain.Model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
}
