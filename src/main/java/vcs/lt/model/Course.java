package vcs.lt.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Course implements Serializable {
    private String courseCode;
    private String tittle;
    private String description;
    private LocalDate startDate;
    private int credit;
    private String lecturerCode;

    public Course(String courseCode, String tittle, String description, LocalDate startDate, int credit) {
        this.courseCode = courseCode;
        this.tittle = tittle;
        this.description = description;
        this.startDate = startDate;
        this.credit = credit;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getLecturerCode() {
        return lecturerCode;
    }

    public void setLecturerCode(String lecturerCode) {
        this.lecturerCode = lecturerCode;
    }
}
