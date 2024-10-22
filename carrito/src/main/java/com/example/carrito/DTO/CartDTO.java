package com.example.carrito.DTO;


import com.example.carrito.Domain.Model.EnumCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private Long id;
    private Date creationDate;
    private BigDecimal total;
    private EnumCart cartType;
    private UserDTO user;
    private List<CartDetailDTO> cartDetails;
}
