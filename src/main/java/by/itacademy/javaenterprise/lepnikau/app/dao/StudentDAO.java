package by.itacademy.javaenterprise.lepnikau.app.dao;

import by.itacademy.javaenterprise.lepnikau.app.entity.Parent;
import by.itacademy.javaenterprise.lepnikau.app.entity.Student;

import java.util.List;

public interface StudentDAO {

    boolean saveStudentAndParent(Student student, Parent parent);

    Student save(Student t);

    Student get(int id);

    List<Student> getAllPageByPage(int limit, int offset);

}
