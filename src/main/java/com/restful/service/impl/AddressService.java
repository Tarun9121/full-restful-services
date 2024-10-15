package com.restful.service.impl;

import com.restful.entity.Address;
import com.restful.exception.NotFoundException;
import com.restful.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address getAddressById(UUID addressId) {
        return addressRepository.findById(addressId).orElseThrow(() -> new NotFoundException("address not found"));
    }
}
