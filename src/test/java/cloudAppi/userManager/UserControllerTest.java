package cloudAppi.userManager;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import cloudAppi.userManager.controller.UserController;
import cloudAppi.userManager.dao.UserRepository;
import cloudAppi.userManager.dto.AddressDto;
import cloudAppi.userManager.dto.UserDto;
import cloudAppi.userManager.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MimeTypeUtils;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
//@DataJpaTest
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void getAllUsersIsEmpty() throws Exception{
        // Given

        //When
        final ResultActions result = mvc.perform(get("/users/getusers")
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.length()").value(0));
    }


    @Test
    public void createUserValid_shoudReturnCreated() throws Exception {
        // Given
        UserDto dto = new UserDto(null, "Juan Montoliu", "juanmontoliu@gmail.com", "12/12/1996 10:11",
                new AddressDto(null, "Calle Caballeros", "Comunidad Valenciana", "Valencia",
                        "Espana", "46001"));

        // When
        final ResultActions result = mvc.perform(MockMvcRequestBuilders

                .post("/users/createUsers")
                .content(new ObjectMapper().writeValueAsString((dto)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        //Then
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.length()").value(1));

    }



}
