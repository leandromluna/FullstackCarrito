package com.example.carrito.DTO;

import com.example.carrito.Domain.Model.EnumROL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String Email;
    private EnumROL rol;
    private Date lastBuy;
    private Boolean voucher;
}
