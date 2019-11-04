package com.example.colors.dao.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.colors.dao.PersonDAO;
import com.example.colors.dao.PersonDaoTest;
import com.example.colors.dao.impl.repository.PersonRepository;
import com.example.colors.entity.Person;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest extends PersonDaoTest<PersonDAO<Person>>{

  @Autowired
  private PersonRepository personRepository;
  
  @Override
  protected PersonRepository createInstance(){ 
      return personRepository; 
  }
}
