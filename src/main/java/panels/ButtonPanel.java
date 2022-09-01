package panels;

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
import java.io.IOException;

public class ButtonPanel extends JPanel {
    private MakaoTalk makaoTalk;
    private JPanel imagePanel;
    private JPanel contentPanel;

    public ButtonPanel(MakaoTalk makaoTalk, JPanel imagePanel, JPanel contentPanel) throws IOException {
        this.makaoTalk = makaoTalk;
        this.imagePanel = imagePanel;
        this.contentPanel = contentPanel;

        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.setPreferredSize(new Dimension(100, 0));
        this.setBackground(new Color(64,64,64));
        this.add(friendsButton());
        this.add(chattingButton());
        this.add(settingButton());
        this.add(logoutButton());

        showFriendLists();
    }

    private JButton friendsButton() {
        JButton button = new JButton("친구목록");
        button.addActionListener(event -> {
            try {
                showFriendLists();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    private void showFriendLists() throws IOException {
        setDefaultContentPanelSetting();
        contentPanel.add(friendsToolPanel(), BorderLayout.NORTH);

        JPanel friendsPanel = new FriendsPanel(makaoTalk);
        contentPanel.add(friendsPanel);
        showImagePanel();
    }

    private JPanel friendsToolPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
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
            JFrame friendAddWindow = new FriendAddFrame(makaoTalk, contentPanel);

            friendAddWindow.setVisible(true);

            friendAddWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        showFriendLists();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        });
        return button;
    }

    // TODO : 채팅 창
    private JButton chattingButton() {
        JButton button = new JButton("채팅목록");
        button.addActionListener(event -> {
            try {
                showChattingRoomList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    private void showChattingRoomList() throws IOException {
        setDefaultContentPanelSetting();
        contentPanel.add(chattingRoomsToolPanel(), BorderLayout.NORTH);

        JPanel chattingRoomsPanel = new ChattingRoomsPanel(makaoTalk);
        contentPanel.add(chattingRoomsPanel);

        showImagePanel();
    }

    private JPanel chattingRoomsToolPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
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

            selectChattingModeWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        showChattingRoomList();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        });
        return button;
    }

    private JButton settingButton() {
        JButton button = new JButton("사용자 설정");
        button.addActionListener(event -> {
            setDefaultContentPanelSetting();

            try {
                JPanel modifyUserInformationPanel = new ModifyUserInformationPanel(makaoTalk, imagePanel, contentPanel);
                contentPanel.add(modifyUserInformationPanel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            showImagePanel();
        });
        return button;
    }

    private void setDefaultContentPanelSetting() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private void showImagePanel() {
        imagePanel.setVisible(false);
        imagePanel.setVisible(true);
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
}
