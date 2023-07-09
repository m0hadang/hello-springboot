package mycompany.example.hellospringboot.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public User retieveUser(@PathVariable int id /*int 로 컨버팅됨*/) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        // ResponseEntit를 사용하여 API 호출 결과에 대한 적절한 반환을 하도록 처리
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public  void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
}