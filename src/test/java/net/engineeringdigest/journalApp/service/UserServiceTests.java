package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("dev")
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Disabled
    @Test
    public void findByUserNameTest(){
        assertNotNull(userRepository.findByUsername("ram"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,5,7",
            "10,3,13"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "vipul"
    })
    public void findByUserNameTest(String name){
        assertNotNull(userRepository.findByUsername(name));
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void findByUsersNameTest(User user){
        assertNotNull(userService.saveNewUser(user));
    }

}

