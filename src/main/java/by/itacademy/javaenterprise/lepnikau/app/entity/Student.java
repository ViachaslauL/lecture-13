package by.itacademy.javaenterprise.lepnikau.app.entity;

import lombok.Data;

@Data
public class Student{
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private int classId;
}
