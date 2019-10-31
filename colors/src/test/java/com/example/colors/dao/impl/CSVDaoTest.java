package com.example.colors.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.colors.dao.PersonDAO;
import com.example.colors.dao.PersonDaoTest;
import com.example.colors.jpa.Person;

@SpringBootTest
public class CSVDaoTest extends PersonDaoTest<PersonDAO<Person>>{

  @Autowired
  private CSVDao csvDao;
  
  @Override
  protected CSVDao createInstance(){ 
      return csvDao; 
  }
}
