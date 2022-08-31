package utils.loader;

import models.Message;
import models.Relation.ChattingRoomMessageRelation;
import models.Relation.UsersRelation;
import utils.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessageLoader {
    public long loadMessageId() throws FileNotFoundException {
        long chattingRoomId = 0;

        File file = new File("./src/main/resources/DB/messages.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            chattingRoomId = Long.parseLong(line.split(",")[0]);
        }

        return chattingRoomId;
    }

    public List<Message> loadMessages() throws FileNotFoundException {
        List<Message> messages = new ArrayList<>();

        File file = new File("./src/main/resources/DB/messages.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Parser parser = new Parser();

            Message message = parser.parseMessage(line);

            messages.add(message);
        }

        return messages;
    }

    public void saveMessage(List<Message> messages) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/main/resources/DB/messages.csv");

        for (Message message : messages) {
            String line = message.toCsvRow();

            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }

    public List<ChattingRoomMessageRelation> loadChattingRoomMessageRelations() throws IOException {
        List<ChattingRoomMessageRelation> chattingRoomMessageRelations = new ArrayList<>();

        File file = new File("./src/main/resources/relations/chattingRoomToMessageRelations.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Parser parser = new Parser();

            ChattingRoomMessageRelation chattingRoomMessageRelation = parser.parseChattingRoomMessageRelation(line);

            chattingRoomMessageRelations.add(chattingRoomMessageRelation);
        }

        return chattingRoomMessageRelations;
    }

    public void saveChattingRoomMessageRelations(
            List<ChattingRoomMessageRelation> chattingRoomMessageRelations) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/main/resources/relations/chattingRoomToMessageRelations.csv");

        for (ChattingRoomMessageRelation chattingRoomMessageRelation : chattingRoomMessageRelations) {
            String line = chattingRoomMessageRelation.toCsvRow();

            fileWriter.write(line + "\n");
        }

        fileWriter.close();
    }
}
