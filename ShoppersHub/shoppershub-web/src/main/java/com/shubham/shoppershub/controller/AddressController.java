package com.shubham.shoppershub.controller;

import com.shubham.shoppershub.entity.Address;
import com.shubham.shoppershub.entity.User;
import com.shubham.shoppershub.service.AddressService;
import com.shubham.shoppershub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {
    private final UserService userService;
    private final AddressService addressService;

    public AddressController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping("/user/{userId}/addresses")
    public String listUserAddresses(@PathVariable String userId, Model model) {
//        model.addAttribute("user", userService.getUserById(Long.valueOf(userId)));
        model.addAttribute("addressList", addressService.findAllAddressByUserId(Long.valueOf(userId)));
        return "user/address/list";
    }

    @GetMapping("/user/{userId}/address/new")
    public String newUserAddress(@PathVariable String userId, Model model) {

        User user = userService.getUserById(Long.valueOf(userId));
        Address address = new Address();
        address.setUser(user);
        model.addAttribute("address",address);
        return "user/address/addressForm";
    }

    @PostMapping("/user/{userId}/address")
    public String saveOrUpdateAddress(@ModelAttribute("address") Address address) {
//        User user = userService.getUserById(Long.valueOf(userId));
//        address.setUser(user);
        Address newAddress = addressService.saveAddress(address);
        return "redirect:/user/" + newAddress.getUser().getId() + "/addresses";
    }

    @GetMapping("/user/{userId}/address/{addrId}/update")
    public String updateUserAddress(@PathVariable String userId, @PathVariable String addrId, Model model) {
        Address address = addressService.findByUserIdAndAddrId(Long.valueOf(userId),Long.valueOf(addrId));
        model.addAttribute("address", address);
        return "user/address/addressForm";
    }

    @GetMapping("/user/{userId}/address/{addrId}/delete")
    public String deleteUserAddress(@PathVariable String userId, @PathVariable String addrId, Model model) {
        addressService.deleteAddressById(Long.valueOf(addrId));
        return "redirect:/user/" + userId + "/addresses";
    }

}
