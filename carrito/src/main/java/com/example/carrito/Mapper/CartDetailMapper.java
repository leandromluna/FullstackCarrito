package com.example.carrito.Mapper;


import com.example.carrito.DTO.CartDTO;
import com.example.carrito.DTO.CartDetailDTO;
import com.example.carrito.Domain.Model.Cart;
import com.example.carrito.Domain.Model.CartDetail;
import org.springframework.stereotype.Component;

@Component
public class CartDetailMapper {

    public CartDetailDTO toDto(CartDetail cartDetail) {
        CartDetailDTO cartDetailDTO = new CartDetailDTO();
        cartDetailDTO.setId(cartDetail.getId());
        cartDetailDTO.setAmount(cartDetail.getAmount());
        cartDetailDTO.setTotalPrice(cartDetail.getTotalPrice());
        return cartDetailDTO;
    }

    public CartDetail toModel(CartDetailDTO cartDetailDTO) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setId(cartDetailDTO.getId());
        cartDetail.setAmount(cartDetailDTO.getAmount());
        cartDetail.setTotalPrice(cartDetailDTO.getTotalPrice());
        return cartDetail;
    }
}
