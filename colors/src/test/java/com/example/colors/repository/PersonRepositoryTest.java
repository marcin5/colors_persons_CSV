package com.example.colors.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.colors.jpa.Person;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepository;
  
  @Test
  public void schouldFindAll() throws Exception {
    //when
    List<Person> persons = this.personRepository.findAll();
    
    //then
    assertThat(persons.size()).isEqualTo(2);
  }
  
  @Test
  public void schouldFindById() throws Exception {
    //when
    Optional<Person> person = this.personRepository.findById(1L);
    
    //then
    assertThat(person.isPresent()).isTrue();
    assertThat(person.get().getName()).isEqualTo(2);
  }
  
  @Test
  public void schould() throws Exception {
    //when
    List<Person> persons = this.personRepository.findAll();
    
    //then
    assertThat(persons.size()).isEqualTo(2);
  }
}
