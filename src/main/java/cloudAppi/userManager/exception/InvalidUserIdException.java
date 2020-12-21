package cloudAppi.userManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason= "Invalid user id", value = HttpStatus.BAD_REQUEST)
public final class InvalidUserIdException extends RuntimeException{

    public InvalidUserIdException(){super();}

    public InvalidUserIdException(String message) { super(message);}



}
