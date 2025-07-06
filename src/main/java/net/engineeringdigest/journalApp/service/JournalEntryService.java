package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    public JournalEntry saveEntry(JournalEntry journalEntry){
        journalEntry.setDateTime(LocalDateTime.now());
        return journalEntryRepository.save(journalEntry);
    }
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        try {
            User userInDb = userService.findByUserName(username);
            journalEntry.setDateTime(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            userInDb.getJournalEntries().add(saved);
            userService.saveUser(userInDb);
        }catch (Exception e){
            throw new RuntimeException("An error occurred while saving entry : ", e);
        }
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getEntryById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    @Transactional
    public Boolean deleteById(ObjectId id, String username){
        boolean removed = false;
        try {
            User userInDb = userService.findByUserName(username);
            removed = userInDb.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveUser(userInDb);
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception e){
            throw new RuntimeException("An error occurred while deleting entry:", e);
        }
        return removed;
    }
}
