package vcs.lt.model;

public class Student extends User {

    private String personalNumber;
    private String dateOfBirth;
    private String email;
    private String mobileNumber;
    private String gender;
    private String address;

    public Student(String userName, String firstName, String secondName, String password) {
        super(userName, firstName, secondName, password, Role.STUDENT);
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
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
