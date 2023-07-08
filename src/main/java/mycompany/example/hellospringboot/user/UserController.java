package mycompany.example.hellospringboot.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;
    public UserController(UserDaoService service) {
        this.service = service;
    }
    @GetMapping("/users")
    public List<User> retieveAllUsers() {
        return service.findAll();
    }

    // GET /users/1 or /users/10 -> String 이다
    @GetMapping("/users/{id}")
    public User retieveAllUser(@PathVariable int id /*int 로 컨버팅됨*/) {
        return service.findOne(id);
    }

    @PostMapping("/users")
    public  void createUser(@RequestBody User user) {
        User savedUser = service.save(user);
    }
}