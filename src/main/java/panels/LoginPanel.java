package panels;

import frames.AlertFrame;
import frames.RegisterFrame;
import models.MakaoTalk;
import models.User;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;

public class LoginPanel extends JPanel {
    private MakaoTalk makaoTalk;

    private JTextField inputUserNameField;
    private JTextField inputPasswordField;
    private final JPanel imagePanel;
    private final JPanel contentPanel;

    public LoginPanel(MakaoTalk makaoTalk, JPanel imagePanel, JPanel contentPanel) {
        this.makaoTalk = makaoTalk;
        this.imagePanel = imagePanel;
        this.contentPanel = contentPanel;

        contentPanel.setBorder(BorderFactory.createEmptyBorder(300, 120, 300, 120));

        this.setLayout(new GridLayout(4, 1));
        this.add(inputIdField());
        this.add(inputPasswordField());
        this.add(loginButton());
        this.add(registerButton());
    }

    private JTextField inputIdField() {
        inputUserNameField = new JTextField(10);
        inputUserNameField.setText("ID를 입력해주세요!");
        return inputUserNameField;
    }

    private JTextField inputPasswordField() {
        inputPasswordField = new JTextField(10);
        inputPasswordField.setText("암호를 입력해주세요!");
        return inputPasswordField;
    }

    private JButton loginButton() {
        JButton button = new JButton("로그인");
        button.addActionListener(event -> {
            String userName = inputUserNameField.getText();
            String password = inputPasswordField.getText();

            try {
                User loginUser = makaoTalk.undeletedUsers().stream()
                        .filter(user -> user.userName().equals(userName))
                        .toList().get(0);

                boolean passwordCorrect = password.equals(loginUser.passWord());

                if (!passwordCorrect) {
                    showAlert("비밀번호를 확인해주세요!");
                }

                if (passwordCorrect) {
                    makaoTalk.login(loginUser.id());
                    mainPage();
                }
            } catch (ArrayIndexOutOfBoundsException | IOException exception) {
                showAlert("아이디를 확인해주세요!");
            }
        });
        return button;
    }

    private void showAlert(String alertMessage) {
        JFrame alertWindow = new AlertFrame(alertMessage);
        alertWindow.setVisible(true);
    }

    private JButton registerButton() {
        JButton button = new JButton("회원 가입");
        button.addActionListener(event -> {
            JFrame registerWindow = new RegisterFrame(makaoTalk);

            registerWindow.setVisible(true);
        });
        return button;
    }

    private void mainPage() throws IOException {
        contentPanel.removeAll();
        contentPanel.setOpaque(true);
        contentPanel.setBackground(new Color(38,38,38));

        JPanel buttonPanel = new ButtonPanel(makaoTalk, imagePanel, contentPanel);

        imagePanel.add(buttonPanel, BorderLayout.WEST);
        showImagePanel();
    }

    private void showImagePanel() {
        imagePanel.setVisible(false);
        imagePanel.setVisible(true);
        this.setVisible(true);
    }
}
