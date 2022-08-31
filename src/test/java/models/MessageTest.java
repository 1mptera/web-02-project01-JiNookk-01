package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test
    void creation() {
        long id = 1;
        Message message = new Message(id,"content", "20220828",1);

        assertEquals("content",message.content());
        assertEquals("20220828",message.time());
        assertEquals(1,message.userId());

        assertEquals(new Message(id, "content", "20220828", 1), message);
    }
}