package vcs.lt.interfaces;

import vcs.lt.model.Admin;
import vcs.lt.model.Role;
import vcs.lt.model.User;
import vcs.lt.service.CourseService;
import vcs.lt.service.UserService;
import vcs.lt.utils.ScannerUtils;

import java.time.LocalDate;

public class AdminUserInterface implements UserInterface {
    private Admin adminUser;
    private String userName;
    private String firstName;
    private String secondName;
    private String password;

    private UserService userService = new UserService();
    private CourseService courseService = new CourseService();

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

    private void addCourse() {
        String lecturerCode = ScannerUtils.scanString("Enter lecturer USERNAME: ");
        String courseCode = ScannerUtils.scanString("Enter COURSE CODE: ");
        String courseTitle = ScannerUtils.scanString("Enter TITLE: ");
        String courseDescription = ScannerUtils.scanString("Enter DESCRIPTION: ");
        LocalDate courseStartDate = LocalDate.parse(ScannerUtils.scanString("Enter start date (yyyy-mm-dd): "));
        //ScannerUtils.checkValidDate(courseStartDate.toString());
        int courseCredits = ScannerUtils.scanInt("Enter CREDITS: ");
        //lecturerCode = userName;

        courseService.createCourse(courseCode, courseTitle, courseDescription, courseStartDate, courseCredits, lecturerCode);

        System.out.println("Course registration complete.");
    }

    private void addLecturer() {
        userName = ScannerUtils.scanString("Enter USERNAME: ");
        firstName = ScannerUtils.scanString("Enter FIRST NAME: ");
        secondName = ScannerUtils.scanString("Enter SECOND NAME: ");
        password = ScannerUtils.scanString("Enter PASSWORD: ");

        userService.createUser(firstName, secondName, userName, password, Role.LECTURER);

        System.out.println("Lecturer registration complete.");
    }

    private void addStudent() {
        userName = ScannerUtils.scanString("Enter USERNAME: ");
        firstName = ScannerUtils.scanString("Enter FIRST NAME: ");
        secondName = ScannerUtils.scanString("Enter SECOND NAME: ");
        password = ScannerUtils.scanString("Enter PASSWORD: ");

        userService.createUser(firstName, secondName, userName, password, Role.STUDENT);

        System.out.println("Student registration complete.");
    }

    private void lecturersInfo() {
        userService.selectUser(Role.LECTURER);
    }

    private void studentInfo() {
        userService.selectUser(Role.STUDENT);
    }

    private void courseInfo() {
        courseService.viewAllCourses();
    }
}
