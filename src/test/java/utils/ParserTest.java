package utils;

import models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parseUser() {
        Parser parser = new Parser();

        User user1 = parser.parseUser("1,ojw,ojw123,오진욱");

        User user2 = new User(2, "ojw", "ojw123", "오진욱");

        assertEquals(user2.userName(), user1.userName());
        assertEquals(user2.passWord(), user1.passWord());
        assertEquals(user2.name(), user1.name());
    }

    @Test
    void parseLine() {
        Parser parser = new Parser();

        String line = parser.parseLine(1, "ojw", "ojw123", "오진욱");

        assertEquals("1,ojw,ojw123,오진욱", line);
    }
}