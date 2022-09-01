package models;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {
    @Test
    void creation() throws IOException {
        long id = 1;
        boolean deleted = false;
        Profile profile = new Profile(id, deleted);

        assertEquals("", profile.message());
        assertNotNull(profile.picture());
        assertNull(profile.music());
        assertEquals(false,profile.deleted());
    }

    @Test
    void setProfileMessage() throws IOException {
        long id = 1;
        boolean deleted = false;
        Profile profile = new Profile(id, deleted);

        profile.updateProfileMessage("Hello");

        assertEquals("Hello", profile.message());
    }

    @Test
    void toCsvRow() throws IOException {
        boolean deleted = false;
        Profile profile1 = new Profile(1, deleted);

        assertEquals("1,,./src/main/resources/images/defaultProfileImage.png,false", profile1.toCsvRow());

        Profile profile2 = new Profile(2, deleted);

        assertEquals("2,,./src/main/resources/images/defaultProfileImage.png,false", profile2.toCsvRow());
    }

    @Test
    void delete() throws IOException {
        boolean deleted = false;
        Profile profile = new Profile(1, deleted);

        profile.delete();

        assertTrue(profile.deleted());
    }
}