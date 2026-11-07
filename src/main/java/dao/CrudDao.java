package dao;

import java.util.List;

public interface CrudDao<T> {
    boolean insert(T data);
    boolean update(T data);
    boolean delete(int id);
    T getById(int id);
    List<T> getAll();
}