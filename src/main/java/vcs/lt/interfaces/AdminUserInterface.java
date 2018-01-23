package vcs.lt.interfaces;

import vcs.lt.model.*;
import vcs.lt.service.CourseService;
import vcs.lt.utils.IOObjectStreamUtils;
import vcs.lt.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;

public class AdminUserInterface implements UserInterface {
    private Admin adminUser;
    private String userName;
    private String firstName;
    private String secondName;
    private String password;

    @Override
    public void openMainMenu(User user) {
        this.adminUser = (Admin) user;

        System.out.println("Hello, " + adminUser.getFirstName() + " " + adminUser.getSecondName());
        adminMenu();
    }

    private void adminMenu() {
        System.out.println();
        System.out.println("----Administrator menu----");
        System.out.println("1. Register/info LECTURER");
        System.out.println("2. Register/info STUDENT");
        System.out.println("3. Register/info COURSE");
        System.out.println("4. Logout");
        System.out.println();

        System.out.print("Please, enter your menu choise: ");
        int choise = ScannerUtils.checkValidScan(1, 4);
        switch (choise) {
            case 1: {
                lecturerMenu();
                adminMenu();
                break;
            }
            case 2: {
                studentMenu();
                adminMenu();
                break;
            }
            case 3: {
                courseMenu();
                adminMenu();
                break;
            }
            case 4: {
                System.out.println("Bye bye.");
                break;
            }
        }
    }

    private void subMenu(String message) {
        System.out.println();
        System.out.println("Administrator ----" + message + " menu----");
        System.out.println("1. Register -" + message + "-");
        System.out.println("2. Information");
        System.out.println("3. Exit");
        System.out.println();
    }

    private void lecturerMenu() {
        subMenu("Lecturer");
        System.out.print("Please, enter your choice: ");
        int menuChoice = ScannerUtils.checkValidScan(1, 3);
        switch (menuChoice){
            case 1: {
                addLecturer();
                lecturerMenu();
                break;
            }
            case 2: {
                lecturersInfo();
                lecturerMenu();
                break;
            }
            case 3: {
                adminMenu();
                System.out.println();
                break;
            }
        }
    }

    private void studentMenu() {
        subMenu("Student");
        System.out.print("Please, enter your choice: ");
        int menuChoice = ScannerUtils.checkValidScan(1, 3);
        switch (menuChoice){
            case 1: {
                addStudent();
                break;
            }
            case 2: {
                studentInfo();
                break;
            }
            case 3: {
                adminMenu();
                System.out.println();
                break;
            }
        }
    }

    private void courseMenu() {
        subMenu("Course");
        System.out.print("Please, enter your choice: ");
        int menuChoice = ScannerUtils.checkValidScan(1, 3);
        switch (menuChoice){
            case 1: {
                addCourse();
                break;
            }
            case 2: {
                courseInfo();
                break;
            }
            case 3: {
                adminMenu();
                System.out.println();
                break;
            }
        }
    }

    private void courseInfo() {
        try {
            HashMap<String, Course> courses = (HashMap<String, Course>) IOObjectStreamUtils.readFirstObjectFromFile("courses");
            int nr = 0;

            System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", "Nr.", "Course Code", "Title", "Started Date", "Credits");
            for (Course course : courses.values()) {
                nr += 1;
                System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", nr, course.getCourseCode(), course.getTitle(), course.getStartDate(), course.getCredit());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private void addCourse() {
        CourseService course = new CourseService();
        String courseCode = ScannerUtils.scanString("Enter COURSE CODE: ");
        String courseTitle = ScannerUtils.scanString("Enter TITLE: ");
        String courseDescription = ScannerUtils.scanString("Enter DESCRIPTION: ");
        LocalDate courseStartDate = LocalDate.parse(ScannerUtils.scanString("Enter start date (yyyy-mm-dd): "));
        //ScannerUtils.checkValidDate(courseStartDate.toString());

        String lecturerCode = userName;
        int courseCredits = ScannerUtils.scanInt("Enter CREDITS: ");
        course.createCourse(courseCode, courseTitle, courseDescription, courseStartDate, courseCredits, lecturerCode);

        System.out.println("Course registration complete.");
    }

    private void addLecturer() {
        userName = ScannerUtils.scanString("Enter USERNAME: ");
        firstName = ScannerUtils.scanString("Enter FIRST NAME: ");
        secondName = ScannerUtils.scanString("Enter SECOND NAME: ");
        password = ScannerUtils.scanString("Enter PASSWORD: ");

        try {
            HashMap<String, User> users = (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            users.put(userName, new Lecturer(userName, firstName, secondName, password));
            IOObjectStreamUtils.writeObjectToFile("users", users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Lecturer registration complete.");
    }

    private void addStudent() {
        userName = ScannerUtils.scanString("Enter USERNAME: ");
        firstName = ScannerUtils.scanString("Enter FIRST NAME: ");
        secondName = ScannerUtils.scanString("Enter SECOND NAME: ");
        password = ScannerUtils.scanString("Enter PASSWORD: ");

        HashMap<String, User> users = new HashMap<>();
        users.put(userName, new Student(userName, firstName, secondName, password, Role.STUDENT));
        IOObjectStreamUtils.writeObjectToFile("users", users);

        System.out.println("Student registration complete.");
    }

    private void lecturersInfo() {
        try {
            HashMap<String, User> users = (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            int nr = 0;

            System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", "Nr.", "Username", "Role", "First name", "Second Name");
            for (User user : users.values()) {
                if (user.getRole().equals(Role.LECTURER)) {
                    nr += 1;
                    System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", nr, user.getUserName(), user.getRole(), user.getFirstName(), user.getSecondName());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void studentInfo() {
        try {
            HashMap<String, User> users = (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            int nr = 0;

            System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", "Nr.", "Username", "Role", "First name", "Second Name");
            for (User user : users. values()) {
                if (user.getRole().equals(Role.STUDENT)) {
                    nr += 1;
                    System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", nr, user.getUserName(), user.getRole(), user.getFirstName(), user.getSecondName());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
