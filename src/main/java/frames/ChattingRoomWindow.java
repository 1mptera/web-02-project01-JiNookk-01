package frames;

import models.ChattingRoom.ChattingRoom;
import models.Message;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class ChattingRoomWindow extends JFrame {
    private final ChattingRoom chattingRoom;
    private JTextField inputMessageTextField;
    private JPanel messagesPanel;

    public ChattingRoomWindow(ChattingRoom chattingRoom) {
        this.chattingRoom = chattingRoom;

        this.setLayout(new BorderLayout());
        this.setTitle(chattingRoom.title());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);

        this.add(chattingRoomStatusPanel(), BorderLayout.NORTH);
        this.add(messagesPanel());
        this.add(inputMessagePanel(), BorderLayout.SOUTH);

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
            String newMessage = inputMessageTextField.getText();

            chattingRoom.currentUser().
                    sendMessageToChattingRoom(newMessage, chattingRoom);

            messagesPanel.setLayout(new GridLayout(chattingRoom.messages().size(), 1));

            messagesPanel.removeAll();

            for (Message message : chattingRoom.messages()) {
                messagesPanel.add(messagePanel(message));
            }

            messagesPanel.setVisible(false);
            messagesPanel.setVisible(true);
            this.setVisible(true);
        });
        return button;
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
        String userName = chattingRoom.messageOwnerName(message);
        return new JLabel(userName);
    }

    private JLabel messageContentLabel(Message message) {
        return new JLabel(message.content());
    }

    private JLabel messageTimeLabel(Message message) {
        return new JLabel(message.time());
    }
}
