package vcs.lt.service;

import vcs.lt.model.Role;
import vcs.lt.model.User;
import vcs.lt.service.db.CourseRepository;
import java.time.LocalDate;

public class CourseService {

    private UserService userService = new UserService();
    private CourseRepository courseRepository = new CourseRepository();

    public void createCourse(String courseCode, String title, String description, LocalDate startDate, int credit, String username) {
        User user = userService.findUser(username);
        if (user != null && user.getRole() != Role.LECTURER) {
            System.out.println("This user can not drive the course");
            return;
        }
        courseRepository.createCourse(courseCode, title, description, startDate, credit, username);
    }

    public void selectCourse(String username) {
        courseRepository.selectCourse(username);
    }

    public void viewAllCourses() {
        courseRepository.viewAllCourses();
    }
}
