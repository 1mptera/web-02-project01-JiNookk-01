package models;

import models.ChattingRoom.ChattingRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MakaoTalkTest {
    @Test
    void users() {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<User> users = makaoTalk.users();

        User user1 = new User(1, "ojw0828", "1", "오진욱");

        makaoTalk.addUser(user1);

        assertEquals(user1, users.get(0));

        User user2 = new User(2, "ojs0828", "1", "오진성");

        makaoTalk.addUser(user2);

        assertEquals(user2, users.get(1));
    }

    @Test
    void currentUser() {
        MakaoTalk makaoTalk = new MakaoTalk();

        User currentUser = new User(1, "ojw0828", "1", "오진욱");

        makaoTalk.setCurrentUser(currentUser);

        assertEquals(currentUser,makaoTalk.currentUser());
    }

    @Test
    void chattingRooms() {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("hi","20220828","ojw0828"));
        messages.add(new Message("why","20220828","ojs0828"));

        User currentUser = new User(1, "ojw0828", "7895123", "오진욱");
        User otherUser = new User(2, "ojs0828", "9645123", "오진성");

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(currentUser);
        invitedUsers.add(otherUser);

        ChattingRoom chattingRoom1 = new ChattingRoom(
                invitedUsers,
                currentUser,
                messages
        );

        makaoTalk.addChattingRoom(chattingRoom1);

        assertEquals(chattingRoom1,makaoTalk.chattingRooms().get(0));
    }

    @Test
    void friends() {
        MakaoTalk makaoTalk = new MakaoTalk();

        User user = new User(1, "ojw0828", "7895123", "오진욱");

        makaoTalk.addFriend(user);

        assertEquals(user, makaoTalk.friends().get(0));
    }

    @Test
    void register() throws IOException {
        MakaoTalk makaotalk = new MakaoTalk();

        makaotalk.register("ojw","ojw123","오진욱");

//        assertEquals(List.of(new User(1, "ojw","ojw123","오진욱")),makaotalk.users());
    }
}