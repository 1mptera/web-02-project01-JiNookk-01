package panels;

import frames.FriendAddFrame;
import models.ChattingRoom.ChattingRoom;
import models.ChattingRoom.SingleChatting;
import models.MakaoTalk;
import models.Relation.UsersRelation;
import models.User;
import utils.MouseEventListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class ButtonPanel extends JPanel {
    private final MouseEventListener mouseListener = new MouseEventListener();
    private MakaoTalk makaoTalk;
    private JPanel imagePanel;
    private JPanel contentPanel;

    public ButtonPanel(MakaoTalk makaoTalk, JPanel imagePanel, JPanel contentPanel) {
        this.makaoTalk = makaoTalk;
        this.imagePanel = imagePanel;
        this.contentPanel = contentPanel;

        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.setPreferredSize(new Dimension(100, 0));
        this.setBackground(Color.gray);
        this.add(friendsButton());
        this.add(chattingButton());
        this.add(settingButton());
        this.add(logoutButton());
    }

    private JButton friendsButton() {
        JButton button = new JButton("친구목록");
        button.addActionListener(event -> {
            contentPanel.removeAll();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(friendsToolPanel(), BorderLayout.NORTH);

            JPanel friendsPanel = new FriendsPanel(makaoTalk);
            contentPanel.add(friendsPanel);
            showImagePanel();
        });
        return button;
    }

    private JPanel friendsToolPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.green);
        panel.add(friendFindButton());
        panel.add(addFriendButton());
        return panel;
    }

    private JButton friendFindButton() {
        JButton button = new JButton("찾기");
        return button;
    }

    private JButton addFriendButton() {
        JButton button = new JButton("친구 추가");
        button.addActionListener(event -> {
            JFrame friendAddWindow = new FriendAddFrame(makaoTalk);

            friendAddWindow.setVisible(true);
        });
        return button;
    }



    // TODO : 채팅 창
    private JButton chattingButton() {
        JButton button = new JButton("채팅목록");
        button.addActionListener(event -> {
            contentPanel.removeAll();

            for (ChattingRoom chattingRoom : makaoTalk.chattingRooms()) {
                if (chattingRoom.type().equals("single")) {
                    ChattingRoom singleChatting =
                            new SingleChatting(chattingRoom.invitedUsers(),
                                    chattingRoom.currentUser(),
                                    chattingRoom.messages()
                            );

                    contentPanel.add(chattingRoomPanel(singleChatting));
                }

                if (chattingRoom.type().equals("multi")) {
                    contentPanel.add(chattingRoomPanel(chattingRoom));
                }
            }

            showImagePanel();
        });
        return button;
    }

    private JPanel chattingRoomPanel(ChattingRoom chattingRoom) {
        JPanel panel = new JPanel();
        panel.add(chattingRoomProfilePicturePanel(chattingRoom));
        panel.add(chattingRoomDescriptionPanel(chattingRoom));
        panel.addMouseListener(mouseListener.openChattingRoomWindow(chattingRoom));
        return panel;
    }

    private JPanel chattingRoomDescriptionPanel(ChattingRoom chattingRoom) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(new JLabel(chattingRoom.title()));
        panel.add(new JLabel(chattingRoom.previewMessage()));
        return panel;
    }

    private JPanel chattingRoomProfilePicturePanel(ChattingRoom chattingRoom) {
        return new JPanel();
    }

    private JButton logoutButton() {
        JButton button = new JButton("로그아웃");
        button.addActionListener(event -> {
            imagePanel.removeAll();
            contentPanel.removeAll();

            JPanel loginPanel = new LoginPanel(makaoTalk, imagePanel, contentPanel);
            contentPanel.add(loginPanel);
            contentPanel.setOpaque(false);

            imagePanel.add(contentPanel);
            showImagePanel();
        });
        return button;
    }

    private JButton settingButton() {
        JButton button = new JButton("사용자 설정");
        return button;
    }

    private void showImagePanel() {
        imagePanel.setVisible(false);
        imagePanel.setVisible(true);
    }
}
