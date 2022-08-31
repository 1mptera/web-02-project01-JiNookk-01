package utils.loader;

import models.ChattingRoom.ChattingRoom;
import models.Relation.UserChattingRoomRelation;
import models.User;
import utils.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChattingRoomLoader {
    public long loadChattingRoomId() throws FileNotFoundException {
        long chattingRoomId = 0;

        File file = new File("./src/main/resources/DB/chattingRooms.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            chattingRoomId = Long.parseLong(line.split("/")[0]);
        }

        return chattingRoomId;
    }

    public List<ChattingRoom> loadChattingRoom(List<User> users,
                                               List<UserChattingRoomRelation> userToChattingRoomRelations)
            throws FileNotFoundException {
        List<ChattingRoom> chattingRooms = new ArrayList<>();

        File file = new File("./src/main/resources/DB/chattingRooms.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Parser parser = new Parser();

            ChattingRoom chattingRoom = parser.parseChattingRoom(line, users, userToChattingRoomRelations);

            chattingRooms.add(chattingRoom);
        }

        return chattingRooms;
    }

    public void saveChattingRooms(List<ChattingRoom> chattingRooms) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/main/resources/DB/chattingRooms.csv");

        for (ChattingRoom chattingRoom : chattingRooms) {
            String line = chattingRoom.toCsvRow();

            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }

    public List<UserChattingRoomRelation> loadUserToChattingRoomRelations() throws FileNotFoundException {
        List<UserChattingRoomRelation> userToChattingRoomRelations = new ArrayList<>();

        File file = new File("./src/main/resources/relations/userToChattingRoomRelations.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Parser parser = new Parser();

            UserChattingRoomRelation userChattingRoomRelation = parser.parseUserChattingRoomRelation(line);

            userToChattingRoomRelations.add(userChattingRoomRelation);
        }

        return userToChattingRoomRelations;
    }

    public void saveUserChattingRoomRelations(List<UserChattingRoomRelation> userChattingRoomRelations) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/main/resources/relations/userToChattingRoomRelations.csv");

        for (UserChattingRoomRelation userChattingRoomRelation : userChattingRoomRelations) {
            String line = userChattingRoomRelation.toCsvRow();

            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }
}
