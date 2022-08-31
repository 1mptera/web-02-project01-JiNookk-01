package models;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PictureTest {
    @Test
    void creation() throws IOException {
        Picture picture = new Picture();

        assertEquals("./src/main/resources/images/defaultProfileImage.png", picture.imagePath());
        assertNotNull(picture.profileImageIcon());
    }

}