package cloudAppi.userManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class InvalidInputException extends RuntimeException{
    public InvalidInputException(){super();}

    public InvalidInputException(String message) { super(message);}



}
