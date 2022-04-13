package com.shubham.shoppershub.dao;

import com.shubham.shoppershub.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select a from Address a where a.user.id = ?1")
    public List<Address> findAllByUserId(long id);
}
