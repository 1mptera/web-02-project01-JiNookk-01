package panels;

import frames.AlertFrame;
import frames.DeleteIDFrame;
import frames.EditProfileFrame;
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
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ModifyUserInformationPanel extends JPanel {
    private JTextField nickNameField;
    private JTextField userNameField;
    private JTextField passwordField;
    private JTextField phoneNumberField;
    private JPanel nickNameAndUserPanel;
    private final MakaoTalk makaoTalk;
    private JPanel imagePanel;
    private JPanel contentPanel;

    public ModifyUserInformationPanel(MakaoTalk makaoTalk, JPanel imagePanel, JPanel contentPanel) throws IOException {
        this.makaoTalk = makaoTalk;
        this.imagePanel = imagePanel;
        this.contentPanel = contentPanel;

        this.setBackground(new Color(38, 38, 38));
        updateModifyPanel();
    }

    private void updateModifyPanel() throws IOException {
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(userInformationPanel(), BorderLayout.NORTH);
        this.add(modifyUserInformationPanel());
    }

    private JPanel userInformationPanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 160));
        panel.add(titleLabel());
        panel.add(displayInformationPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JLabel titleLabel() {
        JLabel label = new JLabel("더보기");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        label.setForeground(new Color(0xEFEBEB));
        return label;
    }

    private JPanel displayInformationPanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(46, 46, 46));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 125));
        panel.add(nickNameAndUserNamePanel(), BorderLayout.WEST);
        panel.add(editProfilePanel(), BorderLayout.EAST);
        return panel;
    }

    private JPanel nickNameAndUserNamePanel() {
        nickNameAndUserPanel = new JPanel();
        nickNameAndUserPanel.setOpaque(false);
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
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(60, 60, 60)),
                BorderFactory.createEmptyBorder(0, 0, 20, 0)
        ));
        panel.add(userNickNameLabel(loginUser));
        panel.add(userNameLabel(loginUser), BorderLayout.SOUTH);
        return panel;
    }

    private JLabel userNickNameLabel(User loginUser) {
        JLabel label = new JLabel(loginUser.name());
        label.setFont(new Font("Serif", Font.PLAIN, 14));
        label.setForeground(new Color(0xEFEBEB));
        return label;
    }

    private JLabel userNameLabel(User loginUser) {
        JLabel label = new JLabel(loginUser.userName());
        label.setForeground(new Color(0x9B9B9B));
        label.setFont(new Font("Serif", Font.PLAIN, 12));
        return label;
    }

    private JLabel phoneNumberLabel(User loginUser) {
        JLabel label = new JLabel(loginUser.phoneNumber());
        label.setFont(new Font("Serif", Font.PLAIN, 14));
        label.setForeground(new Color(0xEFEBEB));
        label.setPreferredSize(new Dimension(0, 30));
        return label;
    }

    private JPanel editProfilePanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(130, 0));
        panel.add(profilePictureLabel());
        panel.add(editProfileButton(), BorderLayout.SOUTH);
        return panel;
    }

    private JButton editProfileButton() {
        JButton button = new JButton("프로필 수정");
        button.addActionListener(event -> {
            try {
                JFrame editProfileWindow = new EditProfileFrame(makaoTalk);

                editProfileWindow.setVisible(true);

                editProfileWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            updateModifyPanel();
                            showImagePanel();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    private JLabel profilePictureLabel() throws IOException {
        User loginUser = makaoTalk.user(makaoTalk.loginUserId());
        return new JLabel(loginUser.profile().picture().profilePicture(65, 60));
    }

    private JPanel modifyUserInformationPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.add(inputNewInformationPanel());
        panel.add(modifyButtonsPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel inputNewInformationPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(8, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 0, 70, 0));

        nickNameField = new JTextField(10);
        nickNameField.setText("이름");
        userNameField = new JTextField(10);
        userNameField.setText("ID");
        passwordField = new JTextField(10);
        passwordField.setText("PassWord");
        phoneNumberField = new JTextField(10);
        phoneNumberField.setText("PhoneNumber");

        panel.add(editLabel("변경할 이름을 입력해주세요!"));
        panel.add(nickNameField);

        panel.add(editLabel("변경할 ID를 입력해주세요!"));
        panel.add(userNameField);

        panel.add(editLabel("변경할 비밀번호를 입력해주세요!"));
        panel.add(passwordField);

        panel.add(editLabel("변경할 폰번호를 입력해주세요!"));
        panel.add(phoneNumberField);
        return panel;
    }

    private JLabel editLabel(String introduceText) {
        JLabel label = new JLabel(introduceText);
        label.setForeground(new Color(0x9B9B9B));
        label.setFont(new Font("Serif", Font.PLAIN, 12));
        return label;
    }

    private JPanel modifyButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
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

            for (User user : makaoTalk.undeletedUsers()) {
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
            JFrame deleteIDFrame = new DeleteIDFrame(makaoTalk, imagePanel, contentPanel);

            deleteIDFrame.setVisible(true);
        });
        return button;
    }

    private void showImagePanel() {
        imagePanel.setVisible(false);
        imagePanel.setVisible(true);
    }
}
