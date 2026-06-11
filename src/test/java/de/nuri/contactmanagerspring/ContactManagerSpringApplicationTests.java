package de.nuri.contactmanagerspring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ContactManagerSpringApplicationTests {
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void shouldReturnContacts() throws Exception {
		mockMvc.perform(get("/contacts"))
		       .andExpect(status().isOk());
	}
	
	@Test
	void shouldReturnJson() throws Exception {
		mockMvc.perform(get("/contacts"))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType("application/json"));
	}
	
	@Test
	void shouldCreateContact() throws Exception {
		
		String json = """
		              {
		                  "name":"Nuri",
		                  "email":"nuri@test.de",
		                  "phoneNumber":"123456789"
		              }
		              """;
		
		mockMvc.perform(
				post("/contacts")
						.contentType("application/json")
						.content(json)
		)
		       .andExpect(status().isOk());
	}
	
	@Test
	void shouldRejectInvalidEmail() throws Exception {
		
		String json = """
		              {
		                  "name":"Nuri",
		                  "email":"abc",
		                  "phoneNumber":"123456789"
		              }
		              """;
		
		mockMvc.perform(
				post("/contacts")
						.contentType("application/json")
						.content(json)
		)
		       .andExpect(status().isBadRequest());
	}
	
	@Test
	void shouldGetContactById() throws Exception {
		String json = """
		              {
		                  "name":"Nuri",
		                  "email":"nuri@test.de",
		                  "phoneNumber":"123456789"
		              }
		              """;
		
		String responseBody = mockMvc.perform(
				post("/contacts")
						.contentType("application/json")
						.content(json)
		)
		                             .andExpect(status().isOk())
		                             .andReturn()
		                             .getResponse()
		                             .getContentAsString();
		
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		int      id       = jsonNode.get("id").asInt();
		
		mockMvc.perform(get("/contacts/" + id))
		       .andExpect(status().isOk());
	}
	
	@Test
	void shouldDeleteContact() throws Exception {
		String json = """
		              {
		                  "name":"Nuri",
		                  "email":"nuri@test.de",
		                  "phoneNumber":"123456789"
		              }
		              """;
		
		String responseBody = mockMvc.perform(
				post("/contacts")
						.contentType("application/json")
						.content(json)
		)
		                             .andExpect(status().isOk())
		                             .andReturn()
		                             .getResponse()
		                             .getContentAsString();
		
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		int      id       = jsonNode.get("id").asInt();
		
		mockMvc.perform(delete("/contacts/" + id))
		       .andExpect(status().isOk());
		
		mockMvc.perform(get("/contacts/" + id))
		       .andExpect(status().isNotFound());
	}
	
	@Test
	void shouldUpdateContact() throws Exception {
		String createJson = """
		                    {
		                        "name":"Nuri",
		                        "email":"nuri@test.de",
		                        "phoneNumber":"123456789"
		                    }
		                    """;
		
		String responseBody = mockMvc.perform(
				post("/contacts")
						.contentType("application/json")
						.content(createJson)
		)
		                             .andExpect(status().isOk())
		                             .andReturn()
		                             .getResponse()
		                             .getContentAsString();
		
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		int      id       = jsonNode.get("id").asInt();
		
		String updateJson = """
		                    {
		                        "name":"Nuri Updated",
		                        "email":"updated@test.de",
		                        "phoneNumber":"987654321"
		                    }
		                    """;
		
		mockMvc.perform(
				put("/contacts/" + id)
						.contentType("application/json")
						.content(updateJson)
		)
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.name").value("Nuri Updated"))
		       .andExpect(jsonPath("$.email").value("updated@test.de"))
		       .andExpect(jsonPath("$.phoneNumber").value("987654321"));
	}
	
	@Test
	void shouldToggleFavorite() throws Exception {
		String createJson = """
            {
                "name":"Nuri",
                "email":"nuri@test.de",
                "phoneNumber":"123456789"
            }
            """;
		
		String responseBody = mockMvc.perform(
				post("/contacts")
						.contentType("application/json")
						.content(createJson)
		)
		                             .andExpect(status().isOk())
		                             .andExpect(jsonPath("$.favorite").value(false))
		                             .andReturn()
		                             .getResponse()
		                             .getContentAsString();
		
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		int id = jsonNode.get("id").asInt();
		
		mockMvc.perform(patch("/contacts/" + id + "/favorite"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.favorite").value(true));
		
		mockMvc.perform(patch("/contacts/" + id + "/favorite"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.favorite").value(false));
	}
	
}
