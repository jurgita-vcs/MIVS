package vcs.lt.service.db;

import vcs.lt.model.*;

import java.sql.*;

public class UserRepository {

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
                        "CREATE TABLE IF NOT EXISTS user " +
                                "(" +
                                "    username VARCHAR(50) NOT NULL," +
                                "    password VARCHAR(50)," +
                                "    firstName VARCHAR(50)," +
                                "    secondName VARCHAR(50)," +
                                "    role VARCHAR(10)," +
                                "    PRIMARY KEY(username)" +
                                ");"
                )
        ) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User findByUserName(String userName) {
        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM user WHERE username = ?"
                )
        ) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            //if no users
            if (!resultSet.next()) {
                return null;
            }
            switch (resultSet.getString("role")) {
                case "ADMIN":
                    return new Admin(resultSet.getString("firstName"),
                            resultSet.getString("secondName"),
                            resultSet.getString("userName"),
                            resultSet.getString("password"));
                case "LECTURER":
                    return new Lecturer(resultSet.getString("firstName"),
                            resultSet.getString("secondName"),
                            resultSet.getString("userName"),
                            resultSet.getString("password"));
                case "STUDENT":
                    return new Student(resultSet.getString("firstName"),
                            resultSet.getString("secondName"),
                            resultSet.getString("userName"),
                            resultSet.getString("password"));
                default:
                    return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createUser(String firstName, String secondName, String userName, String password, String role) {
        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user (" +
                                "firstName,secondName,userName,password,role)" +
                                " VALUES (?,?,?,?,?)")
        ) {
            statement.setString(1, firstName);
            statement.setString(2, secondName);
            statement.setString(3, userName);
            statement.setString(4, password);
            statement.setString(5, role);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectUser(Role role) {
        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM user WHERE role = ?"
                )
        ) {
            statement.setString(1, String.valueOf(role));
            ResultSet resultSet = statement.executeQuery();
            //if no users
            if (!resultSet.next()) {
                System.out.println("No users");
            }

            System.out.printf("%-10s %-20s %-20s %-15s\n", "Username", "First Name", "Second Name", "Role");
            while (resultSet.next()) {
                System.out.printf("%-10s %-20s %-20s %-15s\n",
                        resultSet.getString("userName"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeName(String userName, String firstName, String secondName) {
        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE user SET firstName = ?, secondName = ? WHERE username = ?"
                )
        ) {
            statement.setString(1, firstName);
            statement.setString(2, secondName);
            statement.setString(3, userName);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAdditionalColumns() {
        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "ALTER TABLE user ADD (personalNumber VARCHAR(50), dateOfBirth VARCHAR(50), email VARCHAR(50), mobileNumber VARCHAR(50), gender VARCHAR(10), address VARCHAR(50));"
                )
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAdditionalInformation(String userName, String personalNumber, String dateOfBirth, String email, String mobileNumber, String gender, String address) {
        try (
                Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE user SET personalNumber = ?, dateOfBirth = ?, email = ?, mobileNumber = ?, gender = ?, address = ? WHERE username = ?"
                )
        ) {

            statement.setString(1, personalNumber);
            statement.setString(2, dateOfBirth);
            statement.setString(3, email);
            statement.setString(4, mobileNumber);
            statement.setString(5, gender);
            statement.setString(6, address);
            statement.setString(7, userName);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
