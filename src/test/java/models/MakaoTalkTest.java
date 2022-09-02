package models;

import models.ChattingRoom.ChattingRoom;
import models.Relation.UserChattingRoomRelation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MakaoTalkTest {
    @Test
    void users() throws IOException {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<User> users = makaoTalk.undeletedUsers();

        boolean deleted = false;
        User user1 = new User(1, "ojw0828", "1", "오진욱", "01085568965", new Profile(1, deleted));

        makaoTalk.addUser(user1);

        assertEquals(user1, makaoTalk.undeletedUsers().get(0));

        User user2 = new User(2, "ojs0828", "1", "오진성", "01052398955", new Profile(1, deleted));

        makaoTalk.addUser(user2);

        assertEquals(user2, makaoTalk.undeletedUsers().get(1));
    }

    @Test
    void currentUser() throws IOException {
        MakaoTalk makaoTalk = new MakaoTalk();

        boolean deleted = false;
        User loginUser = new User(1, "ojw0828", "1", "오진욱", "01085568965", new Profile(1, deleted));

        makaoTalk.updateLoginUserId(loginUser.id());

        assertEquals(1, makaoTalk.loginUserId());
    }

    @Test
    void chattingRooms() throws IOException {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1, "hi", "20220828", 1));
        messages.add(new Message(1, "why", "20220828", 2));

        boolean deleted = false;
        User currentUser = new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));
        User otherUser = new User(2, "ojs0828", "9645123", "오진성", "01052398955", new Profile(1, deleted));

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(currentUser);
        invitedUsers.add(otherUser);

        ChattingRoom chattingRoom1 = new ChattingRoom(
                1,
                "title", List.of());

        makaoTalk.addChattingRoom(chattingRoom1);

        assertEquals(chattingRoom1, makaoTalk.chattingRooms().get(0));
    }

    @Test
    void register() throws IOException {
        MakaoTalk makaotalk = new MakaoTalk();

        makaotalk.register("ojw", "ojw123", "오진욱", "phoneNumber");

//        assertEquals(List.of(new User(1, "ojw","ojw123","오진욱")),makaotalk.undeletedUsers());
    }

    @Test
    void login() throws IOException {
        MakaoTalk makaoTalk = new MakaoTalk();

        boolean deleted = false;
        User loginUser1 = new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));

        makaoTalk.login(loginUser1.id());

        assertEquals(1, makaoTalk.loginUserId());
        assertEquals(1, makaoTalk.relation().loginUserId());

        User loginUser2 = new User(2, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));

        makaoTalk.login(loginUser2.id());

        assertEquals(2, makaoTalk.loginUserId());
        assertEquals(2, makaoTalk.relation().loginUserId());
    }

    @Test
    void user() throws IOException {
        MakaoTalk makaoTalk = new MakaoTalk();

        boolean deleted = false;
        makaoTalk.addUser(new User(1, "ojw", "ojw123", "오진욱", "01085568965", new Profile(1, deleted)));
        makaoTalk.addUser(new User(2, "ojs", "ojw123", "오진성", "01085568965", new Profile(1, deleted)));
        makaoTalk.addUser(new User(3, "oja", "ojw123", "오진어", "01085568965", new Profile(1, deleted)));

        assertEquals(new User(1, "ojw", "ojw123", "오진욱", "01085568965", new Profile(1, deleted)), makaoTalk.user(1));
        assertEquals(new User(2, "ojw", "ojw123", "오진욱", "01085568965", new Profile(1, deleted)), makaoTalk.user(2));
        assertEquals(new User(3, "ojw", "ojw123", "오진욱", "01085568965", new Profile(1, deleted)), makaoTalk.user(3));
    }

    @Test
    void messageOwnerName() throws IOException {
        MakaoTalk makaoTalk = new MakaoTalk();

        boolean deleted = false;
        makaoTalk.addUser(new User(1, "ojw", "ojw123", "오진욱", "01085568965", new Profile(1, deleted)));
        makaoTalk.addUser(new User(2, "ojs", "ojw123", "오진성", "01085568965", new Profile(1, deleted)));
        makaoTalk.addUser(new User(3, "oja", "ojw123", "오진어", "01085568965", new Profile(1, deleted)));

        String messageOwner = makaoTalk.messageOwnerName(new Message(1, "content", "20220828", 1));

        assertEquals("오진욱", messageOwner);
    }

    @Test
    void newChattingRoom() throws IOException {
        MakaoTalk makaoTalk = new MakaoTalk();

        boolean deleted = false;
        makaoTalk.addUser(new User(1, "ojw", "ojw123", "오진욱", "01085568965", new Profile(1, deleted)));
        makaoTalk.addUser(new User(2, "ojs", "ojw123", "오진성", "01085568965", new Profile(1, deleted)));
        makaoTalk.addUser(new User(3, "oja", "ojw123", "오진어", "01085568965", new Profile(1, deleted)));
        makaoTalk.addUser(new User(4, "ojb", "ojw123", "오진배", "01085568965", new Profile(1, deleted)));

        List<Invitation> invitations = List.of(
                new Invitation(2),
                new Invitation(3)
        );

        List<User> invitedUsers = List.of(
                new User(2, "ojs", "ojw123", "오진성", "01085568965", new Profile(1, deleted)),
                new User(3, "oja", "ojw123", "배준형", "01085568965", new Profile(1, deleted))
        );

        assertEquals("오진성,오진어", makaoTalk.newChatting(invitations,"일반").title());
        assertEquals(invitedUsers, makaoTalk.newChatting(invitations,"일반").invitedUsers());
    }

    @Test
    void loginUserChattingRooms() throws IOException {
        MakaoTalk makaoTalk = new MakaoTalk();

        makaoTalk.login(1);

        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(1, 1));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(2, 1));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(3, 1));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(1, 2));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(2, 2));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(2, 3));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(3, 3));

        boolean deleted = false;
        User user1 = new User(1, "ojw", "ojw123", "오진욱", "01085568965", new Profile(1, deleted));
        User user2 = new User(2, "ojs", "ojs123", "오진성", "01052398955", new Profile(1, deleted));
        User user3 = new User(3, "bjh", "bjh123", "배준형", "01024593050", new Profile(1, deleted));

        List<User> invitedUsers1 = List.of(user1, user2, user3);
        List<User> invitedUsers2 = List.of(user1, user2);

        makaoTalk.addChattingRoom(new ChattingRoom(1, "", invitedUsers1));
        makaoTalk.addChattingRoom(new ChattingRoom(2, "", invitedUsers2));

        List<ChattingRoom> loginUserChattingRooms = makaoTalk.relativeChattingRooms();

        assertEquals(List.of(
                new ChattingRoom(1, "첫 번쨰", invitedUsers1),
                new ChattingRoom(2, "두 번쨰", invitedUsers2)
        ), loginUserChattingRooms);
    }

    @Test
    void currentChattingRoomWithoutMessages() {
        MakaoTalk makaoTalk = new MakaoTalk();

        assertEquals(List.of(), makaoTalk.currentChattingRoomMessages());
    }

    @Test
    void currentChattingRoomOneMessage() {
        MakaoTalk makaoTalk = new MakaoTalk();

        makaoTalk.addMessage(new Message(2, "", "", 1));
        makaoTalk.addMessage(new Message(3, "", "", 1));
        makaoTalk.addMessage(new Message(4, "", "", 1));
        makaoTalk.addMessage(new Message(5, "", "", 1));
        makaoTalk.addMessage(new Message(6, "", "", 1));
        makaoTalk.addMessage(new Message(7, "", "", 1));

        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(1, "", List.of()),
                new Message(2, "", "", 1));
        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(2, "", List.of()),
                new Message(3, "", "", 1));
        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(3, "", List.of()),
                new Message(4, "", "", 1));
        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(4, "", List.of()),
                new Message(5, "", "", 1));
        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(1, "", List.of()),
                new Message(6, "", "", 1));
        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(2, "", List.of()),
                new Message(7, "", "", 1));

        makaoTalk.relation().updateCurrentChattingRoom(1);

        assertEquals(List.of(
                        new Message(2, "", "", 1),
                        new Message(6, "", "", 1)
                ),
                makaoTalk.currentChattingRoomMessages()
        );
    }
}
