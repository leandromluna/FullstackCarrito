package com.example.carrito.Controller;


import com.example.carrito.DTO.VipHistoryDTO;
import com.example.carrito.Domain.Model.VipHistory;
import com.example.carrito.Domain.Service.iVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vip")
public class VipHistoryController {

    @Autowired
    private iVipService vipService;

    @GetMapping("/history")
    public ResponseEntity<List<VipHistoryDTO>> getVipHistory() {
        List<VipHistoryDTO> vipHistoryList = vipService.getVipHistory();
        return ResponseEntity.ok(vipHistoryList);
    }

}
