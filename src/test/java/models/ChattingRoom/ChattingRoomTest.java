package models.ChattingRoom;

import models.Message;
import models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChattingRoomTest {
    @Test
    void chattingRoom() {
        List<Message> messages = new ArrayList<>();

        User currentUser = new User(1, "ojw0828", "7895123", "오진욱", "01085568965");
        User otherUser = new User(2, "ojs0828", "9645123", "오진성", "01076308965");

        List<User> invitedUsers = new ArrayList<>();

        ChattingRoom chattingRoom = new ChattingRoom(
                invitedUsers,
                currentUser,
                messages
        );

        chattingRoom.invite(currentUser);
        chattingRoom.invite(otherUser);

        Message message1 = new Message("hi", "20220828", "ojw0828");
        Message message2 = new Message("why", "20220828", "ojs0828");
        chattingRoom.receiveMessage(message1);
        chattingRoom.receiveMessage(message2);

        assertEquals(List.of(currentUser, otherUser), chattingRoom.invitedUsers());
        assertEquals(currentUser, chattingRoom.currentUser());
        assertEquals(List.of(message1, message2), chattingRoom.messages());
        assertEquals("오진욱, 오진성", chattingRoom.title());
        assertEquals("why", chattingRoom.previewMessage());
        assertEquals("single", chattingRoom.type());

        chattingRoom.invite(new User(3, "bjh0828", "123", "배준형", "01032921537"));

        assertEquals("multi", chattingRoom.type());
    }

    @Test
    void addMessage() {
        List<Message> messages = new ArrayList<>();
        ChattingRoom chattingRoom = new ChattingRoom(new ArrayList<>(), null, messages);

        chattingRoom.receiveMessage(new Message("Hi", "22.08.28", "ojw0828"));
        chattingRoom.receiveMessage(new Message("Hi", "22.08.29", "ojw0828"));

        assertEquals(new Message("Hi", "22.08.28", "ojw0828"),
                chattingRoom.messages().get(0));
        assertEquals(new Message("Hi", "22.08.29", "ojw0828"),
                chattingRoom.messages().get(1));
    }

    @Test
    void defaultTitle() {
        List<User> invited = new ArrayList<>();
        User jinwook = new User(1, "ojw0828", "7895123", "오진욱", "01085568965");
        invited.add(jinwook);
        invited.add(new User(2, "ojs0828", "9645123", "오진성", "01076308965"));

        ChattingRoom chattingRoom = new ChattingRoom(invited, jinwook, new ArrayList<>());

        assertEquals("오진성", chattingRoom.updateTitle());

        chattingRoom.invite(new User(3, "bjh0828", "9645114", "배준형", "01032921537"));

        assertEquals("오진욱, 오진성, 배준형", chattingRoom.updateTitle());
    }

    @Test
    void invite() {
        List<User> invited = new ArrayList<>();
        User jinwook = new User(1, "ojw0828", "7895123", "오진욱", "01085568965");
        invited.add(jinwook);

        ChattingRoom chattingRoom = new ChattingRoom(invited, jinwook, new ArrayList<>());

        assertEquals(List.of(jinwook), chattingRoom.invitedUsers());

        User jinseong = new User(2, "ojs0828", "9645123", "오진성", "01076308965");
        chattingRoom.invite(jinseong);

        assertEquals(List.of(jinwook, jinseong), chattingRoom.invitedUsers());
    }

    @Test
    void messageOwnerName() {
        List<Message> messages = new ArrayList<>();
        Message message1 = new Message("hi", "20220828", "ojw0828");
        Message message2 = new Message("why", "20220828", "ojs0828");

        User currentUser = new User(1, "ojw0828", "7895123", "오진욱", "01085568965");
        User otherUser = new User(2, "ojs0828", "9645123", "오진성", "01076308965");

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(currentUser);
        invitedUsers.add(otherUser);

        ChattingRoom chattingRoom = new ChattingRoom(
                invitedUsers,
                currentUser,
                messages
        );

        assertEquals("오진욱", chattingRoom.messageOwnerName(message1));
        assertEquals("오진성", chattingRoom.messageOwnerName(message2));
    }
}