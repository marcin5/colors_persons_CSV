package com.example.colors.dao;

import java.util.List;
import java.util.Optional;
import com.example.colors.model.Color;

public interface PersonDAO<T> {

  Optional<T> findById(long id);

  public List<T> findAll();

  public List<T> findByColor(Color color);

  public T save(T t);
}
