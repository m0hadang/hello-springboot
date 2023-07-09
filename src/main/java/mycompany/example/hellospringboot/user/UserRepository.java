package mycompany.example.hellospringboot.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository는 이렇게 선언만 하여도 내부적으로 필요한 것은 구현된다
 * UserRepository는 JpaRepository안에 선언된 메서드르 사용 가능하다
 * UserRepository 안에서 추가적인 메서드 추가나 재정의 가능
 */
@Repository// DB과 관련된 Bean
public interface UserRepository
        extends JpaRepository<User /*User 타입을 다룰 것이다*/, Integer /*User의 기본키 타입*/ > {
}