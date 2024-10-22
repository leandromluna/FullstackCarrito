package com.example.carrito.Domain.Service;

import com.example.carrito.DTO.CartDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface iCartService {

    ResponseEntity<String> processPurchase(CartDTO cartDTO);
    ResponseEntity<?> getPurchaseHistory();
}
