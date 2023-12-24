package com.cerpms.adminservice.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerpms.adminservice.entity.Admin;
import com.cerpms.adminservice.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody @Valid Admin admindto) {
        Optional<Admin> admin = adminService.authenticateAdmin(admindto.getEmail(), admindto.getPassword());
        return admin.isPresent() ? ResponseEntity.ok(Map.of("id", admin.get().getId()))
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}