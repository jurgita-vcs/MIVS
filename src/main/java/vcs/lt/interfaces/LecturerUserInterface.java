package vcs.lt.interfaces;

import vcs.lt.model.Lecturer;
import vcs.lt.model.User;
import vcs.lt.service.CourseService;
import vcs.lt.service.UserService;
import vcs.lt.utils.ScannerUtils;

public class LecturerUserInterface implements UserInterface {

    private Lecturer lecturerUser;
    private UserService userService = new UserService();
    private CourseService courseService = new CourseService();

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
        System.out.println("4. Logout");
        System.out.println();
    }

    private void lecturerChoises() {
        lecturerMenu();
        System.out.println("Please, enter your menu choise: ");
        int lecturerMenuChoise = ScannerUtils.checkValidScan(1, 4);
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
                //runningCourse(lecturerUser.getUserName());
                viewRunningCourses(lecturerUser.getUserName());
                lecturerChoises();
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
        lecturerUser.setSecondName(firstName);
        String secondName = ScannerUtils.scanString("change your SECOND name: ");
        lecturerUser.setFirstName(secondName);

        userService.changeName(username, firstName, secondName);
    }

    private void addLecturerInfo(String username){
        String personalNumber = ScannerUtils.scanString("Add PERSONAL number: ");
        lecturerUser.setPersonalNumber(personalNumber);
        String dateOfBirth = ScannerUtils.scanString("Add date of BIRTH: ");
        lecturerUser.setDateOfBirth(dateOfBirth);
        String email = ScannerUtils.scanString("Add EMAIL: ");
        lecturerUser.setEmail(email);
        String mobileNumber = ScannerUtils.scanString("Add MOBILE number: ");
        lecturerUser.setMobileNumber(mobileNumber);
        String gender = ScannerUtils.scanString("Add gender: ");
        lecturerUser.setGender(gender);
        String address = ScannerUtils.scanString("Add ADDRESS: ");
        lecturerUser.setAddress(address);

        userService.addAdditionalInformation(username, personalNumber, dateOfBirth, email, mobileNumber, gender, address);
    }

    public void viewRunningCourses(String username) {

        courseService.selectCourse(username);


        String question = ScannerUtils.scanString("Do you want to see student of course? (Y/N)");
        System.out.println();

        if (question.toLowerCase().equals("y")) {
            String courseSelection = ScannerUtils.scanString("Enter course CODE: ");
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
