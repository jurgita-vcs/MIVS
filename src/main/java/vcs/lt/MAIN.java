package vcs.lt;

import vcs.lt.interfaces.AdminUserInterface;
import vcs.lt.interfaces.LecturerUserInterface;
import vcs.lt.interfaces.UserInterface;
import vcs.lt.model.User;
import vcs.lt.service.UserService;

public class MAIN {
    public static void main(String[] args) {
        Application.initialize();
        UserInterface userInterface = null;

        User user = UserService.login();
        switch (user.getRole()) {
            case ADMIN: {
                userInterface = new AdminUserInterface();
                userInterface.openMainMenu(user);
            }
            case LECTURER: {
                userInterface = new LecturerUserInterface();
                userInterface.openMainMenu(user);
            }
//            case STUDENT: {
//                userInterface = new StudentUserInterface();
//            }
        }

//        userInterface.openMainMenu(user);

    }

}
