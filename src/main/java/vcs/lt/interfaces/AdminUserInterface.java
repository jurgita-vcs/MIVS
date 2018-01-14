package vcs.lt.interfaces;

import vcs.lt.model.*;
import vcs.lt.utils.IOObjectStreamUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminUserInterface implements UserInterface {
    private Admin adminUser;
    private String userName;
    private String firstName;
    private String secondName;
    private String password;

    Scanner scanner = new Scanner(System.in);

    HashMap<String, User> users = new HashMap<>();
    User student = new Student(userName, firstName, secondName, password, Role.STUDENT);

    @Override
    public void openMainMenu(User user) {

        this.adminUser = (Admin) user;

        System.out.println("Hello, " + adminUser.getFirstName() + " " + adminUser.getSecondName());
        //ScannerUtils.scanString();
        adminMenu();
        adminChoises();
    }

    private void adminMenu() {
        System.out.println("----Administration menu----");
        System.out.println("1. Register lecturer");
        System.out.println("2. Register student");
        System.out.println("3. Register course");
        System.out.println("4. Lecturers information");
        System.out.println("5. Students information");
        System.out.println("6. Courses information");
        System.out.println("7. Logout");
    }

    public void adminChoises() {
        System.out.print("Please, enter your menu choise: ");
        int adminMenuChoise = scanner.nextInt();
        switch (adminMenuChoise){
            case 1: {
                addLecturer();
                adminMenu();
                adminChoises();
                break;
            }
            case 2: {
                addStudent();
                adminMenu();
                adminChoises();
                break;
            }
            case 3: {
                addCourse();
                adminMenu();
                adminChoises();
                break;
            }
            case 4: {
                lecturersInfo();
                adminMenu();
                adminChoises();
                break;
            }
            case 5: {
                studentInfo();
                adminMenu();
                adminChoises();
                break;
            }
            case 6: {
                courseInfo();
                adminMenu();
                adminChoises();
                break;
            }
            case 7: {
                break;
            }
        }
    }

    private void courseInfo() {

    }

    private void addCourse() {

    }

    public void addLecturer() {
        System.out.print("Enter USERNAME: ");
        userName = scanner.next();
        System.out.print("Enter FIRST NAME: ");
        firstName = scanner.next();
        System.out.print("Enter SECOND NAME: ");
        secondName = scanner.next();
        System.out.print("Enter PASSWORD: ");
        password = scanner.next();

        try {
            HashMap<String, User> users = (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            users.put("lecturer", new Lecturer(userName, firstName, secondName, password, Role.LECTURER));
            IOObjectStreamUtils.writeObjectToFile("users", users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addStudent() {
        HashMap<String, User> users = new HashMap<>();
        System.out.print("Enter USERNAME: ");
        userName = scanner.next();
        System.out.print("Enter FIRST NAME: ");
        firstName = scanner.next();
        System.out.print("Enter SECOND NAME: ");
        secondName = scanner.next();
        System.out.print("Enter PASSWORD: ");
        password = scanner.next();

        users.put("student", student);
        IOObjectStreamUtils.writeObjectToFile("users", users);
    }

    public void lecturersInfo() {
        try {
            HashMap<String, User> users = (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            int nr = 0;

            System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", "Nr.", "Username", "Role", "First name", "Second Name");
            for (Map.Entry<String, User> entry : users.entrySet()) {
                User value = entry.getValue();
                if (value.getRole().equals(Role.LECTURER)) {
                    nr += 1;
                    System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", nr, value.getUserName(), value.getRole(), value.getFirstName(), value.getSecondName());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void studentInfo() {
        try {
            HashMap<String, User> users = (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            int nr = 0;

            System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", "Nr.", "Username", "Role", "First name", "Second Name");
            for (Map.Entry<String, User> entry : users.entrySet()) {
                User value = entry.getValue();
                if (value.getRole().equals(Role.STUDENT)) {
                    nr += 1;
                    System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", nr, value.getUserName(), value.getRole(), value.getFirstName(), value.getSecondName());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
