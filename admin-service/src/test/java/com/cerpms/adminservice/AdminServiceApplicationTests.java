package com.cerpms.adminservice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cerpms.adminservice.entity.Admin;
import com.cerpms.adminservice.repository.AdminRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class AdminServiceApplicationTests {
    @Autowired
    AdminRepository adr;

    @Test
    void AdminData() {
        adr.saveAll(List.of(new Admin("admin@gmail.com", "Admin@123"), new Admin("adarsh@gmail.com", "Adarsh@789")));

    }

}
