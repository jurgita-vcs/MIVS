package vcs.lt.interfaces;

import vcs.lt.model.Lecturer;
import vcs.lt.model.User;

public class LecturerUserInterface implements UserInterface {
    private Lecturer lecturerUser;

    @Override
    public void openMainMenu(User user) {

        this.lecturerUser = (Lecturer) user;

        System.out.println("Hello, " + lecturerUser.getFirstName() + " " + lecturerUser.getSecondName());
//        ScannerUtils.scanString();
        lecturerMenu();
//        System.out.print("Please, enter your menu choise: ");

//        int lecturerMenuChoise = scanner.nextInt();
//        lecturerChoises(lecturerMenuChoise);
    }

    private void lecturerMenu() {
        System.out.println("----Lecturer menu----");
        System.out.println("1. Change first and second name");
        System.out.println("2. Change information (personal number, date of birth, e-mail, mobile number, gender, address)");
        System.out.println("3. Running course information");
        System.out.println("4. Running course students");
        System.out.println("5. Exit");
    }
}
