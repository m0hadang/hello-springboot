package mycompany.example.hellospringboot.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
        users.add(new User(1, "Kim", new Date(), "pass1", "701010-111111"));
        users.add(new User(2, "Lee", new Date(), "pass2", "701010-222222"));
        users.add(new User(3, "Park", new Date(), "pass3", "701010-333333"));
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
    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
