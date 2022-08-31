package panels;

import frames.ChattingRoomAddFrame;
import frames.FriendAddFrame;
import frames.SelectChattingFrame;
import models.MakaoTalk;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ButtonPanel extends JPanel {
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
            JFrame friendAddWindow = new FriendAddFrame(makaoTalk,contentPanel);

            friendAddWindow.setVisible(true);
        });
        return button;
    }


    // TODO : 채팅 창
    private JButton chattingButton() {
        JButton button = new JButton("채팅목록");
        button.addActionListener(event -> {
            contentPanel.removeAll();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(chattingRoomsToolPanel(), BorderLayout.NORTH);

            JPanel chattingRoomsPanel = new ChattingRoomsPanel(makaoTalk);
            contentPanel.add(chattingRoomsPanel);

            showImagePanel();
        });
        return button;
    }

    private JPanel chattingRoomsToolPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.green);
        panel.add(chattingRoomsFindButton());
        panel.add(addChattingRoomButton());
        return panel;
    }


    private JButton chattingRoomsFindButton() {
        JButton button = new JButton("찾기");
        return button;
    }

    private JButton addChattingRoomButton() {
        JButton button = new JButton("채팅 추가");
        button.addActionListener(event -> {
            JFrame selectChattingModeWindow = new SelectChattingFrame(makaoTalk);
            selectChattingModeWindow.setVisible(true);
        });
        return button;
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
