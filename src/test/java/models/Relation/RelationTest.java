package models.Relation;

import models.ChattingRoom.ChattingRoom;
import models.MakaoTalk;
import models.Message;
import models.Profile;
import models.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelationTest {

    @Test
    void loginUserChattingRoomRelations() {
        Relation relation = new Relation();

        UserChattingRoomRelation userChattingRoomRelation = new UserChattingRoomRelation(1, 1);
        UserChattingRoomRelation userChattingRoomRelation1 = new UserChattingRoomRelation(2, 1);
        UserChattingRoomRelation userChattingRoomRelation2 = new UserChattingRoomRelation(3, 1);
        UserChattingRoomRelation userChattingRoomRelation3 = new UserChattingRoomRelation(1, 2);
        UserChattingRoomRelation userChattingRoomRelation4 = new UserChattingRoomRelation(2, 2);
        UserChattingRoomRelation userChattingRoomRelation5 = new UserChattingRoomRelation(2, 3);
        UserChattingRoomRelation userChattingRoomRelation6 = new UserChattingRoomRelation(3, 3);

        relation.addUserChattingRoomRelation(userChattingRoomRelation);
        relation.addUserChattingRoomRelation(userChattingRoomRelation1);
        relation.addUserChattingRoomRelation(userChattingRoomRelation2);
        relation.addUserChattingRoomRelation(userChattingRoomRelation3);
        relation.addUserChattingRoomRelation(userChattingRoomRelation4);
        relation.addUserChattingRoomRelation(userChattingRoomRelation5);
        relation.addUserChattingRoomRelation(userChattingRoomRelation6);

        relation.updateloginUserId(1);

        assertEquals(1, relation.loginUserId());

        List<UserChattingRoomRelation> loginRelations = relation.loginUserChattingRoomRelations();

        assertEquals(List.of(userChattingRoomRelation, userChattingRoomRelation3), loginRelations);
    }

    @Test
    void requestFriend() throws IOException {
        MakaoTalk makaoTalk = new MakaoTalk();

        boolean deleted = false;
        User user1 = new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));
        User user2 = new User(2, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));
        User user3 = new User(3, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));

        makaoTalk.login(user1.id());
        makaoTalk.relation().requestFriend(user2.id());

        List<UsersRelation> usersRelations = makaoTalk.relation().usersRelations();

        UsersRelation usersRelation1To2 = usersRelations.get(0);

        assertEquals(1, usersRelation1To2.myId());
        assertEquals(2, usersRelation1To2.friendId());

        makaoTalk.relation().requestFriend(user3.id());

        UsersRelation usersRelation1To3 = usersRelations.get(1);

        assertEquals(1, usersRelation1To3.myId());
        assertEquals(3, usersRelation1To3.friendId());

        makaoTalk.login(user2.id());

        makaoTalk.relation().requestFriend(user3.id());

        UsersRelation usersRelation2To3 = usersRelations.get(2);

        assertEquals(2, usersRelation2To3.myId());
        assertEquals(3, usersRelation2To3.friendId());
    }

    @Test
    void loginUserFriends() {
        MakaoTalk makaoTalk = new MakaoTalk();

        makaoTalk.login(1);

        makaoTalk.relation().addUsersRelation(new UsersRelation(1, 4));
        makaoTalk.relation().addUsersRelation(new UsersRelation(2, 3));
        makaoTalk.relation().addUsersRelation(new UsersRelation(1, 2));
        makaoTalk.relation().addUsersRelation(new UsersRelation(3, 1));
        makaoTalk.relation().addUsersRelation(new UsersRelation(1, 6));
        makaoTalk.relation().addUsersRelation(new UsersRelation(2, 5));

        assertEquals(List.of(
                        new UsersRelation(1, 4),
                        new UsersRelation(1, 2),
                        new UsersRelation(1, 6)
                ),
                makaoTalk.relation().loginUserFriends()
        );
    }

    @Test
    void newChattingRoomMessageRelation() {
        Relation relation = new Relation();

        relation.newChattingRoomMessageRelation(new ChattingRoom(1, "", List.of()),
                new Message(2, "", "", 1));

        relation.newChattingRoomMessageRelation(new ChattingRoom(2, "", List.of()),
                new Message(3, "", "", 1));

        List<ChattingRoomMessageRelation> relations = relation.chattingRoomMessageRelations();
        assertEquals(new ChattingRoomMessageRelation(1, 2), relations.get(0));
        assertEquals(new ChattingRoomMessageRelation(2, 3), relations.get(1));
    }

    @Test
    void currentChattingRoomMessageRelations() {
        Relation relation = new Relation();

        relation.newChattingRoomMessageRelation(new ChattingRoom(1, "", List.of()),
                new Message(2, "", "", 1));
        relation.newChattingRoomMessageRelation(new ChattingRoom(2, "", List.of()),
                new Message(3, "", "", 1));
        relation.newChattingRoomMessageRelation(new ChattingRoom(3, "", List.of()),
                new Message(4, "", "", 1));
        relation.newChattingRoomMessageRelation(new ChattingRoom(4, "", List.of()),
                new Message(5, "", "", 1));
        relation.newChattingRoomMessageRelation(new ChattingRoom(1, "", List.of()),
                new Message(6, "", "", 1));
        relation.newChattingRoomMessageRelation(new ChattingRoom(2, "", List.of()),
                new Message(7, "", "", 1));

        relation.updateCurrentChattingRoom(1);

        assertEquals(List.of(
                        new ChattingRoomMessageRelation(1, 2),
                        new ChattingRoomMessageRelation(1, 6)
                ),
                relation.currentChattingRoomMessageRelations());

        relation.updateCurrentChattingRoom(2);

        assertEquals(List.of(
                        new ChattingRoomMessageRelation(2, 3),
                        new ChattingRoomMessageRelation(2, 7)
                ),
                relation.currentChattingRoomMessageRelations());
    }

    @Test
    void newUserChattingRoomRelations() throws IOException {
        Relation relation = new Relation();

        boolean deleted = false;
        User user1 = new User(1, "ojw", "ojw123", "오진욱", "01085568965", new Profile(1, deleted));
        User user2 = new User(2, "ojs", "ojs123", "오진성", "01052398955", new Profile(1, deleted));
        User user3 = new User(3, "bjh", "bjh123", "배준형", "01024593050", new Profile(1, deleted));

        List<User> invitedUsers = List.of(user1, user2, user3);

        ChattingRoom newChatting = new ChattingRoom(1, "", invitedUsers);

        relation.newUserChattingRoomRelations(newChatting);

        assertEquals(List.of(
                new UserChattingRoomRelation(1,1),
                new UserChattingRoomRelation(2,1),
                new UserChattingRoomRelation(3,1)
        ),
                relation.userChattingRoomRelations());
    }
}