package blockchainstudy.example.tastyload.User;

import blockchainstudy.example.tastyload.user.User;
import blockchainstudy.example.tastyload.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void Builder() {
        User expected = User.builder()
                .userId("tmddn642")
                .userName("유승우3")
                .userPw("Asdfqewr!")
                .email("tmddn6451@naver.com")
                .joinedDate(new Date(2012,10,12))
                .build();

        userRepository.save(expected);

        User source = userRepository.findByUserId("tmddn642")
                .orElseThrow(()->new NullPointerException("me"));

        Assertions.assertEquals(source,expected);
    }

    @Test
    void find() {
        User user = userRepository.findByUserId("tmddn645")
                .orElseThrow(()->new NullPointerException("me"));

        System.out.println(user);
    }
}