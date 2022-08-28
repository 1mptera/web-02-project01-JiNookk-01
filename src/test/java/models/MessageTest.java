package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test
    void creation() {
        Message message = new Message("content", "20220828","ojw0828");

        assertEquals("content",message.content());
        assertEquals("20220828",message.time());
        assertEquals("ojw0828",message.userId());

        assertEquals(new Message("content", "20220828", "ojw0828"), message);
    }

}