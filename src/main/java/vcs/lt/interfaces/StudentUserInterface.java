package vcs.lt.interfaces;

import vcs.lt.model.Lecturer;
import vcs.lt.model.Student;
import vcs.lt.model.User;
import vcs.lt.utils.IOObjectStreamUtils;
import vcs.lt.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class StudentUserInterface implements UserInterface {

    private Student studentUser;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void openMainMenu(User user) {

        this.studentUser = (Student) user;
        System.out.println("Hello, " + studentUser.getFirstName() + " " + studentUser.getSecondName());
        studentChoises();

    }

    private void studentMenu() {
        System.out.println("----Student menu----");
        System.out.println("1. Change first and second name");
        System.out.println("2. Add information (personal number, date of birth, e-mail, mobile number, gender, address)");
        System.out.println("3. Selected courses information");
        System.out.println("4. Exit");
    }

    private void studentChoises() {
        studentMenu();
        System.out.print("Please, enter your menu choise: ");
        int studentMenuChoise = scanner.nextInt();
        ScannerUtils.checkValidScan(1, 4);
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
                //viewRunningCourses(studentUser.getUserName());
                studentChoises();
                break;
            }

            case 4: {
                break;
            }
        }
    }

    private void changeFirstSecondName(String username) {
        String firstName = ScannerUtils.scanString("Change your FIRST name: ");
        studentUser.setSecondName(firstName);
        String secondName = ScannerUtils.scanString("change your SECOND name: ");
        studentUser.setFirstName(secondName);

        try {
            HashMap<String, User> users = (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            Student student = (Student) users.get(username);
            student.setFirstName(firstName);
            student.setSecondName(secondName);

            users.put(username, student);
            IOObjectStreamUtils.writeObjectToFile("users", users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addStudentInfo(String username){

        String personalNumber = ScannerUtils.scanString("Add PERSONAL number: ");
        studentUser.setPersonalNumber(personalNumber);
        LocalDate dateOfBirth = LocalDate.parse(ScannerUtils.scanString("Add date of BIRTH: "));
        studentUser.setDateOfBirth(dateOfBirth);
        String email = ScannerUtils.scanString("Add e-mail: ");
        studentUser.setEmail(email);
        String mobileNumber = ScannerUtils.scanString("Add MOBILE number: ");
        studentUser.setMobileNumber(mobileNumber);
        String gender = ScannerUtils.scanString("Add GENDER: ");
        studentUser.setGender(gender);
        String address = ScannerUtils.scanString("Add ADDRESS: ");
        studentUser.setAddress(address);

        try {
            HashMap<String, User> users = (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            Lecturer lecturer = (Lecturer) users.get(username);
            lecturer.setPersonalNumber(personalNumber);
            lecturer.setDateOfBirth(dateOfBirth);
            lecturer.setEmail(email);
            lecturer.setMobileNumber(mobileNumber);
            lecturer.setGender(gender);
            lecturer.setAddress(address);

            users.put(username, lecturer);
            IOObjectStreamUtils.writeObjectToFile("users", users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




}
