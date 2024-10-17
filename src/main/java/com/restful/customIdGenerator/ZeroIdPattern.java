package com.restful.customIdGenerator;

import com.restful.service.impl.CartService;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.text.DecimalFormat;

public class ZeroIdPattern implements IdentifierGenerator {
    private static final DecimalFormat decimalFormat = new DecimalFormat("000000");

    @Autowired
    private CartService cartService;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Long currentId = cartService.maxCartId();
        Long nextId = currentId +1;
        return decimalFormat.format(nextId);
    }
}
