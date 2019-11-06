package com.example.colors.controller;


import static com.example.colors.ObjectMother.getPersonTOBuilderWithId;
import static com.example.colors.ObjectMother.getPersonTOWithId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.example.colors.exceptions.NoColorFromStringException;
import com.example.colors.exceptions.NoPersonFoundException;
import com.example.colors.model.Color;
import com.example.colors.model.to.PersonTO;
import com.example.colors.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        .thenReturn(getPersonsTO());

    // when // then
    this.mockMvc.perform(get("/persons"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name", is("John")))
        .andExpect(jsonPath("$[1].name", is("Mark")));
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
        .thenReturn(getPersonsTO());

    // when
    this.mockMvc.perform(get("/persons/color/blue"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name", is("John")))
        .andExpect(jsonPath("$[0].color", is("blue")));
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

  private List<PersonTO> getPersonsTO() {
    return Arrays.asList(
        getPersonTOBuilderWithId(1L).name("John")
        .color(Color.BLUE).build(),
        getPersonTOBuilderWithId(2L).name("Mark")
        .color(Color.GREEN).build());}
}
