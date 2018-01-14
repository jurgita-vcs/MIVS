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
        System.out.println("----Lecturer menu----");
        System.out.println("1. Change name");
        System.out.println("2. Change surname");
        System.out.println("3. Lecturers information");
        System.out.println("4. Students information");
        System.out.println("5. Exit");
    }

}
