package com.restful.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity @Table(name="address")
@Data @AllArgsConstructor @NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    private UUID addressId;

    @Column(name="pincode")
    private String pincode;

    @Column(name = "city")
    private String city;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Student student;

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", pincode='" + pincode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
