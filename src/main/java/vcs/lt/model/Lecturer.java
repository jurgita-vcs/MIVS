package vcs.lt.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lecturer extends User {

    private List<String> leadLectures = new ArrayList<>();

    private String personalNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String mobileNumber;
    private String gender;
    private String address;

    public Lecturer(String userName, String firstName, String secondName, String password) {
        super(userName, firstName, secondName, password, Role.LECTURER);
    }


    public List<String> getLeadLectures() {
        return leadLectures;
    }

    public void addLeadLecture(String lecturerCode) {
        this.leadLectures.add(lecturerCode);
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
