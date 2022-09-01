package frames;

import models.MakaoTalk;
import models.User;
import utils.loader.ProfileLoader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class EditProfileFrame extends JFrame {
    private final User loginUser;
    private MakaoTalk makaoTalk;
    private JTextField nickNameField;
    private JTextField profileMessageField;

    public EditProfileFrame(MakaoTalk makaoTalk) throws IOException {
        loginUser = makaoTalk.user(makaoTalk.loginUserId());
        this.makaoTalk = makaoTalk;

        this.setSize(300, 450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(editProfilePanel());
    }

    private JPanel editProfilePanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(titleAndProfilePicturePanel(), BorderLayout.NORTH);
        panel.add(editNameAndProfileMessagePanel());
        panel.add(editButtonPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel titleAndProfilePicturePanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 160));
        panel.add(titleLabel(), BorderLayout.NORTH);
        panel.add(profilePictureLabel());
        return panel;
    }

    private JLabel titleLabel() {
        JLabel label = new JLabel("기본 프로필 편집");
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private JLabel profilePictureLabel() throws IOException {
        JLabel label = new JLabel(loginUser.profile().picture().profilePicture(90, 90));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    JFrame selectPictureWindow = new SelectPictureFrame(makaoTalk);
                    selectPictureWindow.setVisible(true);
                    selectPictureWindow.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            dispose();
                        }
                    });
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return label;
    }

    private JPanel editNameAndProfileMessagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(userNickNameField());
        panel.add(profileMessageField());
        return panel;
    }

    private JTextField userNickNameField() {
        nickNameField = new JTextField(10);
        nickNameField.setText(loginUser.name());
        return nickNameField;
    }

    private JTextField profileMessageField() {
        profileMessageField = new JTextField(10);
        String profileMessage = loginUser.profile().message();
        if (!profileMessage.equals("")) {
            profileMessageField.setText(profileMessage);
        }
        if (profileMessage.equals("")) {
            profileMessageField.setText("상태메시지를 입력해주세요.");
        }
        return profileMessageField;
    }

    private JPanel editButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 130));
        panel.add(new JPanel());
        panel.add(editButtonContainer(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel editButtonContainer() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250, 40));
        panel.add(editButton());
        panel.add(cancelButton());
        return panel;
    }

    private JButton editButton() {
        JButton button = new JButton("확인");
        button.setPreferredSize(new Dimension(120, 35));
        button.addActionListener(event -> {
            String nickName = nickNameField.getText();
            boolean nickNameBlank = nickName.equals("");

            String profileMessage = profileMessageField.getText();

            if (!nickNameBlank) {
                loginUser.updateNickName(nickName);
                loginUser.profile().updateProfileMessage(profileMessage);

                try {
                    new ProfileLoader().saveProfiles(makaoTalk.profiles());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                dispose();
            }
            if (nickNameBlank) {
                new AlertFrame("이름을 입력해주세요!");
            }
        });
        return button;
    }

    private JButton cancelButton() {
        JButton button = new JButton("취소");
        button.setPreferredSize(new Dimension(120, 35));
        button.addActionListener(event -> {
            dispose();
        });
        return button;
    }
}
