package utils;

import models.Profile;
import models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserLoader {
    public List<User> loadUser() throws FileNotFoundException {
        List<User> users = new ArrayList<>();

        File file = new File("./src/main/resources/DB/user.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Parser parser = new Parser();

            User user = parser.parseUser(line);

            users.add(user);
        }

        return users;
    }

    public long loadUserId() throws FileNotFoundException {
        long userId = 0;

        File file = new File("./src/main/resources/DB/user.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            userId = Long.parseLong(line.split(",")[0]);
        }

        return userId;
    }

    public void saveUsers(List<User> users) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/main/resources/DB/user.csv");

        for (User user : users) {
            String line = user.toCsvRow();

            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }

    public Profile loadProfile() {
        return null;
    }
}
