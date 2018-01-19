package vcs.lt.interfaces;

import vcs.lt.model.*;
import vcs.lt.utils.IOObjectStreamUtils;
import vcs.lt.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class AdminUserInterface implements UserInterface {
    private Admin adminUser;
    private String userName;
    private String firstName;
    private String secondName;
    private String password;

    Scanner scanner = new Scanner(System.in);

    @Override
    public void openMainMenu(User user) {

        this.adminUser = (Admin) user;

        System.out.println("Hello, " + adminUser.getFirstName() + " " + adminUser.getSecondName());
        adminChoises();
    }

    private void adminMenu() {
        System.out.println("----Administration menu----");
        System.out.println("1. Register -lecturer-");
        System.out.println("2. Register -student-");
        System.out.println("3. Register -course-");
        System.out.println("4. Lecturers information");
        System.out.println("5. Students information");
        System.out.println("6. Add -course-");
        System.out.println("7. Courses information");
        System.out.println("8. Logout");
        System.out.println();
    }

    public void adminChoises() {
        adminMenu();
        System.out.print("Please, enter your menu choise: ");
        int adminMenuChoise = ScannerUtils.checkValidScan(1, 8);
        switch (adminMenuChoise){
            case 1: {
                addLecturer();
                adminChoises();
                break;
            }
            case 2: {
                addStudent();
                adminChoises();
                break;
            }
            case 3: {
                addCourse();
                adminChoises();
                break;
            }
            case 4: {
                lecturersInfo();
                adminChoises();
                break;
            }
            case 5: {
                studentInfo();
                adminChoises();
                break;
            }
            case 6: {
                addCourse();
                adminChoises();
                break;
            }
            case 7: {
                courseInfo();
                adminChoises();
                break;
            }
            case 8: {
                System.out.println("Bye bye.");
                System.out.println();
                break;
            }
        }
    }

    private void courseInfo() {
        try {
            HashMap<String, Course> courses = (HashMap<String, Course>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            int nr = 0;

            System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", "Nr.", "Course Code", "Title", "Started Date", "Credits");
            for (Course course : courses.values()) {
                nr += 1;
                System.out.printf("%-3s %-10s %-10s %-15s %-15s\n", nr, course.getCourseCode(), course.getTittle(), course.getStartDate(), course.getCredit());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private void addCourse() {
        String courseCode = ScannerUtils.scanString("Enter COURSE CODE");
        String courseTitle = ScannerUtils.scanString("Enter TITLE");
        String courseDescription = ScannerUtils.scanString("Enter DESCRIPTION");
        LocalDate courseStartDate = LocalDate.parse(ScannerUtils.scanString("Enter start date (yyyy-mm-dd)"));
        int courseCredits = ScannerUtils.scanInt("Enter CREDITS");

        HashMap<String, Course> courses = new HashMap<>();
        courses.put(courseCode, new Course(courseCode, courseTitle, courseDescription, courseStartDate, courseCredits));
        IOObjectStreamUtils.writeObjectToFile("users", courses);

        System.out.println("Course registration complete.");
        System.out.println();

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
        System.out.println();

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
        System.out.println();
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
        System.out.println();
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
        System.out.println();
    }


}
