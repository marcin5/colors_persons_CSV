package com.example.colors.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.colors.ObjectMother;
import com.example.colors.entity.Person;
import com.example.colors.model.Color;

public abstract class PersonDaoTest<T extends PersonDAO<Person>> {
  
  @Autowired
  private T dao;
  
  protected abstract T createInstance();
  
  @Test
  public void schouldFindAll(){
    //when
    List<Person> persons = this.dao.findAll();
    
    //then
    assertThat(persons.size()).isEqualTo(2);
  }
  
  @Test
  public void schouldFindById(){
    //when
    Optional<Person> person = this.dao.findById(1L);
    
    //then
    assertTrue(person.isPresent());
    assertThat(person.get().getLastname()).isEqualTo("Müller");
  }
  
  @Test
  public void schouldFindByColor(){
    //when
    List<Person> persons = this.dao.findByColor(Color.PURPLE);
    
    //then
    assertThat(persons.size()).isEqualTo(1);
  }
  
  @Test
  public void schouldAddPerson(){
    // given
    //assertTrue(this.dao.findAll().size() == 2);
    
    //when
    this.dao.save(ObjectMother.getPersonWithId(3L));
    
    //then
    assertTrue(this.dao.findAll().size() == 3L);
  }
}
