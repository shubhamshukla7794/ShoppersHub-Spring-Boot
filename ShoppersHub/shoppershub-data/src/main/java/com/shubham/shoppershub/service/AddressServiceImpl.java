package com.shubham.shoppershub.service;

import com.shubham.shoppershub.dao.AddressRepository;
import com.shubham.shoppershub.dao.UserRepository;
import com.shubham.shoppershub.entity.Address;
import com.shubham.shoppershub.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

//    @Autowired
//    public AddressServiceImpl(AddressRepository addressRepository) {
//        this.addressRepository = addressRepository;
//    }

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Address> findAllAddressByUserId(long id) {
        return addressRepository.findAllByUserId(id);
    }

    @Override
    public Address saveAddress(Address address) {

        Optional<User> userOptional = userRepository.findById(address.getUser().getId());

        if (userOptional.isEmpty()) {
            return new Address();
        } else {
            User user = userOptional.get();
            Optional<Address> addressOptional = user.getAddresses().stream()
                    .filter(address1 -> address1.getId().equals(address.getId())).findFirst();

            if (addressOptional.isPresent()) {
                Address addressFound = addressOptional.get();
                addressFound.setHouse_info(address.getHouse_info());
                addressFound.setArea_info(address.getArea_info());
                addressFound.setLandmark(address.getLandmark());
                addressFound.setCity(address.getCity());
                addressFound.setState(address.getState());
                addressFound.setPincode(address.getPincode());
                addressFound.setAddr_type(address.getAddr_type());
            } else {
                address.setUser(user);
                user.addAddress(address);
            }

            User savedUser = userRepository.save(user);

            Optional<Address> savedAddressOptional = savedUser.getAddresses().stream()
                    .filter(address1 -> address1.getId().equals(address.getId())).findFirst();
            if (savedAddressOptional.isEmpty()) {
                savedAddressOptional = savedUser.getAddresses().stream()
                        .filter(address1 -> address1.getHouse_info().equals(address.getHouse_info()))
                        .filter(address1 -> address1.getArea_info().equals(address.getArea_info()))
                        .filter(address1 -> address1.getLandmark().equals(address.getLandmark()))
                        .filter(address1 -> address1.getCity().equals(address.getCity()))
                        .filter(address1 -> address1.getState().equals(address.getState()))
                        .filter(address1 -> address1.getPincode().equals(address.getPincode()))
                        .filter(address1 -> address1.getAddr_type().equals(address.getAddr_type()))
                        .findFirst();
            }

            return savedAddressOptional.get();
        }
    }

    @Override
    public void deleteAddressById(long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address findByUserIdAndAddrId(long userId, long addrId) {

        Optional<User> userOptional = Optional.ofNullable(userRepository.getUserById(userId));

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with Id " + userId + " not found!!!");
        }

        User foundUser = userOptional.get();

        Optional<Address> addressOptional = foundUser.getAddresses().stream()
                .filter(address -> address.getId().equals(addrId)).findFirst();

        if (addressOptional.isEmpty()) {
            throw new RuntimeException("Address with Id " + addrId + " not found!!!");
        }

        return addressOptional.get();
    }
}
