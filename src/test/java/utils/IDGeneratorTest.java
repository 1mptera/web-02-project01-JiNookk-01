package utils;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class IDGeneratorTest {
    @Test
    void userId() {
        IDGenerator idGenerator = new IDGenerator();

        assertEquals(1, idGenerator.userID());

        idGenerator.nextUserID();

        assertEquals(2, idGenerator.userID());

        idGenerator.nextUserID();

        assertEquals(3, idGenerator.userID());
    }

    @Test
    void newID() throws FileNotFoundException {
        IDGenerator idGenerator = new IDGenerator();

        long id = idGenerator.newUserId();

//        assertEquals(4, id);
    }

    @Test
    void chattingRoomId() {
        IDGenerator idGenerator = new IDGenerator();

        assertEquals(1, idGenerator.chattingRoomId());

        idGenerator.nextChattingRoomID();

        assertEquals(2, idGenerator.chattingRoomId());

        idGenerator.nextChattingRoomID();

        assertEquals(3, idGenerator.chattingRoomId());
    }

    @Test
    void newChattingRoomId() throws FileNotFoundException {
        IDGenerator idGenerator = new IDGenerator();

//        assertEquals(1, idGenerator.newChattingRoomId());
    }

    @Test
    void messageId() {
        IDGenerator idGenerator = new IDGenerator();

        assertEquals(1, idGenerator.messageId());

        idGenerator.nextMessageID();

        assertEquals(2, idGenerator.messageId());

        idGenerator.nextMessageID();

        assertEquals(3, idGenerator.messageId());
    }

    @Test
    void newMessageId() throws FileNotFoundException {
        IDGenerator idGenerator = new IDGenerator();

//        assertEquals(2, idGenerator.newMessageId());
    }
}