package com.example.carrito.DTO;


import com.example.carrito.Domain.Model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VipHistoryDTO {

    private Long id;
    private Date startVip;
    private Date endVip;
    private UserDTO user;
}
