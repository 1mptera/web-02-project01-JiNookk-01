package panels;

import frames.AlertFrame;
import frames.DeleteIDFrame;
import frames.FriendAddFrame;
import frames.SelectChattingFrame;
import models.MakaoTalk;
import models.User;
import utils.loader.UserLoader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

public class ButtonPanel extends JPanel {
    private MakaoTalk makaoTalk;
    private JPanel imagePanel;
    private JPanel contentPanel;
    private JTextField nickNameField;
    private JTextField userNameField;
    private JTextField passwordField;
    private JTextField phoneNumberField;
    private JPanel nickNameAndUserPanel;

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
            setDefaultContentPanelSetting();
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
            JFrame friendAddWindow = new FriendAddFrame(makaoTalk, contentPanel);

            friendAddWindow.setVisible(true);
        });
        return button;
    }

    // TODO : 채팅 창
    private JButton chattingButton() {
        JButton button = new JButton("채팅목록");
        button.addActionListener(event -> {
            setDefaultContentPanelSetting();
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


    private JButton settingButton() {
        JButton button = new JButton("사용자 설정");
        button.addActionListener(event -> {
            setDefaultContentPanelSetting();
            contentPanel.add(userInformationPanel(), BorderLayout.NORTH);
            contentPanel.add(modifyUserInformationPanel());
            showImagePanel();
        });
        return button;
    }

    private void setDefaultContentPanelSetting() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private JPanel userInformationPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 160));
        panel.add(new JLabel("더보기"));
        panel.add(displayInformationPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel displayInformationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 125));
        panel.add(nickNameAndUserNamePanel(), BorderLayout.WEST);
        panel.add(editProfilePanel(), BorderLayout.EAST);
        return panel;
    }

    private JPanel editProfilePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(130, 0));
        panel.add(new JButton("프로필 수정"), BorderLayout.SOUTH);
        panel.add(new JLabel("프로필 사진"));
        return panel;
    }

    private JPanel nickNameAndUserNamePanel() {
        nickNameAndUserPanel = new JPanel();
        nickNameAndUserPanel.setLayout(new BorderLayout());
        nickNameAndUserPanel.setPreferredSize(new Dimension(150, 0));
        updateNickNameAndUserPanel();
        return nickNameAndUserPanel;
    }

    private void updateNickNameAndUserPanel() {
        nickNameAndUserPanel.removeAll();
        User loginUser = makaoTalk.user(makaoTalk.loginUserId());
        nickNameAndUserPanel.add(namePanel(loginUser));
        nickNameAndUserPanel.add(phoneNumberLabel(loginUser), BorderLayout.SOUTH);
    }

    private JPanel namePanel(User loginUser) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(userNickNameLabel(loginUser));
        panel.add(userNameLabel(loginUser), BorderLayout.SOUTH);
        return panel;
    }

    private JLabel userNickNameLabel(User loginUser) {
        JLabel label = new JLabel(loginUser.name());
        return label;
    }

    private JLabel userNameLabel(User loginUser) {
        JLabel label = new JLabel(loginUser.userName());
        return label;
    }

    private JLabel phoneNumberLabel(User loginUser) {
        JLabel label = new JLabel(loginUser.phoneNumber());
        label.setPreferredSize(new Dimension(0, 45));
        return label;
    }

    private JPanel modifyUserInformationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(inputNewInformationPanel());
        panel.add(modifyButtonsPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel inputNewInformationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 0, 70, 0));
        panel.add(new JLabel("변경할 이름을 입력해주세요!"));
        nickNameField = new JTextField(10);
        nickNameField.setText("이름");
        panel.add(nickNameField);
        panel.add(new JLabel("변경할 ID를 입력해주세요!"));
        userNameField = new JTextField(10);
        userNameField.setText("ID");
        panel.add(userNameField);
        panel.add(new JLabel("변경할 비밀번호를 입력해주세요!"));
        passwordField = new JTextField(10);
        passwordField.setText("PassWord");
        panel.add(passwordField);
        panel.add(new JLabel("변경할 폰번호를 입력해주세요!"));
        phoneNumberField = new JTextField(10);
        phoneNumberField.setText("PhoneNumber");
        panel.add(phoneNumberField);
        return panel;
    }

    private JPanel modifyButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 120));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        panel.add(modifyButton());
        panel.add(deleteAccountButton());
        return panel;
    }

    private JButton modifyButton() {
        JButton button = new JButton("회원정보 수정");
        button.setPreferredSize(new Dimension(100, 40));
        button.addActionListener(event -> {
            String nickName = nickNameField.getText();
            String userName = userNameField.getText();
            String password = passwordField.getText();
            String phoneNumber = phoneNumberField.getText();

            boolean noNickName = nickName.equals("");
            boolean noUserName = userName.equals("");
            boolean noPassWord = password.equals("");
            boolean noPhoneNumber = phoneNumber.equals("");
            boolean userNameOverlapped = false;

            for (User user : makaoTalk.users()) {
                if (userName.equals(user.userName())) {
                    userNameOverlapped = true;
                }
            }

            if (noNickName || noUserName || noPassWord || noPhoneNumber) {
                new AlertFrame("빈 칸을 채워주세요!");
            }

            if (userNameOverlapped) {
                new AlertFrame("다른 유저의 ID와 중복됩니다.");
            }

            if (!noNickName && !noUserName && !noPassWord && !noPhoneNumber && !userNameOverlapped) {
                User loginUser = makaoTalk.user(makaoTalk.loginUserId());

                loginUser.updateNickName(nickName);
                loginUser.updateUserName(userName);
                loginUser.updatePassWord(password);
                loginUser.updatePhoneNumber(phoneNumber);

                try {
                    new UserLoader().saveUsers(makaoTalk.users());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                updateNickNameAndUserPanel();
                showImagePanel();

                new AlertFrame("회원정보가 수정되었습니다.");
            }
        });
        return button;
    }

    private JButton deleteAccountButton() {
        JButton button = new JButton("회원 탈퇴");
        button.setPreferredSize(new Dimension(100, 40));
        button.addActionListener(event -> {
            JFrame deleteIDFrame = new DeleteIDFrame(makaoTalk,imagePanel,contentPanel);

            deleteIDFrame.setVisible(true);
        });
        return button;
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
