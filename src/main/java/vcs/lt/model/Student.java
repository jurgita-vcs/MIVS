package vcs.lt.model;

public class Student extends User {

    private String personalNumber;
    private String dateOfBirth;
    private String email;
    private String mobileNumber;
    private String gender;
    private String address;

    public Student(String userName, String firstName, String secondName, String password, Role userRole) {
        super(userName, firstName, secondName, password, userRole);
    }
}
