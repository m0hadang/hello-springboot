package mycompany.example.hellospringboot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")// prefix 지정
public class UserJpaController {
    @Autowired
    private UserRepository userRepository;

    // http://localhost:8088:/jpa/users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        /**
         * HATEOAS
         * "all-users", SERVER_PATH + "/users"
         * retieveAllUsers
         */
        EntityModel<User> model =  EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = linkTo(
                methodOn(this.getClass()).retrieveAllUsers()// 리플렉션을 사용하여 링크할 메소드 정보 설정
        );
        model.add(
                linkTo.withRel("all-users"));// 이 retieveAllUsers 메소드는 all-users 관련된 메소드이다

        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // 사용자의 post 정보를 조회하는 API
    // /jpa/users/1000/
    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user.get().getPosts();
    }
}
