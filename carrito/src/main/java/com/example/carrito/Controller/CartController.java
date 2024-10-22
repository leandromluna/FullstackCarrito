package com.example.carrito.Controller;


import com.example.carrito.DTO.CartDTO;
import com.example.carrito.Domain.Service.iCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private iCartService cartService;

    @PostMapping("/purchase")
    public ResponseEntity<String> processPurchase(@RequestBody CartDTO cartDTO) {
        return cartService.processPurchase(cartDTO);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getPurchaseHistory() {
        return cartService.getPurchaseHistory();
    }
}
