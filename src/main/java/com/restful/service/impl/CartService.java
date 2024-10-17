package com.restful.service.impl;

import com.restful.entity.Cart;
import com.restful.repository.CartRepository;
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
        Long currentId = maxCartId();
        Long nextId = currentId+1;

        cart.setId(decimalFormat.format(nextId));
        return cartRepository.save(cart);
    }
}
