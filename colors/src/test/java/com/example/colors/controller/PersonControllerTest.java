package com.example.colors.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.example.colors.ObjectMother;
import com.example.colors.model.PersonDTO;
import com.example.colors.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  @Mock
  private PersonService personService;

  @Test
  public void shouldReturnAllPersons() throws Exception {
    //given
    when(personService.getAllPersons()).thenReturn(getPersons());
    
    //when
    ResultActions resultActions = this.mockMvc.perform(get("/persons"))
        .andExpect(status().isOk());
    
    //then
    //MvcResult g = resultActions.andReturn();
  }
  
  private List<PersonDTO> getPersons(){
    return Arrays.asList(ObjectMother.getDefaultPerson());
  }
}
