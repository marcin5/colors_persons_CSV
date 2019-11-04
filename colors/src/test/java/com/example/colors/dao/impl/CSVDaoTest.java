package com.example.colors.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.colors.dao.PersonDAO;
import com.example.colors.dao.PersonDaoTest;
import com.example.colors.dao.impl.csv.CsvDao;
import com.example.colors.entity.Person;

@SpringBootTest
public class CSVDaoTest extends PersonDaoTest<PersonDAO<Person>>{

  @Autowired
  private CsvDao csvDao;
  
  @Override
  protected CsvDao createInstance(){ 
      return csvDao; 
  }
}
