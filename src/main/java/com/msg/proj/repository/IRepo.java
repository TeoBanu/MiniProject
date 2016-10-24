package com.msg.proj.repository;

import java.util.List;

public interface IRepo<T, K> {
    void create(T entity);

    T read(Class<T> clazz, K id);

    void update(T entity);

    void delete(T entity);

    List<T> readAll(Class<T> clazz);

    void closeEm();

}
