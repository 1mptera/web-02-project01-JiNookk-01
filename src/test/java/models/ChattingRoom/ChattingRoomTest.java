package models.ChattingRoom;

import models.Message;
import models.Profile;
import models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChattingRoomTest {
    @Test
    void chattingRoom() {
        Message message1 = new Message("hi","20220828","ojw0828");
        Message message2 = new Message("why","20220828","ojs0828");
        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);

//        messages.equals(List.of());       //List의 equals는 또 다르네!!

        User currentUser = new User("ojw0828", "7895123", "오진욱", new Profile());
        User otherUser = new User("ojs0828", "9645123", "오진성", new Profile());

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(currentUser);
        invitedUsers.add(otherUser);

        ChattingRoom chattingRoom = new ChattingRoom(
                invitedUsers,
                currentUser,
                messages,
                "title",
                "previewMessage");

        assertEquals(List.of(currentUser, otherUser), chattingRoom.invitedUsers());
        assertEquals(currentUser, chattingRoom.currentUser());
        assertEquals(List.of(message1, message2), chattingRoom.messages());
        assertEquals("title", chattingRoom.title());
        assertEquals("previewMessage", chattingRoom.previewMessage());
    }

    @Test
    void addMessage() {
        List<Message> messages = new ArrayList<>();
        ChattingRoom chattingRoom = new ChattingRoom(null, null, messages, null, null);

        chattingRoom.addMessage("Hi", "22.08.28", "ojw0828");
        chattingRoom.addMessage("Hi", "22.08.29", "ojw0828");

        assertEquals(new Message("Hi", "22.08.28", "ojw0828"),
                chattingRoom.messages().get(0));
        assertEquals(new Message("Hi", "22.08.29", "ojw0828"),
                chattingRoom.messages().get(1));
    }
}