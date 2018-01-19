package vcs.lt.interfaces;

import vcs.lt.model.Course;
import vcs.lt.model.User;

public class CourseInterface implements UserInterface{

    private Course course;

    @Override
    public void openMainMenu(User user) {
        this.course = (Course) course;
    }

    public void courseMenu(){

    }
}
