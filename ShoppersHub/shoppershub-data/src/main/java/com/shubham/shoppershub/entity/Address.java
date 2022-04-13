package com.shubham.shoppershub.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addr_id")
    private Long id;

    @Column(name = "house_info")
    private String house_info;

    @Column(name = "area_info")
    private String area_info;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "addr_type")
    @Enumerated(value = EnumType.STRING)
    private AddrType addr_type;

    @ManyToOne
    private User user;

    public Address() {
    }

    public Address(String house_info, String area_info, String landmark, String city, String state, String pincode, AddrType addr_type) {
        this.house_info = house_info;
        this.area_info = area_info;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.addr_type = addr_type;
    }
}
