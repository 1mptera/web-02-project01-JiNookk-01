package utils;

import models.Relation.UsersRelation;
import models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parseUser() {
        Parser parser = new Parser();

        User user1 = parser.parseUser("1,ojw,ojw123,오진욱,01085568965");

        User user2 = new User(2, "ojw", "ojw123", "오진욱", "01085568965");

        assertEquals(user2.userName(), user1.userName());
        assertEquals(user2.passWord(), user1.passWord());
        assertEquals(user2.name(), user1.name());
    }

    @Test
    void parseLine() {
        Parser parser = new Parser();

        String line = parser.parseLine(1, "ojw", "ojw123", "오진욱","01085568965");

        assertEquals("1,ojw,ojw123,오진욱,01085568965", line);
    }

    @Test
    void parseUserRelation() {
        Parser parser = new Parser();

        UsersRelation userRelation1 = parser.parseUserRelation("1,2");

        UsersRelation userRelation2 = new UsersRelation(1,2);

        assertEquals(userRelation1,userRelation2);
    }
}