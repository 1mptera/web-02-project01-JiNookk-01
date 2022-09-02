package models.ChattingRoom;

import models.Profile;
import models.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChattingRoomTest {
    @Test
    void creation() throws IOException {
        boolean deleted = false;
        List<User> invitedUsers = List.of(
                new User(1, "ojw0828", "1", "오진욱", "01085568965", new Profile(1, deleted)),
                new User(2, "ojw0828", "1", "오진욱", "01085568965", new Profile(1, deleted))
        );
        ChattingRoom chattingRoom = new ChattingRoom(1, "title", invitedUsers);

        assertEquals(new ChattingRoom(1, "title", invitedUsers).id(), chattingRoom.id());
        assertEquals(invitedUsers, chattingRoom.invitedUsers());
    }

    @Test
    void updateTitle() {
        ChattingRoom chattingRoom = new ChattingRoom(1, "title", List.of());

        assertEquals("title", chattingRoom.title());

        chattingRoom.updateTitle("Hi");

        assertEquals("Hi", chattingRoom.title());
    }

    @Test
    void updatePreviewMessage() {
        ChattingRoom chattingRoom = new ChattingRoom(1, "title", List.of());

        assertEquals("", chattingRoom.previewMessage());

        chattingRoom.updatePreviewMessage("Hi");

        assertEquals("Hi", chattingRoom.previewMessage());
    }

    @Test
    void toCsvRow() {
        ChattingRoom chattingRoom = new ChattingRoom(1, "title", List.of());

        chattingRoom.updatePreviewMessage("Hi");

        assertEquals("1/title/Hi/일반", chattingRoom.toCsvRow());
    }
}
