package cloudAppi.userManager.exception;

import cloudAppi.userManager.modelo.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){super();}

    public UserNotFoundException(String message) { super(message);}

}
