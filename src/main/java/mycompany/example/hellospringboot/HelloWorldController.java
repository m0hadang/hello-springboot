package mycompany.example.hellospringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    /**
     * GET
     * /hello-world (endpoint)
     * @RequestMapping(method=RequestMethod.GET, path="/hello-world")
     */
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        /*

         */
        /**
         * 스프링 프레임워크에서는 객체를 반환할 경우 JSON 형태로 변환해서 반환
         * 라이브러리를 추가하면 XML로 변경도 가능
         */
        return new HelloWorldBean("Hello World");
    }
}
