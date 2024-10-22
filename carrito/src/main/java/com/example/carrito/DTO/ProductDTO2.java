package com.example.carrito.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO2 {

        private Long id;
        private String name;
        private BigDecimal price;
}
