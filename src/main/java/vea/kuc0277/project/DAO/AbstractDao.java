package vea.kuc0277.project.DAO;

import java.util.List;

public interface AbstractDao<T> {
    T save(T entity);
    List<T> getAll();
    T get(int id);
    T update(T entity);
    void delete(int id);
}
