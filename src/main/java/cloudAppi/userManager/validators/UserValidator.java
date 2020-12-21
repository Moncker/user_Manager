package cloudAppi.userManager.validators;

import cloudAppi.userManager.dto.UserDto;
import cloudAppi.userManager.exception.InvalidInputException;
import cloudAppi.userManager.exception.InvalidUserIdException;


public class UserValidator {



    public static void validate(UserDto dto){
        if ( !(dto.getEmail().contains("@")) || (dto.getName().equals("")) || dto.getEmail().length() < 8)
            throw new InvalidInputException("Invalid input");
    }


    public static void validateId(Integer userId) {
        if (userId < 10)
            throw new InvalidUserIdException("Invalid user id");
    }
}
