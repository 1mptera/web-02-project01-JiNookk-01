package utils;

// TODO :
//  csv파일에서 ID를 읽어와서 새로운 아이디를 받아온다.

import java.io.FileNotFoundException;

public class IDGenerator {
    private long userId = 1;

    public long newUserId() throws FileNotFoundException {
        UserLoader userLoader = new UserLoader();

        userId = userLoader.loadUserId();

        nextID();

        long newId = userID();

        return newId;
    }

    public void nextID() {
        userId += 1;
    }

    public long userID() {
        return userId;
    }
}
