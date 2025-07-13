package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserRepositoryImplTest {
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private MongoTemplate mongoTemplate;
    @BeforeEach
    public void setup() {
        System.out.println("--- Running @BeforeEach setup ---");

        // 1. Clean up the collection before each test to ensure test isolation
        mongoTemplate.dropCollection(User.class);
        System.out.println("Collection 'user' dropped.");

        // 2. Insert test data for testgetUserForSentimentAnanlysis()
        User akshitUser = new User();
        akshitUser.setUsername("akshit");
        akshitUser.setPassword("pass1"); // Placeholder
        akshitUser.setEmail("something@gmail.com");
        akshitUser.setSentimentAnalysis(true);
        akshitUser.setRoles(Arrays.asList("USER"));
        mongoTemplate.save(akshitUser);
        System.out.println("Test user 'akshit' saved.");

        // 3. Insert test data for testUserForSA()
        User vipulUser = new User();
        vipulUser.setUsername("vipul");
        vipulUser.setPassword("pass2"); // Placeholder
        vipulUser.setEmail("vipul@example.com"); // Example email for vipul
        vipulUser.setSentimentAnalysis(false); // This can be true/false, doesn't affect getUserForSA()
        vipulUser.setRoles(Arrays.asList("USER"));
        mongoTemplate.save(vipulUser);
        System.out.println("Test user 'vipul' saved.");

        // (Optional) Insert other users that should be excluded by various queries
        User excludedUser = new User();
        excludedUser.setUsername("excluded");
        excludedUser.setPassword("pass3");
        excludedUser.setEmail("excluded@example.com");
        excludedUser.setSentimentAnalysis(false); // Excluded by sentimentAnalysis query
        excludedUser.setRoles(Arrays.asList("USER"));
        mongoTemplate.save(excludedUser);
        System.out.println("Excluded user saved.");

        System.out.println("Users in test DB after setup: " + mongoTemplate.findAll(User.class));
    }
    @Disabled("tested")
    @Test
    public void testgetUserForSA(){
        Assertions.assertNotNull(userRepositoryImpl.getUserForSA());
    }
}

