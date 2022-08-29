package frames;

import models.ChattingRoom.ChattingRoom;
import models.ChattingRoom.SingleChatting;
import models.MakaoTalk;
import models.User;
import panels.ImagePanel;
import utils.MouseEventListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

public class MainFrame extends JFrame {
    private final MouseEventListener mouseListener = new MouseEventListener();
    private JPanel imagePanel;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    private Image background;
    private MakaoTalk makaoTalk;
    private JPanel loginPanel;
    private JTextField inputIdField;
    private JTextField inputPasswordField;


    public MainFrame(MakaoTalk makaoTalk) {
        this.makaoTalk = makaoTalk;

        this.setTitle("Makao Talk");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(490, 800);

        setLoginBackground();

        initImagePanel();

        initContentPanel();

        initLoginPanel();
//
        initLoginMenus();

//        initButtonPanel();

//        start();
    }

    private void initImagePanel() {
        imagePanel = new ImagePanel("./src/main/resources/images/makaoTalk.png");
        imagePanel.setOpaque(false);
        imagePanel.setLayout(new BorderLayout());

        this.add(imagePanel);
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
    }

    private void initLoginPanel() {
        loginPanel = new JPanel();
//        loginPanel.setOpaque(false);
    }

    private void initLoginMenus() {
        contentPanel.setLayout(new BorderLayout());

        contentPanel.setBorder(BorderFactory.createEmptyBorder(300,120,300,120));

        loginPanel.setLayout(new GridLayout(4, 1,0,0));
        loginPanel.add(inputIdField());
        loginPanel.add(inputPasswordField());
        loginPanel.add(loginButton());
        loginPanel.add(registerButton());

        contentPanel.add(loginPanel);

        imagePanel.add(contentPanel);
    }

    private JTextField inputIdField() {
        inputIdField = new JTextField(10);
        return inputIdField;
    }

    private JTextField inputPasswordField() {
        inputPasswordField = new JTextField(10);
        return inputPasswordField;
    }

    private JButton loginButton() {
        return new JButton("로그인");
    }

    private JButton registerButton() {
        JButton button = new JButton("회원 가입");
        button.addActionListener(event -> {
            JFrame registerWindow = new RegisterFrame(makaoTalk);

            registerWindow.setVisible(true);
        });
        return button;
    }


    private void setLoginBackground() {
        background = new ImageIcon().getImage();

    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(100, 0));
    }

    private void start() {
//        imagePanel.add(buttonPanel, BorderLayout.WEST);
//
//        imagePanel.add(contentPanel);

//        buttonPanel.add(friendsButton());
//        buttonPanel.add(chattingButton());
    }


    private JButton friendsButton() {
        JButton button = new JButton("친구목록");
        button.addActionListener(event -> {
            contentPanel.removeAll();

            for (User friend : makaoTalk.friends()) {
                contentPanel.add(friendsPanel(friend));
            }

            showImagePanel();
        });
        return button;
    }

    private JPanel friendsPanel(User friend) {
        JPanel panel = new JPanel();
        panel.addMouseListener(mouseListener.openFriendProfileWindow(friend));
        panel.add(friendProfilePicturePanel(friend));
        panel.add(friendDescriptionPanel(friend));
        return panel;
    }

    private JPanel friendProfilePicturePanel(User friend) {
        return new JPanel();
    }

    private JPanel friendDescriptionPanel(User friend) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(new JLabel(friend.name()));
        panel.add(new JLabel(friend.profile().profileMessage()));
        return panel;
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

    private void showImagePanel() {
        imagePanel.setVisible(false);
        imagePanel.setVisible(true);
        this.setVisible(true);
    }
}
