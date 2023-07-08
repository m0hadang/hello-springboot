package mycompany.example.hellospringboot.exception;

import mycompany.example.hellospringboot.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice // 모든 Controller가 실행될때 처리하는 Bean
public class CustomizedResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

    // 모든 Controller의 에러가 발생할 경우 처리할 수 있도록 Unhandled Exception Handler 함수 구현

    @ExceptionHandler(Exception.class)// Exception이라는 예외 발생할 경우 처리(Unhandled Exception 처리)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)// UserNotFoundException 에러 발생할 경우 처리
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * 잘못된 인자를 처리하기 위핸 예외 핸들러
     * 잘못된 인자가 들어오면 호출되는 부모 클래스 예외처리 메서드를 오버라이딩
     */
    @Override// 정확한 오버라이드를 위해 정의
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
         ExceptionResponse exceptionResponse =
                 new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
         return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}