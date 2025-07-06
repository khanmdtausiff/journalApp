package net.engineeringdigest.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class User {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true) //it makes searching by username faster in db, also username will be unique, so a duplicate username can't be created, also we need to enable indexing in application.properties file
    private String username;
    @NonNull
    private String password;
    @DBRef //annotation from mongodb that creates link between journalEntry and Users
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles;

}
