package com.jimmyyouhei.student.mysql.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

public class Student {

    private long studentId;
    private String studentName;
    private Date studentBirth;
    private String studentAddress;
    private String studentMobile;
    private String studentEmail;
    private Timestamp studentEnrollmentDate;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentBirth() {
        return dateFormat.format(studentBirth);
    }

    public void setStudentBirth(Date studentBirth) {
        this.studentBirth = studentBirth;
    }

    public Timestamp getStudentEnrollmentDate() {

        return studentEnrollmentDate;
    }

    public void setStudentEnrollmentDate(Timestamp studentEnrollmentDate) {
        this.studentEnrollmentDate = studentEnrollmentDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentBirth=" + studentBirth +
                ", studentAddress='" + studentAddress + '\'' +
                ", studentMobile='" + studentMobile + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentEnrollmentDate=" + studentEnrollmentDate +
                '}';
    }
}


