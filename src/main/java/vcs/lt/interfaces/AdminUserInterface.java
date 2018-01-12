package vcs.lt.interfaces;

import vcs.lt.model.*;
import vcs.lt.utils.IOObjectStreamUtils;

import java.util.HashMap;
import java.util.Scanner;

public class AdminUserInterface implements UserInterface {
    private Admin adminUser;

    Scanner scanner = new Scanner(System.in);

    @Override
    public void openMainMenu(User user) {

        this.adminUser = (Admin) user;

        System.out.println("Hello, " + adminUser.getFirstName() + " " + adminUser.getSecondName());
        //ScannerUtils.scanString();
        adminMenu();
        System.out.print("Please, enter your menu choise: ");

        int adminMenuChoise = scanner.nextInt();
        adminChoises(adminMenuChoise);
    }

    public void adminMenu() {
        System.out.println("----Administration menu----");
        System.out.println("1. Register lecturer");
        System.out.println("2. Register student");
        System.out.println("3. Lecturers information");
        System.out.println("4. Students information");
        System.out.println("5. Exit");
    }

    public void adminChoises(int choise) {
        switch (choise){
            case 1: {
                addLecturer();
                break;
            }
            case 2: {
                addStudent();
                break;
            }
            case 3: {
                lecturersInfo();
                break;
            }
            case 4: {
                studentInfo();
                break;
            }
            case 5: {

                break;
            }
        }
    }

    public void addLecturer() {
        HashMap<String, User> users = new HashMap<>();
        System.out.print("Enter USERNAME: ");
        String userName = scanner.next();
        System.out.print("Enter FIRST NAME: ");
        String firstName = scanner.next();
        System.out.print("Enter SECOND NAME: ");
        String secondName = scanner.next();
        System.out.print("Enter PASSWORD: ");
        String password = scanner.next();

        users.put("lecturer", new Lecturer(userName, firstName, secondName, password, Role.LECTURER));
        IOObjectStreamUtils.writeObjectToFile("users", users);
    }

    public void addStudent() {
        HashMap<String, User> users = new HashMap<>();
        System.out.print("Enter USERNAME: ");
        String userName = scanner.next();
        System.out.print("Enter FIRST NAME: ");
        String firstName = scanner.next();
        System.out.print("Enter SECOND NAME: ");
        String secondName = scanner.next();
        System.out.print("Enter PASSWORD: ");
        String password = scanner.next();

        users.put("student", new Student(userName, firstName, secondName, password, Role.STUDENT));
        IOObjectStreamUtils.writeObjectToFile("users", users);

    }

    public void lecturersInfo() {

    }

    public void studentInfo() {

    }


}
