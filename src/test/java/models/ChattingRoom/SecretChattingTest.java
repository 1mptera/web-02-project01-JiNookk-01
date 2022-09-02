package models.ChattingRoom;

import models.Profile;
import models.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SecretChattingTest {
    @Test
    void creation() throws IOException {
        SecretChatting secret = new SecretChatting(1, "title", List.of());

        assertEquals(1,secret.id());
        assertEquals("title", secret.title());
        assertEquals(List.of(), secret.invitedUsers());
        assertEquals("비밀",secret.type());
//        assertEquals("비밀메시지",secret.previewMessage());
    }

    @Test
    void updatePreviewMessage() throws IOException {
        SecretChatting secret = new SecretChatting(1, "title", List.of());

        assertEquals("",secret.previewMessage());

        secret.updatePreviewMessage("");

        assertEquals("비밀메시지",secret.previewMessage());
    }
}