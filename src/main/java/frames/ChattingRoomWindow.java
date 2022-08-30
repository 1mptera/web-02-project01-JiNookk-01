package frames;

import models.ChattingRoom.ChattingRoom;
import models.MakaoTalk;
import models.Message;
import utils.loader.MessageLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

public class ChattingRoomWindow extends JFrame {
    private MakaoTalk makaoTalk;
    private final ChattingRoom chattingRoom;
    private JTextField inputMessageTextField;
    private JPanel messagesPanel;

    public ChattingRoomWindow(MakaoTalk makaoTalk, ChattingRoom chattingRoom) {
        this.makaoTalk = makaoTalk;
        this.chattingRoom = chattingRoom;

        this.setLayout(new BorderLayout());
        this.setTitle(chattingRoom.title());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 800);

        this.add(chattingRoomStatusPanel(), BorderLayout.NORTH);
        this.add(messagesPanel());
        this.add(inputMessagePanel(), BorderLayout.SOUTH);

        showMessagesPanel();
        showMessages();
    }

    private JPanel chattingRoomStatusPanel() {
        JPanel panel = new JPanel();
        panel.add(profilePicturePanel());
        panel.add(titleAndPreviewMessagePanel());
        return panel;
    }

    private JPanel profilePicturePanel() {
        return new JPanel();
    }

    private JPanel titleAndPreviewMessagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(titleLabel());
        panel.add(headCountLabel());
        return panel;
    }

    private JLabel titleLabel() {
        return new JLabel(chattingRoom.title());
    }

    private JLabel headCountLabel() {
        return new JLabel(headCount() + "명");
    }

    private String headCount() {
        return String.valueOf(chattingRoom.invitedUsers().size());
    }

    private JPanel messagesPanel() {
        messagesPanel = new JPanel();
        return messagesPanel;
    }

    private JPanel inputMessagePanel() {
        JPanel panel = new JPanel();
        panel.add(inputMessageTextField());
        panel.add(transferMessageButton());
        return panel;
    }

    private JTextField inputMessageTextField() {
        inputMessageTextField = new JTextField(10);
        return inputMessageTextField;
    }

    private JButton transferMessageButton() {
        JButton button = new JButton("전송");
        button.addActionListener(event -> {
            MessageLoader messageLoader = new MessageLoader();
            String content = inputMessageTextField.getText();
            inputMessageTextField.setText("");

            if (!content.equals("")) {
                try {
                    Message newMessage = makaoTalk.user(makaoTalk.loginUserId())
                            .sendMessageToSystem(content);

                    makaoTalk.newMessage(newMessage, chattingRoom);

                    messageLoader.saveMessage(makaoTalk.messages());

                    messageLoader.saveChattingRoomMessageRelations(
                            makaoTalk.relation().chattingRoomMessageRelations());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                showMessages();
                this.setVisible(true);
            }
        });
        return button;
    }

    private void showMessages() {
        messagesPanel.removeAll();
        messagesPanel.add(messagesContainer());
    }

    private JPanel messagesContainer() {
        JPanel panel = new JPanel();
        panel.removeAll();
        panel.setLayout(new GridLayout(makaoTalk.currentChattingRoomMessages().size(), 1));
        for (Message message : makaoTalk.currentChattingRoomMessages()) {
            panel.add(messagePanel(message));
        }

        showMessagesPanel();
        return panel;
    }

    private void showMessagesPanel() {
        messagesPanel.setVisible(false);
        messagesPanel.setVisible(true);
    }

    private JPanel messagePanel(Message message) {
        JPanel panel = new JPanel();
        panel.add(messageOwnerAndContentPanel(message));
        panel.add(messageTimeLabel(message));
        return panel;
    }

    private JPanel messageOwnerAndContentPanel(Message message) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(userNameLabel(message));
        panel.add(messageContentLabel(message));
        return panel;
    }

    private JLabel userNameLabel(Message message) {
        String userName = makaoTalk.messageOwnerName(message);
        return new JLabel(userName);
    }

    private JLabel messageContentLabel(Message message) {
        return new JLabel(message.content());
    }

    private JLabel messageTimeLabel(Message message) {
        return new JLabel(message.time());
    }
}
