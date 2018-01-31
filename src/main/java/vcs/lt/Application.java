package vcs.lt;

import vcs.lt.service.db.CourseRepository;
import vcs.lt.service.db.UserRepository;
import vcs.lt.utils.IOObjectStreamUtils;

import java.io.File;
import java.io.FileNotFoundException;


public class Application {
    static void initialize() {
        Configuration configuration;
        if (!new File("configuration").exists()) {
            createConfigurationFile();
            initializeData();
        } else {
            configuration = getConfigurationFromFile();
            if (doesConfigurationIsNotInitialized(configuration)) {
                initializeData();
            }
        }
    }

    private static void createConfigurationFile() {
        Configuration configuration;
        configuration = new Configuration();
        configuration.setInitialized(true);
        IOObjectStreamUtils.writeObjectToFile("configuration", configuration);
    }

    private static Configuration getConfigurationFromFile() {
        try {
            return  (Configuration) IOObjectStreamUtils.readFirstObjectFromFile("configuration");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean doesConfigurationIsNotInitialized(Configuration configuration) {
        return configuration == null || !configuration.isInitialized();
    }

    private static void initializeData() {
        initUsers();
        initCourses();
    }


    private static void initUsers() {
        UserRepository userRepository = new UserRepository();
        userRepository.createTable();
        if (userRepository.findByUserName("admin") == null) {
            new UserRepository().createUser("admin", "admin", "admin", "admin", "ADMIN");
        }
    }

    private static void initCourses() {
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.createTable();
    }

}
