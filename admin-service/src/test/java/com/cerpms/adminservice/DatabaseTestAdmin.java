package com.cerpms.adminservice;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DatabaseTestAdmin {
//    @Autowired
//    AdminRepository adr;
//
//    @Test
//    void AdminData() {
//        adr.saveAll(List.of(new Admin("admin@gmail.com", "Admin@123"), new Admin("adarsh@gmail.com", "Adarsh@789")));
//
//    }

}