package vcs.lt.service;

import vcs.lt.model.Role;
import vcs.lt.model.User;
import vcs.lt.service.db.UserRepository;
import vcs.lt.utils.ScannerUtils;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public User login() {
        System.out.println("Welcome! Please login.");
        while (true) {
            System.out.println();
            String username = ScannerUtils.scanString("Username: ");
            String password = ScannerUtils.scanString("Password: ");

            User user = findUser(username);
            if (user != null
                    && username.equals(user.getUserName())
                    && password.equals(user.getPassword())) {
                return user;
            }
            System.out.println("Login failed, please try again");
        }
    }

    public User findUser(String username) {
        return userRepository.findByUserName(username);
    }

    public void createUser(String firstName, String secondName, String userName, String password, Role role) {
        userRepository.createUser(firstName, secondName, userName, password, role.toString());
    }

    public void selectUser(Role role) {
        userRepository.selectUser(role);
    }

    public void changeName(String userName, String firstName, String secondName) {
        userRepository.changeName(userName, firstName, secondName);
    }

    public void addAdditionalInformation(String userName, String personalNumber, String dateOfBirth, String email, String mobileNumber, String gender, String address) {
        //userRepository.addAdditionalColumns();
        userRepository.addAdditionalInformation(userName, personalNumber, dateOfBirth, email, mobileNumber, gender, address);
    }

}
