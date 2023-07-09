package mycompany.example.hellospringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration// Bean 등록
@EnableSwagger2// 용도
public class SwaggerConfig {
    @Bean
    public Docket api() {// 문서 반환
        return new Docket(DocumentationType.SWAGGER_2);
    }
}
