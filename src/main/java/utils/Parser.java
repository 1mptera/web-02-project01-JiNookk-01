package utils;

import models.User;

public class Parser {
    public User parseUser(String line) {
        String[] words = line.split(",");

        long id = Long.parseLong(words[0]);
        String username = words[1];
        String password = words[2];
        String nickname = words[3];

        User user = new User(id,username,password,nickname);
        return user;
    }

    public String parseLine(long id, String userName, String password, String nickName) {
        return id + "," + userName + "," + password + "," + nickName;
    }
}
