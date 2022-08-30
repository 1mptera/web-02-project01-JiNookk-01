package models.ChattingRoom;

import models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChattingRoomTest {
    @Test
    void creation() {
        List<User> invitedUsers = List.of(
                new User(1, "ojw0828", "1", "오진욱", "01085568965"),
                new User(2, "ojw0828", "1", "오진욱", "01085568965")
        );
        ChattingRoom chattingRoom = new ChattingRoom(1, "title", invitedUsers);

        assertEquals(new ChattingRoom(1, "title", invitedUsers).id(), chattingRoom.id());
        assertEquals(invitedUsers, chattingRoom.invitedUsers());
    }


}