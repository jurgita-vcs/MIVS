package vcs.lt.interfaces;

import vcs.lt.model.Student;
import vcs.lt.model.User;

public class StudentUserInterface implements UserInterface {

    private Student studentUser;

    @Override
    public void openMainMenu(User user) {

        this.studentUser = (Student) user;
        System.out.println("Hello, " + studentUser.getFirstName() + " " + studentUser.getSecondName());

    }

    private void studentMenu() {
        System.out.println("----Student menu----");
        System.out.println("1. Change first and second name");
        System.out.println("2. Selected courses information");
        System.out.println("3. Exit");
    }

}
