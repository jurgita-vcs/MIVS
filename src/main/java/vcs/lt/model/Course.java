package vcs.lt.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Course implements Serializable {
    private String courseCode;
    private String title;
    private String description;
    private LocalDate startDate;
    private int credit;
    private String usermane;

    public Course(String courseCode, String title, String description, LocalDate startDate, int credit, String usermane) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.credit = credit;
        this.usermane = usermane;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsermane() {
        return usermane;
    }

    public void setUsermane(String usermane) {
        this.usermane = usermane;
    }
}
