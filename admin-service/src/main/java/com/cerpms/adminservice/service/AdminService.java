package com.cerpms.adminservice.service;

import com.cerpms.adminservice.entity.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> authenticateAdmin(String email, String password);
    Admin findByEmail(String email);
}