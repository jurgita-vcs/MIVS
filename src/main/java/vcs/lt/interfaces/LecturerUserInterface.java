package vcs.lt.interfaces;

import vcs.lt.model.Lecturer;
import vcs.lt.model.User;

import java.util.Scanner;

public class LecturerUserInterface implements UserInterface {

    private Lecturer lecturerUser;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void openMainMenu(User user) {

        this.lecturerUser = (Lecturer) user;
        System.out.println("\nHello, " + lecturerUser.getFirstName() + " " + lecturerUser.getSecondName());
        lecturerChoises();
    }

    private void lecturerMenu() {
        System.out.println("----Lecturer menu----");
        System.out.println("1. Change first and second name");
        System.out.println("2. Add information (personal number, date of birth, e-mail, mobile number, gender, address)");
        System.out.println("3. Running courses information");
        System.out.println("4. Running course students");
        System.out.println("5. Exit");
    }

    public void lecturerChoises() {
        lecturerMenu();
        System.out.print("Please, enter your menu choise: ");
        int lecturerMenuChoise = scanner.nextInt();
        switch (lecturerMenuChoise) {
            case 1: {
                changeFirstSecondName();
                lecturerChoises();
                break;
            }
            case 2: {
                addLecturerInfo();
                lecturerChoises();
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }

        }

    }

    public void changeFirstSecondName() {
        String firstName = scanner.next();
        lecturerUser.setSecondName(firstName);
        String secondName = scanner.next();
        lecturerUser.setFirstName(secondName);
    }

    public void addLecturerInfo(){

    }

}
