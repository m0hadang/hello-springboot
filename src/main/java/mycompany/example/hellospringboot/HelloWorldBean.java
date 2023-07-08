package mycompany.example.hellospringboot;
// lombok

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok 어노테이션
@Data // 접근자
@AllArgsConstructor // 필드를 초기화 하는 생성자
@NoArgsConstructor // 인자 없는 생성자
public class HelloWorldBean {
    private String message;
    /**
     * @Data
     *
     * bean 클래스를 만들 경우 필드에 대한 접근자를 만들어 주어야 하지만
     * lombok을 사용할 경우 알아서 생성해준다
     * public String getMessage() {
     *     return this.message;
     * }
     * public void setMessage(String message) {
     *     this.message = msg;
     * }
     */

    /**
     * @AllArgsConstructor
     *
     * public HelloWorldBean(String message) {
     *     this.message = message;
     * }
     */
}
