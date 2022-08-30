package utils.loader;

import models.Profile;
import models.Relation.UsersRelation;
import models.User;
import utils.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserLoader {
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

    public void saveUsers(List<User> users) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/main/resources/DB/user.csv");

        for (User user : users) {
            String line = user.toCsvRow();

            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }

    public List<UsersRelation> loadUserRelations() throws FileNotFoundException {
        List<UsersRelation> userRelations = new ArrayList<>();

        File file = new File("./src/main/resources/relations/usersRelations.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Parser parser = new Parser();

            UsersRelation usersRelation = parser.parseUserRelation(line);

            userRelations.add(usersRelation);
        }

        return userRelations;
    }

    public void saveUsersRelations(List<UsersRelation> usersRelations) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/main/resources/relations/usersRelations.csv");

        for (UsersRelation userRelation : usersRelations) {
            String line = userRelation.toCsvRow();

            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }
}
