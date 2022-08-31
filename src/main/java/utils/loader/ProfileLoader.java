package utils.loader;

import models.Profile;
import models.User;
import utils.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfileLoader {

    public List<Profile> loadProfiles() throws IOException {
        List<Profile> profiles = new ArrayList<>();

        File file = new File("./src/main/resources/DB/profile.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Parser parser = new Parser();

            Profile profile = parser.parseProfile(line);

            profiles.add(profile);
        }
        return profiles;
    }

    public void saveProfiles(List<Profile> profiles) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/main/resources/DB/profile.csv");

        for (Profile profile : profiles) {
            String line = profile.toCsvRow();

            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }
}
