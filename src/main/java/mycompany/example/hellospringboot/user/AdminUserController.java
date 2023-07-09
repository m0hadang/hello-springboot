package mycompany.example.hellospringboot.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
// !!! 관리자를 나타내는 path를 그대로 노출하는 것은 보안상 좋지 않은 방법이다
@RequestMapping("/admin")// 클래스에 사용하고 있는 Mapping API 앞에 /admin 을 추가한다
public class AdminUserController {
    private UserDaoService service;
    public AdminUserController(UserDaoService service) {
        this.service = service;
    }
    @GetMapping("/users")
    public MappingJacksonValue retieveAllUsers() {
        List<User> users = service.findAll();

        // 사용필 필드 지정
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        // User에 정의한 필터 사용
        FilterProvider filters =
                new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

    // GET /users/1 or /users/10 -> String 이다
    @GetMapping("/users/{id}")
    public MappingJacksonValue retieveAllUser(@PathVariable int id /*int 로 컨버팅됨*/) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // 사용필 필드 지정
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        // User에 정의한 필터 사용
        FilterProvider filters =
                new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }
}