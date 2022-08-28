package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {
    @Test
    void creation() {
        Profile profile = new Profile();

        assertEquals("", profile.profileMessage());
        assertNull(profile.profilePicture());
        assertNull(profile.profileMusic());
    }

}