package com.example.colors.dao.impl.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.colors.dao.PersonDAO;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;

@Repository
public interface PersonRepository extends CrudRepository<PersonEty, Long>, PersonDAO<PersonEty> {

  public List<PersonEty> findAll();

  public List<PersonEty> findByColor(Color color);
}
