package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Disabled
    @Test
    public void sendMailTest(){
        emailService.sendMail("khanmdtausiff1@gmail.com", "Testing java mail sender", "Hey, aap kaise hain?");
    }
}
