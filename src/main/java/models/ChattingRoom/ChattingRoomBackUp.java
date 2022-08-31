package models.ChattingRoom;

import models.Message;
import models.User;

import java.util.ArrayList;
import java.util.List;

// TODO : 단톡방에 초대된 시용자들 + 현재 로그인한 유저 + 메시지 내역 + 단톡방 제목 + 미리보기 메시지 -> 엔티티

public class ChattingRoomBackUp {
    private final long id;
    private final List<User> invitedUsers;
    private final User currentUser;
    private final List<Message> messages;
    private String type;
    private String title;
    private String previewMessage = "";

    public ChattingRoomBackUp(
            long id,
            List<User> invitedUsers,
            User currentUser,
            List<Message> messages

    ) {
        this.id = id;

        this.invitedUsers = invitedUsers;
        this.currentUser = currentUser;
        this.messages = messages;
        this.type = updateType();
        this.title = updateTitle();
        this.previewMessage = updatePreviewMessage(messages);
    }

    private String updateType() {
        if (invitedUsers.size() > 2) {
            return "multi";
        }

        if (invitedUsers.size() == 2) {
            return "single";
        }

        if (invitedUsers.size() == 1) {
            return "alone";
        }

        return ".";
    }

    private String updatePreviewMessage(List<Message> messages) {
        if (messages.size() == 0) {
            return "";
        }

        return messages.get(messages.size() - 1).content();
    }

    public User currentUser() {
        return currentUser;
    }

    public String title() {
        return title;
    }

    public List<User> invitedUsers() {
        return invitedUsers;
    }

    public String previewMessage() {
        return previewMessage;
    }

    public String updateTitle() {
        List<String> names = new ArrayList<>();

        invitedUsers.forEach(invitedUser -> names.add(invitedUser.name()));

        List<String> titleNames = names.stream()
                .filter(name -> !name.equals(currentUser.name()))
                .toList();

        if (type.equals("single")) {
            return titleNames.get(0);
        }

        return String.join(", ", names);
    }

    public List<Message> messages() {
        return messages;
    }

    public void invite(User user) {
        invitedUsers.add(user);

        title = updateTitle();

        type = updateType();
    }

    public String messageOwnerName(Message message) {
        String messageOwnerName = "";

        for (User invitedUser : invitedUsers) {
            if (invitedUser.userName().equals(message.userId())) {
                messageOwnerName = invitedUser.name();
            }
        }

        return messageOwnerName;
    }

    public void receiveMessage(Message message) {
        addMessage(message);

        this.previewMessage = updatePreviewMessage(messages);
    }

    private void addMessage(Message message) {
        messages.add(message);
    }

    public String type() {
        return type;
    }
}
