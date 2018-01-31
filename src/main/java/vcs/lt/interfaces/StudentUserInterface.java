package vcs.lt.interfaces;

import vcs.lt.model.Student;
import vcs.lt.model.User;
import vcs.lt.service.CourseService;
import vcs.lt.service.UserService;
import vcs.lt.utils.ScannerUtils;

public class StudentUserInterface implements UserInterface {

    private Student studentUser;
    private UserService userService = new UserService();
    private CourseService courseService = new CourseService();

    @Override
    public void openMainMenu(User user) {

        this.studentUser = (Student) user;
        System.out.println("Hello, " + studentUser.getFirstName() + " " + studentUser.getSecondName());
        studentChoises();
    }

    private void studentMenu() {
        System.out.println();
        System.out.println("----Student menu----");
        System.out.println("1. Change first and second name");
        System.out.println("2. Add information (personal number, date of birth, e-mail, mobile number, gender, address)");
        System.out.println("3. Selected courses information");
        System.out.println("4. Logout");
        System.out.println();
    }

    private void studentChoises() {
        studentMenu();
        System.out.println("Please, enter your menu choise: ");
        int studentMenuChoise = ScannerUtils.checkValidScan(1, 4);
        switch (studentMenuChoise) {
            case 1: {
                changeFirstSecondName(studentUser.getUserName());
                studentChoises();
                break;
            }
            case 2: {
                addStudentInfo(studentUser.getUserName());
                studentChoises();
                break;
            }
            case 3: {
                //runningCourse(studentUser.getUserName());
                viewRunningCourses(studentUser.getUserName());
                studentChoises();
                break;
            }
            case 4: {
                System.out.println("Bye bye.");
                break;
            }
        }

    }

    private void changeFirstSecondName(String username) {
        String firstName = ScannerUtils.scanString("Change your FIRST name: ");
        studentUser.setSecondName(firstName);
        String secondName = ScannerUtils.scanString("change your SECOND name: ");
        studentUser.setFirstName(secondName);

        userService.changeName(username, firstName, secondName);
    }

    private void addStudentInfo(String username){
        String personalNumber = ScannerUtils.scanString("Add PERSONAL number: ");
        studentUser.setPersonalNumber(personalNumber);
        String dateOfBirth = ScannerUtils.scanString("Add date of BIRTH: ");
        studentUser.setDateOfBirth(dateOfBirth);
        String email = ScannerUtils.scanString("Add EMAIL: ");
        studentUser.setEmail(email);
        String mobileNumber = ScannerUtils.scanString("Add MOBILE number: ");
        studentUser.setMobileNumber(mobileNumber);
        String gender = ScannerUtils.scanString("Add gender: ");
        studentUser.setGender(gender);
        String address = ScannerUtils.scanString("Add ADDRESS: ");
        studentUser.setAddress(address);

        userService.addAdditionalInformation(username, personalNumber, dateOfBirth, email, mobileNumber, gender, address);
    }

    public void viewRunningCourses(String courseCode) {
        courseService.selectCourse(courseCode);

    }

}
