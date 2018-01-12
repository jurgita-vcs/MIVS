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
//        adminMenu();
//        System.out.print("Please, enter your menu choise: ");
//
//        int adminMenuChoise = scanner.nextInt();
//        adminChoises(adminMenuChoise);
    }
}
