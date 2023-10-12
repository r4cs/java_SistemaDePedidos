package org.example.repositories;

import java.util.List;

public interface Repository<T> {
    public List<T> getAll();
    public T findById(Object id);
    public void create(T entity);
    public void update(T entity);
    public void deleteById(Object id);
}
