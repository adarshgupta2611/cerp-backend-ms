package com.cerpms.adminservice.service;

import com.cerpms.adminservice.entity.Admin;
import com.cerpms.adminservice.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Optional<Admin> authenticateAdmin(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email).orElseThrow(()-> new NoSuchElementException());
    }
}