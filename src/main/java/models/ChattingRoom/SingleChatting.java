package models.ChattingRoom;

import models.Message;
import models.User;

import java.util.List;

public class SingleChatting extends ChattingRoom {
    private User otherUser;

    public SingleChatting(List<User> invitedUsers, User currentUser, List<Message> messages) {
        super(invitedUsers, currentUser, messages);
        otherUser = otherUser();
    }



    public User otherUser() {
        if (invitedUsers().size() != 2) {
            return currentUser();
        }

        otherUser = invitedUsers().stream()
                .filter(user -> !user.userName().equals(currentUser().userName()))
                .toList()
                .get(0);

        return otherUser;
    }
}
