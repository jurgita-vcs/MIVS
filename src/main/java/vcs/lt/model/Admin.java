package vcs.lt.model;

public class Admin extends User {

    public Admin(String userName, String firstName, String secondName, String password) {
        super(userName, firstName, secondName, password, vcs.lt.model.Role.ADMIN);
    }




}