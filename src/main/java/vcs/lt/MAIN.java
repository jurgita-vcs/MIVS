package vcs.lt;

import vcs.lt.interfaces.AdminUserInterface;
import vcs.lt.interfaces.LecturerUserInterface;
import vcs.lt.interfaces.StudentUserInterface;
import vcs.lt.interfaces.UserInterface;
import vcs.lt.model.User;
import vcs.lt.service.UserService;

public class MAIN {
    public static void main(String[] args) {
        Application.initialize();
        UserInterface userInterface = null;

        System.out.println();
        while (true) {
            User user = UserService.login();
            switch (user.getRole()) {
                case ADMIN: {
                    userInterface = new AdminUserInterface();
                    break;
                }
                case LECTURER: {
                    userInterface = new LecturerUserInterface();
                    break;
                }
                case STUDENT: {
                    userInterface = new StudentUserInterface();
                    break;
                }
            }
            userInterface.openMainMenu(user);
        }



    }

}
