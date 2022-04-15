package com.shubham.shoppershub.controller;

import com.shubham.shoppershub.entity.Address;
import com.shubham.shoppershub.entity.User;
import com.shubham.shoppershub.service.AddressService;
import com.shubham.shoppershub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/{userId}")
public class AddressController {
    private final UserService userService;
    private final AddressService addressService;

    public AddressController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public String listUserAddresses(@PathVariable String userId, Model model) {
        model.addAttribute("addressList", addressService.findAllAddressByUserId(Long.valueOf(userId)));
        return "user/address/list";
    }

    @GetMapping("/address/new")
    public String newUserAddress(@PathVariable String userId, Model model) {

        User user = userService.getUserById(Long.valueOf(userId));
        Address address = new Address();
        address.setUser(user);
        model.addAttribute("address",address);
        return "user/address/addressForm";
    }

    @PostMapping("/address")
    public String saveOrUpdateAddress(@ModelAttribute("address") Address address) {
        Address newAddress = addressService.saveAddress(address);
        return "redirect:/user/" + newAddress.getUser().getId() + "/addresses";
    }

    @GetMapping("/address/{addrId}/update")
    public String updateUserAddress(@PathVariable String userId, @PathVariable String addrId, Model model) {
        Address address = addressService.findByUserIdAndAddrId(Long.valueOf(userId),Long.valueOf(addrId));
        model.addAttribute("address", address);
        return "user/address/addressForm";
    }

    @GetMapping("/address/{addrId}/delete")
    public String deleteUserAddress(@PathVariable String userId, @PathVariable String addrId) {
        addressService.deleteAddressById(Long.valueOf(addrId));
        return "redirect:/user/" + userId + "/addresses";
    }

}
