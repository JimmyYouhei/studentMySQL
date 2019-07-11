package com.jimmyyouhei.student.mysql.util;

import com.jimmyyouhei.student.mysql.entity.Student;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class StudentDAO implements DataAccessObject<Student> {
    private static final String CREATE_STUDENT_DEFAULT = "INSERT INTO Student (StudentName , StudentBirthDate , " +
            "StudentAddress , StudentMobile , StudentEmail)" +
            "VALUES(? , ? , ? , ? , ?)";

    private static final String GET_STUDENT = "SELECT StudentId , StudentName , StudentBirthDate, StudentAddress , StudentMobile , StudentEmail , StudentEnrollmentDate\n" +
            "FROM student \n" +
            "Where StudentId = ?";

    private static final String UPDATE = "UPDATE Student SET StudentName = ? , StudentBirthDate = ? , StudentAddress= ?, StudentMobile = ? ," +
            " StudentEmail = ? , StudentEnrollmentDate = ?\n" +
            "Where StudentId = ? ";

    final Connection connection;

    public StudentDAO(Connection thisConnection) {
        this.connection = thisConnection;
    }

    @Override
    public Student get(long id) {
        Student student = new Student();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_STUDENT)){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                student.setStudentId(rs.getLong("StudentId"));
                student.setStudentName(rs.getString("StudentName"));
                student.setStudentBirth(rs.getDate("StudentBirthDate"));
                student.setStudentAddress(rs.getString("StudentAddress"));
                student.setStudentMobile(rs.getString("StudentMobile"));
                student.setStudentEmail(rs.getString("StudentEmail"));
                student.setStudentEnrollmentDate(rs.getTimestamp("StudentEnrollmentDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;

    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public void create(Student dto) {

    }

    public void createDefault(Student dto) {
        try (PreparedStatement statement = this.connection.prepareStatement(CREATE_STUDENT_DEFAULT)){
            statement.setString(1, dto.getStudentName());
            statement.setString(2 , dto.getStudentBirth());
            statement.setString(3 , dto.getStudentAddress());
            statement.setString(4 , dto.getStudentMobile());
            statement.setString(5 , dto.getStudentEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Student update(Student dto) {
        Student student = null;
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE)){
            statement.setString(1 , dto.getStudentName());
            statement.setString(2 , dto.getStudentBirth());
            statement.setString(3 , dto.getStudentAddress());
            statement.setString(4 , dto.getStudentMobile());
            statement.setString(5, dto.getStudentEmail());
            statement.setTimestamp(6 , dto.getStudentEnrollmentDate());
            statement.setLong(7 , dto.getStudentId());
            statement.execute();
            student = this.get(dto.getStudentId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public void delete(Student dto) {

    }

}
