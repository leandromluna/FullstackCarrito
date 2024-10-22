package com.example.carrito.DTO;


import com.example.carrito.Domain.Model.Cart;
import com.example.carrito.Domain.Model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDTO {

    private Long id;
    private ProductDTO2 product;
    private int amount;
    private BigDecimal totalPrice;
}
