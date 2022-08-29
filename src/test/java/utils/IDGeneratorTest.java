package utils;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class IDGeneratorTest {
    @Test
    void userId() {
        IDGenerator idGenerator = new IDGenerator();

        assertEquals(1, idGenerator.userID());

        idGenerator.nextID();

        assertEquals(2, idGenerator.userID());

        idGenerator.nextID();

        assertEquals(3, idGenerator.userID());
    }

    @Test
    void newID() throws FileNotFoundException {
        IDGenerator idGenerator = new IDGenerator();

        long id = idGenerator.newUserId();

//        assertEquals(4, id);
    }

}