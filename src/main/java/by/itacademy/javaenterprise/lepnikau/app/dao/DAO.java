package by.itacademy.javaenterprise.lepnikau.app.dao;

import java.util.List;

public interface DAO<T> {

    T save(T t);

    T get(int id);

    List<T> getAllPageByPage(int limit, int offset);

}
