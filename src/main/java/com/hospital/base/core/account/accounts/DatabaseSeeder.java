//package com.hospital.base.core.account.accounts;
//
//import java.time.LocalDateTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DatabaseSeeder implements CommandLineRunner {
//	@Autowired
// private AccountsRepository accountsRepository;
//    
//    @Override
//    public void run(String... args) throws Exception {
//       String hashedPassword = new BCryptPasswordEncoder().encode("Preethu@777");
//          AccountsEntity accountsEntity=new AccountsEntity();
//          accountsEntity.setUsername("Preethampreethu852@gmail.com");
//          accountsEntity.setPassword(hashedPassword);
//          accountsEntity.setCreatedTime(LocalDateTime.now().toString());
//          accountsEntity.setDisabled(false);
//          accountsEntity.setRole("ROLE_SUPER");
//          accountsRepository.save(accountsEntity);
//          
//           
//
//           
//    }
//}
