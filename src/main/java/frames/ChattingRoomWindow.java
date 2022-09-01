package frames;

import models.ChattingRoom.ChattingRoom;
import models.MakaoTalk;
import models.Message;
import models.Profile;
import models.User;
import utils.loader.ChattingRoomLoader;
import utils.loader.MessageLoader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ChattingRoomWindow extends JFrame {
    private final MakaoTalk makaoTalk;
    private final ChattingRoom chattingRoom;

    private JTextField inputMessageTextField;
    private JPanel scrollMessagesPanel;
    private JPanel chattingRoomContainer;

    public ChattingRoomWindow(MakaoTalk makaoTalk, ChattingRoom chattingRoom) throws IOException {
        this.makaoTalk = makaoTalk;
        this.chattingRoom = chattingRoom;

        this.setTitle(chattingRoom.title());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 500);
        this.add(chattingRoomContainer());
        this.setResizable(false);

        showScrollMessagesPanel();
        showScrollMessages();
    }

    private JPanel chattingRoomContainer() throws IOException {
        chattingRoomContainer = new JPanel();
        updateChattingRoom();
        return chattingRoomContainer;
    }

    private void updateChattingRoom() throws IOException {
        chattingRoomContainer.removeAll();
        chattingRoomContainer.setBackground(new Color(25, 25, 25));
        chattingRoomContainer.setLayout(new BorderLayout());
        chattingRoomContainer.add(chattingRoomTitlePanel(), BorderLayout.NORTH);
        chattingRoomContainer.add(scrollMessagePanel());
        chattingRoomContainer.add(inputMessagePanel(), BorderLayout.SOUTH);
    }

    private JScrollPane scrollMessagePanel() {
        scrollMessagesPanel = new JPanel();
        scrollMessagesPanel.setBackground(new Color(25, 25, 25));
        return new JScrollPane(scrollMessagesPanel);
    }

    private JPanel chattingRoomTitlePanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.add(titleAndProfilePicturePanel(), BorderLayout.WEST);
        panel.add(editChattingRoomPanel(), BorderLayout.EAST);
        return panel;
    }

    private JPanel editChattingRoomPanel() {
        // TODO : 채팅방 이름 변경, 채팅방 나가기 버튼 구현
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(renameChattingRoomButton());
        return panel;
    }

    private JButton renameChattingRoomButton() {
        JButton button = new JButton("채팅방 이름 변경");
        button.addActionListener(event -> {
            try {
                JFrame renameChattingRoomWindow = new RenameChattingRoomFrame(chattingRoom, button);
                renameChattingRoomWindow.setVisible(true);

                renameChattingRoomWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            updateChattingRoom();

                            new ChattingRoomLoader().saveChattingRooms(makaoTalk.chattingRooms());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        scrollMessagesPanel.setVisible(false);
                        scrollMessagesPanel.setVisible(true);
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    private JPanel titleAndProfilePicturePanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(profilePicturePanel());
        panel.add(titleAndPreviewMessagePanel());
        return panel;
    }

    private JPanel profilePicturePanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        JLabel profilePictureLabel =
                new JLabel(chattingRoom.invitedUsers().get(0).
                        profile().picture().profilePicture(Profile.PROFILEWIDTH, Profile.PROFILEHEIGHT));
        panel.add(profilePictureLabel);
        return panel;
    }

    private JPanel titleAndPreviewMessagePanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(titleLabel());
        panel.add(headCountLabel());
        return panel;
    }

    private JLabel titleLabel() {
        JLabel label = new JLabel(chattingRoom.title());
        label.setFont(new Font("Serif", Font.PLAIN, 14));
        label.setForeground(new Color(0xEFEBEB));
        return label;
    }

    private JLabel headCountLabel() {
        JLabel label = new JLabel(headCount() + "명");
        label.setForeground(new Color(0x9B9B9B));
        label.setFont(new Font("Serif", Font.PLAIN, 10));
        return label;
    }

    private String headCount() {
        return String.valueOf(chattingRoom.invitedUsers().size());
    }

    private JPanel inputMessagePanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(inputMessageTextField());
        panel.add(transferMessageButton());
        return panel;
    }

    private JTextField inputMessageTextField() {
        inputMessageTextField = new JTextField(10);
        inputMessageTextField.setFocusable(true);
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
                    User loginUser = makaoTalk.user(makaoTalk.loginUserId());

                    Message newMessage = loginUser.sendMessageToSystem(content);

                    makaoTalk.newMessage(newMessage, chattingRoom);

                    new ChattingRoomLoader().saveChattingRooms(makaoTalk.chattingRooms());

                    messageLoader.saveMessage(makaoTalk.messages());

                    messageLoader.saveChattingRoomMessageRelations(
                            makaoTalk.relation().chattingRoomMessageRelations());

                    showScrollMessages();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                this.setVisible(true);
            }
        });
        return button;
    }

    private void showScrollMessages() throws IOException {
        scrollMessagesPanel.removeAll();
        scrollMessagesPanel.add(messagesContainer());
    }

    private JPanel messagesContainer() throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.removeAll();
        panel.setLayout(new GridLayout(makaoTalk.currentChattingRoomMessages().size(), 1));
        for (Message message : makaoTalk.currentChattingRoomMessages()) {
            panel.add(messagePanel(message));
        }

        showScrollMessagesPanel();
        return panel;
    }

    private void showScrollMessagesPanel() {
        scrollMessagesPanel.setVisible(false);
        scrollMessagesPanel.setVisible(true);
    }

    private JPanel messagePanel(Message message) throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(350, 70));
        panel.add(profilePictureLabel(message), BorderLayout.WEST);
        panel.add(messageOwnerAndContentPanel(message));
        panel.add(messageTimeLabel(message), BorderLayout.EAST);
        return panel;
    }

    private JLabel profilePictureLabel(Message message) throws IOException {
        User messageOwner = makaoTalk.user(message.userId());

        ImageIcon userProfilePicture = messageOwner.profile().picture().
                profilePicture(Profile.PROFILEWIDTH, Profile.PROFILEHEIGHT);

        return new JLabel(userProfilePicture);
    }

    private JPanel messageOwnerAndContentPanel(Message message) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(userNameLabel(message));
        panel.add(messageContentLabel(message));
        return panel;
    }

    private JLabel userNameLabel(Message message) {
        String userName = makaoTalk.messageOwnerName(message);

        JLabel label = new JLabel("   " + userName);
        label.setFont(new Font("Serif", Font.PLAIN, 14));
        label.setForeground(new Color(0xEFEBEB));
        return label;
    }

    private JLabel messageContentLabel(Message message) {
        JLabel label = new JLabel("   " + message.content());
        label.setForeground(new Color(0x9B9B9B));
        label.setFont(new Font("Serif", Font.PLAIN, 10));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private JLabel messageTimeLabel(Message message) {
        String time = message.convertTime();

        JLabel label = new JLabel(time);
        label.setForeground(new Color(0x9B9B9B));
        label.setFont(new Font("Serif", Font.PLAIN, 10));
        return label;
    }
}
