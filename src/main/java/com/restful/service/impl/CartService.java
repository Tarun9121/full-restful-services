package com.restful.service.impl;

import com.restful.dto.CartDTO;
import com.restful.entity.Cart;
import com.restful.exception.NotFoundException;
import com.restful.repository.CartRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    private DecimalFormat decimalFormat = new DecimalFormat("000000");

    public Long maxCartId() {
        Long currentId = cartRepository.maxCartId();
        return (currentId == null) ? 0L : currentId;
    }

    public Cart postCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public CartDTO getCartById(int cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("cart not found"));
        CartDTO cartDTO = new CartDTO();
        String formatedCartId = decimalFormat.format(cartId);
        BeanUtils.copyProperties(cart, cartDTO);
        cartDTO.setId(formatedCartId);
        return cartDTO;
    }
}
