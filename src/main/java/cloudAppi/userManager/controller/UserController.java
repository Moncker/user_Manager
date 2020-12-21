package cloudAppi.userManager.controller;

import cloudAppi.userManager.dto.UserDto;
import cloudAppi.userManager.exception.InvalidUserIdException;
import cloudAppi.userManager.exception.UserNotFoundException;
import cloudAppi.userManager.modelo.User;
import cloudAppi.userManager.validators.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cloudAppi.userManager.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
//@Api(tags = {"users"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/getusers",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    ResponseEntity<List<UserDto>> getUsers(HttpServletRequest request, HttpServletResponse response){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(200)
                .body(users.stream().map(this::convertToDto)
                .collect(Collectors.toList()));
    }


    @RequestMapping(value = "/createUsers",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto, HttpServletRequest request,
                                                HttpServletResponse response) throws ParseException {

        // Validamos peticion - Si algo incorrecto: EXCEPTION
        UserValidator.validate(dto);

        User user = convertToEntity(dto);
        User userCreated = userService.createUser(user);

        return ResponseEntity.status(201).body(convertToDto(userCreated));
    }


    @RequestMapping(value = "/getusersById/{userId}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId, HttpServletRequest request,
                                                HttpServletResponse response) throws IOException {

        UserValidator.validateId(userId);

        User userFound = this.userService.getUserById(userId);
        if (userFound == null)
            throw new UserNotFoundException("User not found");

        return ResponseEntity.status(200).body(convertToDto(userFound));
    }


    @RequestMapping(value = "/updateUsersById/{userId}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<UserDto> updateUsersById(@PathVariable("userId") Integer userId, @RequestBody UserDto dto,
                                                    HttpServletRequest request, HttpServletResponse response)
                                                    throws ParseException {

        UserValidator.validateId(userId);
        // Validar input
        // UserValidator.validate(dto);

        if (this.userService.getUserById(userId) == null)
            throw new UserNotFoundException("User not found");

        dto.setId(userId);
        User user = convertToEntity(dto);

        return ResponseEntity.status(200).body(convertToDto(this.userService.updateUserById(user)));
    }


    @RequestMapping(value = "/deleteUsersById/{userId}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteUsersById(@PathVariable("userId") Integer userId,
                                HttpServletRequest request, HttpServletResponse response){

        UserValidator.validateId(userId);

        if (this.userService.getUserById(userId) == null)
            throw new UserNotFoundException("User not found");

        this.userService.deleteUserById(userId);
    }


    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        try {
            userDto.setSubmissionDate(user.getBirthDate());
        }
        catch (Exception ex){
            return userDto;
        }
        return userDto;
    }


    private User convertToEntity(UserDto postDto) throws ParseException {
        User user = modelMapper.map(postDto, User.class);
        user.setSubmissionDate(postDto.getBirthDate());

        return user;
        /*
        if (postDto.getId() != null) {
            User oldPost = postDto.getPostById(postDto.getId());
            post.setRedditID(oldPost.getRedditID());
            post.setSent(oldPost.isSent());
        }*/
    }

}
