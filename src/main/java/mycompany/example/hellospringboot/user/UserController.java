package mycompany.example.hellospringboot.user;

import org.hibernate.EntityMode;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<User> retieveUser(@PathVariable int id /*int 로 컨버팅됨*/) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        /**
         * HATEOAS
         * "all-users", SERVER_PATH + "/users"
         * retieveAllUsers
         */
        EntityModel<User> model =  EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(
                methodOn(this.getClass()).retieveAllUsers()// 리플렉션을 사용하여 링크할 메소드 정보 설정
        );
        model.add(
                linkTo.withRel("all-users"));// 이 retieveAllUsers 메소드는 all-users 관련된 메소드이다
        return model;
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