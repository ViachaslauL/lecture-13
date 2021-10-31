package by.itacademy.javaenterprise.lepnikau.app.dao;

import by.itacademy.javaenterprise.lepnikau.app.entity.Mark;

import java.util.List;

public interface MarkDAO {

    Mark save(Mark t);

    Mark get(int id);

    List<Mark> getAllPageByPage(int limit, int offset);

}
