package mycompany.example.hellospringboot.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    // User : Post -> 1 : (0~N), Main : Sub -> Parent : Child
    @ManyToOne(fetch = FetchType.LAZY) // User 테이블과 N:1 관계 설정
    // LAZY : 필요한 시점에 user 데이터 가져올 것이다
    @JsonIgnore// 데이터를 표시하지는 않을 것이다
    private User user;
}
