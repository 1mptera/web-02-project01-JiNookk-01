package models;

import models.ChattingRoom.ChattingRoom;
import models.Relation.UsersRelation;
import org.junit.jupiter.api.Test;
import utils.OverlapValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MakaoTalkTest {
    @Test
    void users() {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<User> users = makaoTalk.users();

        User user1 = new User(1, "ojw0828", "1", "오진욱", "01085568965");

        makaoTalk.addUser(user1);

        assertEquals(user1, users.get(0));

        User user2 = new User(2, "ojs0828", "1", "오진성", "01052398955");

        makaoTalk.addUser(user2);

        assertEquals(user2, users.get(1));
    }

    @Test
    void currentUser() {
        MakaoTalk makaoTalk = new MakaoTalk();

        User loginUser = new User(1, "ojw0828", "1", "오진욱", "01085568965");

        makaoTalk.updateLoginUserId(loginUser.id());

        assertEquals(1, makaoTalk.loginUserId());
    }

    @Test
    void chattingRooms() {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("hi", "20220828", "ojw0828"));
        messages.add(new Message("why", "20220828", "ojs0828"));

        User currentUser = new User(1, "ojw0828", "7895123", "오진욱", "01085568965");
        User otherUser = new User(2, "ojs0828", "9645123", "오진성", "01052398955");

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(currentUser);
        invitedUsers.add(otherUser);

        ChattingRoom chattingRoom1 = new ChattingRoom(
                invitedUsers,
                currentUser,
                messages
        );

        makaoTalk.addChattingRoom(chattingRoom1);

        assertEquals(chattingRoom1, makaoTalk.chattingRooms().get(0));
    }

    @Test
    void register() throws IOException {
        MakaoTalk makaotalk = new MakaoTalk();

        makaotalk.register("ojw", "ojw123", "오진욱", "phoneNumber");

//        assertEquals(List.of(new User(1, "ojw","ojw123","오진욱")),makaotalk.users());
    }

    @Test
    void login() {
        MakaoTalk makaoTalk = new MakaoTalk();

        User loginUser1 = new User(1, "ojw0828", "7895123", "오진욱", "01085568965");

        makaoTalk.login(loginUser1.id());

        assertEquals(1, makaoTalk.loginUserId());

        User loginUser2 = new User(2, "ojw0828", "7895123", "오진욱", "01085568965");

        makaoTalk.login(loginUser2.id());

        assertEquals(2, makaoTalk.loginUserId());
    }

    @Test
    void requestFriend() {
        MakaoTalk makaoTalk = new MakaoTalk();

        User user1 = new User(1, "ojw0828", "7895123", "오진욱", "01085568965");
        User user2 = new User(2, "ojw0828", "7895123", "오진욱", "01085568965");
        User user3 = new User(3, "ojw0828", "7895123", "오진욱", "01085568965");

        makaoTalk.login(user1.id());
        makaoTalk.requestFriend(user2.id());

        List<UsersRelation> usersRelations = makaoTalk.usersRelations();

        UsersRelation usersRelation1To2 = usersRelations.get(0);

        assertEquals(1, usersRelation1To2.myId());
        assertEquals(2, usersRelation1To2.friendId());

        makaoTalk.requestFriend(user3.id());

        UsersRelation usersRelation1To3 = usersRelations.get(1);

        assertEquals(1, usersRelation1To3.myId());
        assertEquals(3, usersRelation1To3.friendId());

        makaoTalk.login(user2.id());

        makaoTalk.requestFriend(user3.id());

        UsersRelation usersRelation2To3 = usersRelations.get(2);

        assertEquals(2, usersRelation2To3.myId());
        assertEquals(3, usersRelation2To3.friendId());
    }

    @Test
    void loginUserFriends() {
        MakaoTalk makaoTalk = new MakaoTalk();

        makaoTalk.login(1);

        makaoTalk.addUsersRelation(new UsersRelation(1, 4));
        makaoTalk.addUsersRelation(new UsersRelation(2, 3));
        makaoTalk.addUsersRelation(new UsersRelation(1, 2));
        makaoTalk.addUsersRelation(new UsersRelation(3, 1));
        makaoTalk.addUsersRelation(new UsersRelation(1, 6));
        makaoTalk.addUsersRelation(new UsersRelation(2, 5));

        assertEquals(List.of(
                        new UsersRelation(1, 4),
                        new UsersRelation(1, 2),
                        new UsersRelation(1, 6)
                ),
                makaoTalk.loginUserFriends()
        );
    }

    @Test
    void user() {
        MakaoTalk makaoTalk = new MakaoTalk();

        makaoTalk.addUser(new User(1, "ojw", "ojw123", "오진욱", "01085568965"));
        makaoTalk.addUser(new User(2, "ojs", "ojw123", "오진성", "01085568965"));
        makaoTalk.addUser(new User(3, "oja", "ojw123", "오진어", "01085568965"));

        assertEquals(new User(1, "ojw", "ojw123", "오진욱", "01085568965"), makaoTalk.user(1));
        assertEquals(new User(2, "ojw", "ojw123", "오진욱", "01085568965"), makaoTalk.user(2));
        assertEquals(new User(3, "ojw", "ojw123", "오진욱", "01085568965"), makaoTalk.user(3));
    }
}
