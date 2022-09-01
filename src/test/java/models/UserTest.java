package models;

import models.ChattingRoom.ChattingRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void creation() throws IOException {
        long id = 1;
        boolean deleted = false;
        Profile profile = new Profile(id, deleted);
        User user = new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));

        assertEquals("ojw0828", user.userName());
        assertEquals("7895123", user.passWord());
        assertEquals("오진욱", user.name());
        assertEquals("01085568965", user.phoneNumber());
//        assertEquals("프로필 메시지: , 프로필 사진: null, 프로필 음악: null",
//                user.profile().toString()); // TODO : Profile부터 작성한 뒤에 나중에 돌아와서 비교
    }

    @Test
    void sendMessage() throws IOException {
        List<Message> messages = new ArrayList<>();

        boolean deleted = false;
        User currentUser = new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));
        User otherUser = new User(2, "ojs0828", "9645123", "오진성", "01076308965", new Profile(1, deleted));

        List<User> invitedUsers = new ArrayList<>();

        ChattingRoom chattingRoom = new ChattingRoom(
                1,
                "title", List.of());

        User user = new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));

        String content = "Hi";

    }

    @Test
    void toCsvRow() throws IOException {
        boolean deleted = false;
        User user = new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));

        String line = user.toCsvRow();

        assertEquals("1,ojw0828,7895123,오진욱,01085568965,false", line);
    }

}