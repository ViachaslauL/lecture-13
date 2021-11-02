package by.itacademy.javaenterprise.lepnikau.app.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Mark{
    private int id;
    private int studentId;
    private int mark;
    private int subjectId;
    private Date date;
}
