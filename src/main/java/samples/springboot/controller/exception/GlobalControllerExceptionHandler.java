package samples.springboot.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by izeye on 14. 12. 9..
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

  @ExceptionHandler(Exception1.class)
  public void handleException1(Exception1 ex) {
    System.out.println(ex);
  }

  @ExceptionHandler(Exception2.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Custom reason.")
  public void handleException2(Exception2 ex) {
    System.out.println(ex);
  }

  @ExceptionHandler(Exception3.class)
  public void handleException3(Exception3 ex, HttpServletResponse response) throws IOException {
    System.out.println(ex);

    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Custom message.");
  }

}
