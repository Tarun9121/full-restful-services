package com.restful.controller;

import com.restful.entity.Address;
import com.restful.service.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/{addressId}")
    public Address getAddressById(@PathVariable("addressId")UUID addressId) {
        return addressService.getAddressById(addressId);
    }
}
