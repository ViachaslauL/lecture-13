package by.itacademy.javaenterprise.lepnikau.app.dao;

import by.itacademy.javaenterprise.lepnikau.app.entity.Parent;

import java.util.List;

public interface ParentDAO {

    Parent save(Parent t);

    Parent get(int id);

    List<Parent> getAllPageByPage(int limit, int offset);

}
