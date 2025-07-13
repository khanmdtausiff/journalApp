package net.engineeringdigest.journalApp.scheduler;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.Sentiment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserSchedulerTest {
    @Autowired
    private UserScheduler userScheduler;
    @Autowired
    private MongoTemplate mongoTemplate; // Used to insert test data

    /**
     * Sets up the test environment before each test method.
     * Inserts specific User data with nested JournalEntry data into the test database.
     */
    @BeforeEach
    public void setup() {
        System.out.println("--- Running @BeforeEach setup for UserSchedulerTest ---");

        // 1. Clear collections to ensure a clean state for each test
        mongoTemplate.dropCollection(User.class);
        mongoTemplate.dropCollection(JournalEntry.class); // Explicitly drop JournalEntry collection if @DBRef is used
        System.out.println("Collections 'user' and 'journalEntry' dropped.");

        // --- Create Journal Entries for Firoz and save them FIRST to get their IDs ---
        JournalEntry firozEntry1 = new JournalEntry(null, "Morning", "Morning", LocalDateTime.parse("2025-07-10T00:19:43.718"), Sentiment.HAPPY);
        JournalEntry firozEntry2 = new JournalEntry(null, "Noon", "Noon", LocalDateTime.parse("2025-07-10T00:20:02.506"), Sentiment.HAPPY);
        JournalEntry firozEntry3 = new JournalEntry(null, "Eve", "Eve", LocalDateTime.parse("2025-07-10T00:20:26.406"), Sentiment.SAD);

        // Save each journal entry individually so they get their unique IDs
        mongoTemplate.save(firozEntry1);
        mongoTemplate.save(firozEntry2);
        mongoTemplate.save(firozEntry3);
        System.out.println("Firoz's journal entries saved and have IDs.");


        // --- Create Firoz User and associate the now-saved journal entries ---
        User firozUser = new User();
        firozUser.setUsername("firoz");
        firozUser.setPassword("$2a$10$XXjFTih2KZJ.suxGe6AAd.sosHucztyTP6swC0w5W3qz5sHLNRKp.");
        firozUser.setEmail("khanmdtausiff1@gmail.com");
        firozUser.setSentimentAnalysis(true);
        firozUser.setRoles(Arrays.asList("USER"));
        firozUser.setJournalEntries(Arrays.asList(firozEntry1, firozEntry2, firozEntry3)); // Assign entries with IDs


        // --- Create Journal Entries for Parwez and save them FIRST to get their IDs ---
        JournalEntry parwezEntry1 = new JournalEntry(null, "Subah", "Subah", LocalDateTime.parse("2025-07-10T00:22:41.331"), Sentiment.SAD);
        JournalEntry parwezEntry2 = new JournalEntry(null, "dopeher", "dopeher", LocalDateTime.parse("2025-07-10T00:23:33.899"), null);
        JournalEntry parwezEntry3 = new JournalEntry(null, "raat", "raat", LocalDateTime.parse("2025-07-10T00:23:59.064"), Sentiment.ANXIOUS);

        // Save each journal entry individually so they get their unique IDs
        mongoTemplate.save(parwezEntry1);
        mongoTemplate.save(parwezEntry2);
        mongoTemplate.save(parwezEntry3);
        System.out.println("Parwez's journal entries saved and have IDs.");


        // --- Create Parwez User and associate the now-saved journal entries ---
        User parwezUser = new User();
        parwezUser.setUsername("parwez");
        parwezUser.setPassword("$2a$10$w4YHyapmSDBCBIyPZywdNuf0HusxRfZq7lCdnDzdbS5iphPgiu5EK");
        parwezUser.setEmail("mdfirozk25@gmail.com");
        parwezUser.setSentimentAnalysis(true);
        parwezUser.setRoles(Arrays.asList("USER"));
        parwezUser.setJournalEntries(Arrays.asList(parwezEntry1, parwezEntry2, parwezEntry3)); // Assign entries with IDs

        // --- Save Users to the test database ---
        mongoTemplate.save(firozUser);
        System.out.println("User 'firoz' saved with linked journal entries.");
        mongoTemplate.save(parwezUser);
        System.out.println("User 'parwez' saved with linked journal entries.");

        // (Optional) Verify all users in test DB after setup
        List<User> allUsersAfterSetup = mongoTemplate.findAll(User.class);
        System.out.println("All users in test DB after setup: " + allUsersAfterSetup.size() + " users: " + allUsersAfterSetup);}
    //@Disabled
    @Test
    public void fetchUsersAndSendSaMailTest(){
        userScheduler.fetchUsersAndSendSaMail();
    }
}
