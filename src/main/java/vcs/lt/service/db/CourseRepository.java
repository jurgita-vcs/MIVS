package vcs.lt.service.db;

import java.sql.*;
import java.time.LocalDate;

public class CourseRepository {

    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/jurga/mivs1";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    public void createTable() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS course " +
                                "(" +
                                "    courseCode VARCHAR(50) NOT NULL," +
                                "    title VARCHAR(50)," +
                                "    description VARCHAR(255)," +
                                "    startDate DATE," +
                                "    credit INT," +
                                "    username VARCHAR(50)," +
                                "    PRIMARY KEY(coursecode)" +
                                ");"
                )
        ) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCourse(String courseCode, String title, String description, LocalDate startDate, int credit, String username) {
        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO course (" +
                                "courseCode,title,description,startDate,credit,username)" +
                                " VALUES (?,?,?,?,?,?)")
        ) {
            statement.setString(1, courseCode);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setString(4, String.valueOf(startDate));
            statement.setInt(5, credit);
            statement.setString(6, username);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllCourses() {
        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM course"
                )
        ) {
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("No Courses Found");
            }

            System.out.printf("%-10s %-10s %-20s %-15s %-15s %-20s\n", "Code", "Title", "Description", "Started Date", "Credits", "LecturerCode");
            while (resultSet.next()) {
                System.out.printf("%-10s %-10s %-20s %-15s %-15s %-20s\n",
                        resultSet.getString("courseCode"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("startDate"),
                        resultSet.getString("credit"),
                        resultSet.getString("username")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectCourse(String username) {
        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM course WHERE username = ?"
                )
        ) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("No Courses Found");
            }

            System.out.printf("%-10s %-10s %-20s %-15s %-15s %-20s\n", "Course", "Title", "Description", "Started Date", "Credits", "LecturerCode");
            while (resultSet.next()) {
                System.out.printf("%-10s %-10s %-20s %-15s %-15s %-20s\n",
                        resultSet.getString("courseCode"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("startDate"),
                        resultSet.getString("credit"),
                        resultSet.getString("username")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
