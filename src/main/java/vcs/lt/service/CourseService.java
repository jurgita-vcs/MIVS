package vcs.lt.service;

import vcs.lt.model.Course;
import vcs.lt.model.Role;
import vcs.lt.model.User;
import vcs.lt.utils.IOObjectStreamUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;

public class CourseService {
    private UserService userService = new UserService();

    public HashMap<String, Course> findAllCourses() {
        try {
            return (HashMap<String, Course>) IOObjectStreamUtils.readFirstObjectFromFile("courses");
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public void createCourse(String title, String description, LocalDate startDate, int credit, String userName) {
        User user = userService.findUser(userName);
        if (user != null && user.getRole() != Role.LECTURER) {
            System.out.println("This user can not drive the course");
            return;
        }
        Course course = new Course(title, description, startDate, credit, user.getUserName());
       // ((Lecturer) user).addLeadLecture(course.getCourseCode());
        userService.save(user);

        HashMap<String, Course> allCourses = findAllCourses();
        allCourses.put(course.getCourseCode(), course);
        IOObjectStreamUtils.writeObjectToFile("courses", allCourses);
    }
}
