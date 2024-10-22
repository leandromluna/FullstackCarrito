package com.example.carrito.Domain.Service;

import com.example.carrito.DTO.VipHistoryDTO;

import java.util.Date;
import java.util.List;

public interface iVipService {
    void updateVipStatus(Date date);
    List<VipHistoryDTO> getVipHistory();
}
