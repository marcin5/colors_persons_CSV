package com.example.colors.controller;


import static com.example.colors.ObjectMother.getPersonTOWithId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.example.colors.exceptions.NoColorFromStringException;
import com.example.colors.exceptions.NoPersonFoundException;
import com.example.colors.model.Color;
import com.example.colors.model.to.PersonTO;
import com.example.colors.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonService personService;

  private ObjectMapper mapper = new ObjectMapper();

  @Test
  public void shouldReturnAllPersons() throws Exception {
    // given
    when(this.personService.getAllPersons())
        .thenReturn(getPersonsDTO());

    // when
    MvcResult result = this.mockMvc.perform(get("/persons"))
        .andExpect(status().isOk())
        .andReturn();

    // then
    String content = result.getResponse().getContentAsString();
    List<PersonTO> persons = convertJsonToObject(content);
    assertTrue(persons.size() == 2);
  }

  @Test
  public void shouldReturnPerson() throws Exception {
    // given
    when(this.personService.getPersonById(1))
        .thenReturn(getPersonTOWithId(1L));

    // when
    this.mockMvc.perform(get("/persons/1"))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnNotFound() throws Exception {
    // given
    long personId = 1L;
    when(this.personService.getPersonById(personId))
        .thenThrow(new NoPersonFoundException(personId));

    // when
    MvcResult result = this.mockMvc.perform(get("/persons/1"))
        .andExpect(status().isNotFound())
        .andReturn();

    // then
    String content = result.getResponse().getContentAsString();
    assertThat(content).isEqualTo(NoPersonFoundException.MESSAGE + personId);
  }

  @Test
  public void shouldReturnPersonsWithColor() throws Exception {
    // given
    when(this.personService.getAllByColor(Color.BLUE))
        .thenReturn(getPersonsDTO());

    // when
    MvcResult result = this.mockMvc.perform(get("/persons/color/blue"))
        .andExpect(status().isOk())
        .andReturn();

    // then
    String content = result.getResponse().getContentAsString();
    List<PersonTO> persons = convertJsonToObject(content);
    assertTrue(persons.size() == 2L);
  }

  @Test
  public void shouldReturnBadRequestWhenNoColor() throws Exception {
    // when
    MvcResult result = this.mockMvc.perform(get("/persons/color/blueXXX"))
        .andExpect(status().isBadRequest())
        .andReturn();

    // then
    String content = result.getResponse().getContentAsString();
    assertThat(content).isEqualTo(NoColorFromStringException.MESSAGE + "blueXXX");
  }

  @Test
  public void shouldAddPerson() throws Exception {
    // given
    String json = this.mapper.writeValueAsString(getPersonTOWithId(1L));
    
    // when
    this.mockMvc.perform(post("/persons")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk());
  }

  private List<PersonTO> convertJsonToObject(String content) {
    List<PersonTO> persons = null;
    try {
      persons = this.mapper.readValue(content, new TypeReference<List<PersonTO>>() {});
    } catch (JsonProcessingException e) {
      Assert.fail("Fail to parse json");
    }
    return persons;
  }

  private List<PersonTO> getPersonsDTO() {
    return Arrays.asList(getPersonTOWithId(1L), getPersonTOWithId(2L));
  }
}
