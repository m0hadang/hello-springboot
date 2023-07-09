package mycompany.example.hellospringboot.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity// DB Row와 매핑할 클래스
public class User {
    @Id// Primary Key
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요")
    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    private String name;
    @Past
    @ApiModelProperty(notes = "사용자 등록일을 입력해 주세요.")
    private Date joinDate;
    /**
     * 노출되면 안되는 중요한 데이터
     */
    @ApiModelProperty(notes = "사용자 비밀번호를 입력해 주세요.")
    private String password;
    @ApiModelProperty(notes = "사용자 주민번호를 입력해 주세요.")
    private String ssn;

    @OneToMany(mappedBy = "user")// User 입장에서 1:N 관계 설정
    private List<Post> posts;

    public User(Integer id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
