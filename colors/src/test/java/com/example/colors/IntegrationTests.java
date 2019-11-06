package com.example.colors;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import com.example.colors.model.Color;
import com.example.colors.model.to.PersonTO;

@SpringBootTest(classes = ColorsApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "csv.path=src/test/resources/testPersons.csv")
public class IntegrationTests {
  
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;
  
  @BeforeEach
  public void init() {
    TestHelper.initDataInTestPersonsCsvFile(ObjectMother.getDataSetExample1());
  }

  @Test
  public void testGetPersonById() {
    // when
    PersonTO person = this.restTemplate
        .getForObject(createURLWithPort("/persons/1"), PersonTO.class);

    // then
    assertEquals(1L, person.getId().longValue());
    assertEquals("John", person.getName());
  }
  
  @Test
  public void testGetAllPersons() {
    // when
    PersonTO[] persons = this.restTemplate
        .getForObject(createURLWithPort("/persons"), PersonTO[].class);

    // then
    assertEquals(3L, persons.length);
  }
  
  @Test
  public void testGetAllPersonsWithColor() {
    // when
    PersonTO[] persons = this.restTemplate
        .getForObject(createURLWithPort("/persons/color/blue"), PersonTO[].class);

    // then
    assertEquals(1L, persons.length);
    assertEquals(Color.BLUE, ((PersonTO)persons[0]).getColor());
  }

  @Test
  public void testAddPerson() {
    // given
    PersonTO[] personsBefore = this.restTemplate
        .getForObject(createURLWithPort("/persons"), PersonTO[].class);
    assertEquals(3L, personsBefore.length);

    PersonTO addedPerson = ObjectMother.getPersonTOBuilderWithId(null).name("Ciomek").build();
    
    // when
    ResponseEntity<String> responseEntity = this.restTemplate
        .postForEntity(createURLWithPort("/persons"), addedPerson, String.class);

    // then
    assertEquals(200, responseEntity.getStatusCodeValue());
    
    PersonTO[] personsAfter = this.restTemplate
        .getForObject(createURLWithPort("/persons"), PersonTO[].class);
    assertEquals(4L, personsAfter.length);
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}
