package cloudAppi.userManager;


import cloudAppi.userManager.controller.UserController;
import cloudAppi.userManager.dao.UserRepository;
import cloudAppi.userManager.exception.InvalidInputException;
import cloudAppi.userManager.modelo.Address;
import cloudAppi.userManager.modelo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerMockMvcStandaloneTest {

    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private JacksonTester<User> jsonUser;

    DateTimeFormatter spanishDateFormatter;

    @Before
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());

        spanishDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        mvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new InvalidInputException())
                .build();
    }

    @Test
    public void canCreateWhenCorrectValues() throws Exception{

        //when
        MockHttpServletResponse response = mvc.perform
                (post("/users/createUsers").contentType(MediaType.APPLICATION_JSON).content(
                        jsonUser.write
                            (new User(101,"Pablo","pabloramirez@gimail.com",
                                    LocalDateTime.now(),
                                    new Address(1, "Calle Caballeros", "Comunidad Valenciana",
                                    "Castello de La Plana", "Espana", "1203" )
                            )).getJson()
                )).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonUser.write
                (new User(101,"Pablo","pabloramirez@gimail.com",
                        LocalDateTime.now(),
                        new Address(1, "Calle Caballeros", "Comunidad Valenciana",
                                "Castello de La Plana", "Espana", "1203" )
                )).getJson()
        );

    }





}
