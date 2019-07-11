package com.jimmyyouhei.student.mysql;

import com.jimmyyouhei.student.mysql.entity.Student;
import com.jimmyyouhei.student.mysql.util.DatabaseConnectionManager;
import com.jimmyyouhei.student.mysql.util.StudentDAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

public class Executor {

    private static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS StudentManagement";
    private static final String CREATE_STUDENT_TABLE = "CREATE TABLE IF NOT EXISTS Student (\n" +
            "                        StudentId BIGINT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY, \n" +
            "                        StudentName VARCHAR(256) NOT NULL,\n" +
            "                       StudentBirthDate DATE NOT NULL,\n" +
            "                      StudentAddress VARCHAR(256),\n" +
            "                       StudentMobile VARCHAR(256),\n" +
            "                      StudentEmail VARCHAR(256),\n" +
            "                       StudentEnrollmentDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)";
    private static final String CREATE_CLASS_TABLE = "CREATE TABLE IF NOT EXISTS Class (\n" +
            "\tClassId VARCHAR(100) PRIMARY KEY NOT NULL,\n" +
            "    ClassName VARCHAR(100) NOT NULL,\n" +
            "    Description VARCHAR (10000) NOT NULL,\n" +
            "    ClassStart DATE NOT NULL,\n" +
            "    ClassEnd DATE NOT NULL)";

    private static final String CREATE_STUDENT_CLASS_TABLE = "CREATE TABLE IF NOT EXISTS StudentClass (\n" +
            "\tStudentId BIGINT(10) NOT NULL ,\n" +
            "    ClassId VARCHAR(100) NOT NULL ,\n" +
            "    AssignmentMark TINYINT NOT NULL check(AssignmentMark >= 0 AND AssignmentMark <=100),    \n" +
            "    MidtermMark TINYINT NOT NULL check(MidtermMark >=0 AND MidtermMark <=100),\n" +
            "    FinalExamMark TINYINT NOT NULL check(FinalExamMark >=0 AND FinalExamMark <=100),\n" +
            "    ExtraActivityMark TINYINT NOT NULL check(ExtraActivityMark >=0 AND ExtraActivityMark <= 100),\n" +
            "    \n" +
            "    PRIMARY KEY (StudentID , ClassId),\n" +
            "    FOREIGN KEY (StudentId ) REFERENCES Student(StudentId) ,\n" +
            "    FOREIGN KEY (ClassId) REFERENCES class(ClassId)\n" +
            ")";

    public static void main(String[] args){
        DatabaseConnectionManager mySQL = null;
        //String username = Command.getString("What is MySQL username?");
        //String password = Command.getString("What is the password");
        // String localHost = Command.getString("please type your host")

        mySQL = new DatabaseConnectionManager("localhost" ,
                "root" , "JimmyYouhei@24122012");

        checkConnection(mySQL);

        // check if database exist
        createDatabase(mySQL);

        createTable(mySQL , CREATE_STUDENT_TABLE);
        createTable(mySQL , CREATE_CLASS_TABLE);
        createTable(mySQL, CREATE_STUDENT_CLASS_TABLE);

        Student student = new Student();
        student.setStudentId(3);
        student.setStudentName("updated");
        student.setStudentBirth(new GregorianCalendar(1980 , 11 , 20).getTime());
        student.setStudentEmail("update");
        student.setStudentMobile("update");
        student.setStudentAddress("update");
        student.setStudentEnrollmentDate(Timestamp.valueOf(LocalDateTime.now()));

        try {
            StudentDAO studentDAO = new StudentDAO(mySQL.getDatabaseConnection());
            //Student student2 = studentDAO.update(student);
            //Student student1 = studentDAO.get(2);
            Student find = studentDAO.update(student);
            System.out.println(find);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void createTable(DatabaseConnectionManager mySQL , String sqlStatement) {
        try(PreparedStatement statement = mySQL.getDatabaseConnection().prepareStatement((sqlStatement))){
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDatabase(DatabaseConnectionManager mySQL) {
        try(PreparedStatement statement = mySQL.checkConnection().prepareStatement(CREATE_DATABASE)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void checkConnection(DatabaseConnectionManager mySQL) {
        try {
            Connection connection = mySQL.checkConnection();
            System.out.println("connection ok");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("please check your username and password again");
            mySQL = null;
        }

    }

}
