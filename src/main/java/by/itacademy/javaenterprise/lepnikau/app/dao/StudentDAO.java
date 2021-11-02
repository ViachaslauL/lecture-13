package by.itacademy.javaenterprise.lepnikau.app.dao;

import by.itacademy.javaenterprise.lepnikau.app.entity.Student;

import java.util.List;

public interface StudentDAO {

    Student save(Student t);

    Student get(int id);

    List<Student> getAllPageByPage(int limit, int offset);

}
