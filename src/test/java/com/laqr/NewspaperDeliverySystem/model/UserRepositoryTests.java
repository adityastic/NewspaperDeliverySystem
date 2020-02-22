package com.laqr.NewspaperDeliverySystem.model;

import com.laqr.NewspaperDeliverySystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertIntoDatabase() {
        User user = new User("aditya", "gupta", UserRole.ADMIN);

        entityManager.persist(user);
        entityManager.flush();

        User foundUser = userRepository.findByUsername(user.getUsername()).get();

        assertThat(foundUser).isEqualTo(user);
    }
}
