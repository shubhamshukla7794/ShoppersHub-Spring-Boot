package com.shubham.shoppershub.service;

import com.shubham.shoppershub.entity.Address;

import java.util.List;

public interface AddressService {
    public List<Address> findAllAddressByUserId(long id);
    public Address saveAddress(Address address);
    public void deleteAddressById(long id);
    public Address findByUserIdAndAddrId(long userId, long addrId);
}
