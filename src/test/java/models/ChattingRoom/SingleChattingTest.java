package models.ChattingRoom;

import models.Message;
import models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SingleChattingTest {
    @Test
    void creation() {
        List<Message> messages = new ArrayList<>();

        User currentUser = new User(1, "ojw0828", "7895123", "오진욱");
        User otherUser = new User(2, "ojs0828", "9645123", "오진성");

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(currentUser);
        invitedUsers.add(otherUser);

        SingleChatting singleChatting = new SingleChatting(invitedUsers,currentUser,messages);

        Message message1 = new Message("hi", "20220828", "ojw0828");
        Message message2 = new Message("why", "20220828", "ojs0828");
        singleChatting.receiveMessage(message1);
        singleChatting.receiveMessage(message2);

        assertEquals(List.of(currentUser, otherUser), singleChatting.invitedUsers());
        assertEquals(currentUser, singleChatting.currentUser());
        assertEquals(List.of(message1, message2), singleChatting.messages());
        assertEquals("오진성", singleChatting.title());
        assertEquals("why", singleChatting.previewMessage());
    }

    @Test
    void setTitle() {
        List<Message> messages = new ArrayList<>();

        User currentUser = new User(1, "ojw0828", "7895123", "오진욱");
        User otherUser = new User(2, "ojs0828", "9645123", "오진성");

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(currentUser);
        invitedUsers.add(otherUser);

        SingleChatting singleChatting = new SingleChatting(invitedUsers, currentUser, messages);

        assertEquals("오진성",singleChatting.title());
    }

    @Test
    void otherUser() {
        List<Message> messages = new ArrayList<>();

        User currentUser = new User(1, "ojw0828", "7895123", "오진욱");
        User otherUser = new User(2, "ojs0828", "9645123", "오진성");

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(currentUser);
        invitedUsers.add(otherUser);

//        SingleChatting singleChatting = new SingleChatting(invitedUsers,currentUser,messages);
//
//        assertEquals(otherUser.userName(),singleChatting.otherUser().userName());
    }
}