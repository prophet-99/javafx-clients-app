package org.prophet99.clientsapp.data.models.dao;

import java.util.List;
import java.util.Optional;

public interface IEntityDAO<T, V> {
  String save(T t);
  String update(V v, T t);
  String delete(V v);
  Optional<T> findById(V v);
  List<T> getAll();
}
