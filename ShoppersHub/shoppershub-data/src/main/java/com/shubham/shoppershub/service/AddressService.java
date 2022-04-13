package com.shubham.shoppershub.service;

import com.shubham.shoppershub.entity.Address;

import java.util.List;

public interface AddressService {
    public List<Address> findAllAddressByUserId(long id);
    public void saveAddress(Address address);
    public void deleteAddressById(long id);
}
