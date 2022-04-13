package com.shubham.shoppershub.service;

import com.shubham.shoppershub.dao.AddressRepository;
import com.shubham.shoppershub.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAllAddressByUserId(long id) {
        return addressRepository.findAllByUserId(id);
    }

    @Override
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(long id) {
        addressRepository.deleteById(id);
    }
}
