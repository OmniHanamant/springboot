package com.omnireach.springboot;

import com.omnireach.springboot.entity.UserEntity;
import com.omnireach.springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest() {
        UserEntity entity = new UserEntity();
        entity.setFirstName("Hanamant");
        entity.setLastName("Choori");
        entity.setEmail("Hanamant.choori@omnireach.app");
        entity.setPassword("Hanamant123@");
        UserEntity save = userRepository.save(entity);

    }

    @Test
    public void testAndFindAll() {
        List<UserEntity> entities = userRepository.findAll();
        for (UserEntity user : entities) {
            System.out.println("Finding the All data from Database" + " " + user);
        }
    }

    @Test
    public void testAndUpdate() {
        Integer id = 1;
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        UserEntity entity = optionalUser.get();
        entity.setPassword("Choori123@");
        UserEntity save = userRepository.save(entity);
        System.out.println(save);
    }

    @Test
    public void testAndFindById() {
        Integer id = 2;
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        System.out.println(optionalUser);
    }
    @Test
    public void testAndDeleteById(){
        Integer id=1;
        userRepository.deleteById(id);
    }
}
