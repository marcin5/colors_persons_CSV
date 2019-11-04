package com.example.colors.dao.impl.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.colors.dao.PersonDAO;
import com.example.colors.entity.Person;
import com.example.colors.model.Color;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>, PersonDAO<Person>{
  
  public List<Person> findAll();
  public List<Person> findByColor(Color color);
}
