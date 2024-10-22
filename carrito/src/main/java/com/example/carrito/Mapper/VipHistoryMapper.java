package com.example.carrito.Mapper;


import com.example.carrito.DTO.ProductDTO;
import com.example.carrito.DTO.UserDTO;
import com.example.carrito.DTO.VipHistoryDTO;
import com.example.carrito.Domain.Model.Product;
import com.example.carrito.Domain.Model.Users;
import com.example.carrito.Domain.Model.VipHistory;
import org.springframework.stereotype.Component;

@Component
public class VipHistoryMapper {

    public VipHistoryDTO toDto(VipHistory vipHistory) {
        VipHistoryDTO vipHistoryDTO = new VipHistoryDTO();
        vipHistoryDTO.setId(vipHistory.getId());
        vipHistoryDTO.setEndVip(vipHistory.getEndVip());
        vipHistoryDTO.setStartVip(vipHistory.getStartVip());
        UserDTO userDto = new UserDTO();
        userDto.setId(vipHistory.getUser().getId());
        userDto.setLastBuy(vipHistory.getUser().getLastBuy());
        userDto.setRol(vipHistory.getUser().getRol());
        userDto.setEmail(vipHistory.getUser().getEmail());
        vipHistoryDTO.setUser(userDto);
        return vipHistoryDTO;
    }

    public VipHistory toModel(VipHistoryDTO vipHistoryDTO) {
        VipHistory vipHistory = new VipHistory();
        vipHistory.setId(vipHistoryDTO.getId());
        vipHistory.setEndVip(vipHistoryDTO.getEndVip());
        vipHistory.setStartVip(vipHistoryDTO.getStartVip());
        Users user = new Users();
        user.setId(vipHistoryDTO.getUser().getId());
        user.setLastBuy(vipHistoryDTO.getUser().getLastBuy());
        user.setRol(vipHistoryDTO.getUser().getRol());
        user.setEmail(vipHistoryDTO.getUser().getEmail());
        vipHistory.setUser(user);
        return vipHistory;
    }
}
