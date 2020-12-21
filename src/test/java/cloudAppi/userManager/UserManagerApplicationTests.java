package cloudAppi.userManager;

import cloudAppi.userManager.controller.UserController;
import cloudAppi.userManager.modelo.User;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserManagerApplication.class)
@ActiveProfiles("test")
public class UserManagerApplicationTests {

	private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/users/createUsers";

	@InjectMocks
	UserController controller;

	@Autowired
	WebApplicationContext context;

	private MockMvc mvc;


	@Before
	public void initTests() {
		MockitoAnnotations.openMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void shouldCreateRetrieveDelete() throws Exception {

		User u1 = mockUser("shouldCreateRetrieveDelete");
		byte[] u1Json = toJson(u1);

		//CREATE
		MvcResult result = mvc.perform(post("/users/createUsers")
				.content(u1Json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();
/*
		//GET
		mvc.perform(get("/example/v1/hotels/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is((int) id)))
				.andExpect(jsonPath("$.name", is(r1.getName())))
				.andExpect(jsonPath("$.city", is(r1.getCity())))
				.andExpect(jsonPath("$.description", is(r1.getDescription())))
				.andExpect(jsonPath("$.rating", is(r1.getRating())));

		//DELETE
		mvc.perform(delete("/example/v1/hotels/" + id))
				.andExpect(status().isNoContent());

		//RETRIEVE should fail
		mvc.perform(get("/example/v1/hotels/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

		//todo: you can test the 404 error body too.


JSONAssert.assertEquals(
  "{foo: 'bar', baz: 'qux'}",
  JSONObject.fromObject("{foo: 'bar', baz: 'xyzzy'}"));
 */
		//long id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());
	}



	private User mockUser(String prefix) {
		User u = new User();
		u.setId(new Random().nextInt(10));
		u.setName(prefix + "_name");

		//u.setAddress(prefix + "_address");
		//u.setEmail();
		//u.setBirthDate();

		return u;
	}

	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r).getBytes();
	}


}
