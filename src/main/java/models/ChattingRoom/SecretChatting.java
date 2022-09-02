package models.ChattingRoom;

import models.User;

import java.util.List;

public class SecretChatting extends ChattingRoom{
    private String previewMessage = "";
    private final String type;

    public SecretChatting(long id, String title, List<User> invitedUsers) {
        super(id, title, invitedUsers);
        this.type = "비밀";
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public String previewMessage() {
        return previewMessage;
    }

    @Override
    public void updatePreviewMessage(String previewMessage) {
        this.previewMessage = "비밀메시지";
    }
}
