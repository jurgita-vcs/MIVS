package vcs.lt.interfaces;

import vcs.lt.model.*;
import vcs.lt.utils.IOObjectStreamUtils;
import vcs.lt.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        System.out.println();
        System.out.println("----Lecturer menu----");
        System.out.println("1. Change first and second name");
        System.out.println("2. Add information (personal number, date of birth, e-mail, mobile number, gender, address)");
        System.out.println("3. Running courses information");
        System.out.println("4. Exit");
        System.out.println();
    }

    private void lecturerChoises() {
        lecturerMenu();
        System.out.print("Please, enter your menu choise: ");
        int lecturerMenuChoise = scanner.nextInt();
        ScannerUtils.checkValidScan(1, 4);
        switch (lecturerMenuChoise) {
            case 1: {
                changeFirstSecondName(lecturerUser.getUserName());
                lecturerChoises();
                break;
            }
            case 2: {
                addLecturerInfo(lecturerUser.getUserName());
                lecturerChoises();
                break;
            }
            case 3: {
                runningCourse(lecturerUser.getUserName());
                viewRunningCourses(lecturerUser.getUserName());
                lecturerChoises();
                break;
            }
            case 4: {
                break;
            }
        }
    }

    private void changeFirstSecondName(String username) {
        String firstName = ScannerUtils.scanString("Change your FIRST name: ");
        lecturerUser.setSecondName(firstName);
        String secondName = ScannerUtils.scanString("Change your SECOND name: ");
        lecturerUser.setFirstName(secondName);

        try {
            HashMap<String, User> users = (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            Lecturer lecturer = (Lecturer) users.get(username);
            lecturer.setFirstName(firstName);
            lecturer.setSecondName(secondName);

            users.put(username, lecturer);
            IOObjectStreamUtils.writeObjectToFile("users", users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addLecturerInfo(String username){

        String personalNumber = ScannerUtils.scanString("Add PERSONAL number: ");
        lecturerUser.setPersonalNumber(personalNumber);
        LocalDate dateOfBirth = LocalDate.parse(ScannerUtils.scanString("Add date of BIRTH: "));
        lecturerUser.setDateOfBirth(dateOfBirth);
        String email = ScannerUtils.scanString("Add e-mail: ");
        lecturerUser.setEmail(email);
        String mobileNumber = ScannerUtils.scanString("Add MOBILE number: ");
        lecturerUser.setMobileNumber(mobileNumber);
        String gender = ScannerUtils.scanString("Add GENDER: ");
        lecturerUser.setGender(gender);
        String address = ScannerUtils.scanString("Add ADDRESS: ");
        lecturerUser.setAddress(address);

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

    private ArrayList<String> runningCourse(String username) {
        int nr = 0;
        ArrayList<String> code = new ArrayList<>();

        HashMap<String, Lecturer> lecturer = null;
        HashMap<String, Course> course = null;

        try {
            lecturer = (HashMap<String, Lecturer>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            course = (HashMap<String, Course>) IOObjectStreamUtils.readFirstObjectFromFile("courses");
        } catch (FileNotFoundException e) {
            System.out.println("No Courses Found");
        }

        System.out.printf("%-3s %-15s %-10s %-15s %-15s\n", "Nr.", "Code (lecCode)", "Title", "StartDate", "Credit");
        for (Map.Entry<String, Course> entry : course.entrySet()) {
            Course value = entry.getValue();

            if (value.getUsermane().equals(lecturer.get(username))) {
                nr += 1;
                code.add(value.getUsermane());
                System.out.printf("%-3s %-15s %-10s %-15s %-15s\n", nr, value.getCourseCode(), value.getTitle(), value.getStartDate(), value.getCredit());
            }
        }
        return code;
    }

    private void viewRunningCourses(String username) {

        String question = ScannerUtils.scanString("Do you want to see student of course " + " (y/n)?");
        System.out.println();
        if (question.toLowerCase().equals("y")) {

        }
        else if (question.toLowerCase().equals("n")) {

        }
        else System.out.println("Įveskite raides y/n");


//        ArrayList<String> courses = runningCourse(username);

//        String code = courses.get(ScannerUtils.scanInt("Select course", 1, courses.size()) - 1);
//        // System.out.println(code);
//        HashMap<String,User> users = null;
//
//        try {
//            users = (HashMap<String,User>)IOObjectStreamUtils.readFirstObjectFromFile("users");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        for (Map.Entry<String, User> entry : users.entrySet()) {
//            User value = entry.getValue();
//            if (value.getRole()== Role.STUDENT) {
//                Student value1 =(Student) value;
//                if(value1.getRunningCourses().contains(code)){
//                    System.out.println(value.getFirstName()+ " "+ value.getSecondName());
//                }
//
//            }
//
//        }
    }

}
