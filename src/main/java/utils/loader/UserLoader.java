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

    public List<User> loadUsers() throws IOException {
        List<User> users = new ArrayList<>();

        File file = new File("./src/main/resources/DB/user.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Parser parser = new Parser();

            // TODO : 나중에 손봐야 할 profile -> 관계에 맞는 프로파일을 입력해주어야 한다.

            Profile defaultProfile = new Profile(1, false);

            User user = parser.parseUser(line, defaultProfile);

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
