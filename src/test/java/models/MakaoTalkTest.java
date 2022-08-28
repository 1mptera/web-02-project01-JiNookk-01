package models;

import models.ChattingRoom.ChattingRoom;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MakaoTalkTest {
    @Test
    void users() {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<User> users = makaoTalk.users();

        User user1 = new User("ojw0828", "1", "오진욱", new Profile());

        makaoTalk.addUsers(user1);

        assertEquals(user1, users.get(0));

        User user2 = new User("ojs0828", "1", "오진성", new Profile());

        makaoTalk.addUsers(user2);

        assertEquals(user2, users.get(1));
    }

    @Test
    void currentUser() {
        MakaoTalk makaoTalk = new MakaoTalk();

        User currentUser = new User("ojw0828", "1", "오진욱", new Profile());

        makaoTalk.setCurrentUser(currentUser);

        assertEquals(currentUser,makaoTalk.currentUser());
    }

    @Test
    void chattingRooms() {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("hi","20220828","ojw0828"));
        messages.add(new Message("why","20220828","ojs0828"));

        User currentUser = new User("ojw0828", "7895123", "오진욱", new Profile());
        User otherUser = new User("ojs0828", "9645123", "오진성", new Profile());

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(currentUser);
        invitedUsers.add(otherUser);

        ChattingRoom chattingRoom1 = new ChattingRoom(
                invitedUsers,
                currentUser,
                messages,
                "title",
                "previewMessage");

        makaoTalk.addChattingRoom(chattingRoom1);

        assertEquals(chattingRoom1,makaoTalk.chattingRooms().get(0));
    }

    @Test
    void friends() {
        MakaoTalk makaoTalk = new MakaoTalk();

        User user = new User("ojw0828", "7895123", "오진욱", new Profile());

        makaoTalk.addFriend(user);

        assertEquals(user, makaoTalk.friends().get(0));
    }
}