package mycompany.example.hellospringboot.user;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 스프링이 이 객체를 찾아서 의존성 주입하기 위해서는 이 클래스가
 * 어떤 용도로 만든 것인지 알려주어야 한다
 * 이를 위해 어노테이션을 지정할 필요 있다
 */
@Service
//@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;
    static {
        users.add(new User(1, "Kim", new Date()));
        users.add(new User(2, "Lee", new Date()));
        users.add(new User(3, "Park", new Date()));
    }
    public List<User> findAll() {
        return users;
    }
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }
    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
