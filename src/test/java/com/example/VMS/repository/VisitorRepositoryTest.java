package com.example.VMS.repository;

import com.example.VMS.model.Visitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VisitorRepositoryTest{
    @Autowired
    private VisitorRepository visitorRepository;

    @Test
    void testExistsByEmailTrue() {

        String existingEmail = "existing@example.com";
        visitorRepository.save(new Visitor(1, "Kevin snow", existingEmail, "Male", "Engineer"));

        boolean exists = visitorRepository.existsByEmail(existingEmail);
        // Assert
        assertThat(exists).isTrue();
    }

    @Test
    void testExistsByEmailFalse() {

        String nonExistingEmail = "fakemail@example.com";

        boolean exists = visitorRepository.existsByEmail(nonExistingEmail);
        // Assert
        assertThat(exists).isFalse();
    }
}
