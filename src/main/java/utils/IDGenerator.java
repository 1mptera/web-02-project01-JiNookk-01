package utils;

// TODO :
//  csv파일에서 ID를 읽어와서 새로운 아이디를 받아온다.

import utils.loader.ChattingRoomLoader;
import utils.loader.MessageLoader;
import utils.loader.UserLoader;

import java.io.FileNotFoundException;

public class IDGenerator {
    private long userId = 1;
    private long chattingRoomId = 1;
    private long messageId = 1;

    public long userID() {
        return userId;
    }

    public long chattingRoomId() {
        return chattingRoomId;
    }

    public long messageId() {
        return messageId;
    }

    public long newUserId() throws FileNotFoundException {
        UserLoader userLoader = new UserLoader();

        userId = userLoader.loadUserId();

        nextUserID();

        long newId = userID();

        return newId;
    }

    public void nextUserID() {
        userId += 1;
    }

    public long newChattingRoomId() throws FileNotFoundException {
        ChattingRoomLoader chattingRoomLoader = new ChattingRoomLoader();

        chattingRoomId = chattingRoomLoader.loadChattingRoomId();

        nextChattingRoomID();

        long newId = chattingRoomId();

        return newId;
    }

    public void nextChattingRoomID() {
        chattingRoomId += 1;
    }

    public void nextMessageID() {
        messageId += 1;
    }

    public long newMessageId() throws FileNotFoundException {
        MessageLoader messageLoader = new MessageLoader();

        chattingRoomId = messageLoader.loadMessageId();

        nextChattingRoomID();

        long newId = chattingRoomId();

        return newId;
    }
}
