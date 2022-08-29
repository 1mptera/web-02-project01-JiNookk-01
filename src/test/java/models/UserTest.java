package models;

import models.ChattingRoom.ChattingRoom;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void creation() {
        Profile profile = new Profile();
        User user = new User(1, "ojw0828", "7895123", "오진욱");

        assertEquals("ojw0828", user.userName());
        assertEquals("7895123", user.passWord());
        assertEquals("오진욱", user.name());
//        assertEquals("프로필 메시지: , 프로필 사진: null, 프로필 음악: null",
//                user.profile().toString()); // TODO : Profile부터 작성한 뒤에 나중에 돌아와서 비교
    }

    @Test
    void sendMessage() {
        List<Message> messages = new ArrayList<>();


        User currentUser = new User(1, "ojw0828", "7895123", "오진욱");
        User otherUser = new User(2, "ojs0828", "9645123", "오진성");

        List<User> invitedUsers = new ArrayList<>();

        ChattingRoom chattingRoom = new ChattingRoom(
                invitedUsers,
                currentUser,
                messages
        );

        User user = new User(1, "ojw0828", "7895123", "오진욱");

        String content = "Hi";

        user.sendMessageToChattingRoom(content, chattingRoom);

        assertEquals("Hi",chattingRoom.messages().get(0).content());

        user.sendMessageToChattingRoom("Hello", chattingRoom);

        assertEquals("Hello",chattingRoom.messages().get(1).content());

        user.sendMessageToChattingRoom("Fighting", chattingRoom);

        assertEquals("Fighting",chattingRoom.messages().get(2).content());
    }

    @Test
    void toCsvRow() {
        User user = new User(1, "ojw0828", "7895123", "오진욱");

        String line = user.toCsvRow();

        assertEquals("1,ojw0828,7895123,오진욱", line);
    }

}