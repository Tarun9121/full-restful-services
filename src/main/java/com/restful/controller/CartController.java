package com.restful.controller;

import com.restful.dto.CartDTO;
import com.restful.entity.Cart;
import com.restful.repository.CartRepository;
import com.restful.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping
    public Cart postCart(@RequestBody Cart cart) {
        return cartService.postCart(cart);
    }

    @GetMapping("/{cartId}")
    public CartDTO getCartById(@PathVariable("cartId") int cartId) {
        return cartService.getCartById(cartId);
    }
}
