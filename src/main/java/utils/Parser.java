package utils;

import models.Relation.UsersRelation;
import models.User;

public class Parser {
    public User parseUser(String line) {
        String[] words = line.split(",");

        long id = Long.parseLong(words[0]);
        String username = words[1];
        String password = words[2];
        String nickname = words[3];
        String phoneNumber = words[4];

        User user = new User(id, username, password, nickname, phoneNumber);
        return user;
    }

    public String parseLine(long id, String userName, String password, String nickName, String phoneNumber) {
        return id + "," + userName + "," + password + "," + nickName + "," + phoneNumber;
    }

    public UsersRelation parseUserRelation(String line) {
        String[] words = line.split(",");

        long myId = Long.parseLong(words[0]);
        long friendId = Long.parseLong(words[1]);

        return new UsersRelation(myId,friendId);
    }
}
