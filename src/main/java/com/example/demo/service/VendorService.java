package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;

import java.util.List;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
