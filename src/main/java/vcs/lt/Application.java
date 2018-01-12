package vcs.lt;

import vcs.lt.model.Admin;
import vcs.lt.model.User;
import vcs.lt.utils.IOObjectStreamUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Application {
    static void initialize() {
        //Bandom nuskaityti pirmą objektą iš configuration failo.
        try {
            Configuration configuration = (Configuration) IOObjectStreamUtils.readFirstObjectFromFile("configuration");
            //Jei egzistuoja configuration objektas ir applikacija jau buvo inicializuota, nieko toliau nevykdome ir nepridedame admin userio.
            if (configuration != null && configuration.isInitialized()){
                return;
            }
        } catch (FileNotFoundException e) {
            //Jei configuration failas nubuvo rastas, mes sukuriam Configuration klasę ir ją įrašom į configuration failą.
            Configuration configuration = new Configuration();
            configuration.setInitialized(true);
            IOObjectStreamUtils.writeObjectToFile("configuration", configuration);
        }
        //Inicializuojame programos pradinius duomenis
        initializeData();
    }

    private static void initializeData() {
        //Sukuriame Admin ir įdedame į HashMapą, kurį išsaugome users faile
        HashMap<String, User> users = new HashMap<>();
        users.put("admin", new Admin("admin", "Super", "Admin", "admin"));
        IOObjectStreamUtils.writeObjectToFile("users", users);
    }
}
