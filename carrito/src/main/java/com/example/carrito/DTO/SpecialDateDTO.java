package com.example.carrito.DTO;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialDateDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private BigDecimal discount;

}
