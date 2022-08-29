package frames;

import models.MakaoTalk;
import models.User;
import utils.UserLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.io.IOException;

public class RegisterFrame extends JFrame {

    private JTextField inputIdField;
    private JTextField inputPasswordField;
    private MakaoTalk makaoTalk;
    private JTextField inputNickNameField;

    public RegisterFrame(MakaoTalk makaoTalk) {
        this.makaoTalk = makaoTalk;

        this.setTitle("회원가입");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 400);
        this.add(registerPanel());
        this.setLocationRelativeTo(null);
    }

    private JPanel registerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(inputNickNameField());
        panel.add(inputIdField());
        panel.add(inputPasswordField());
        panel.add(registerButtonsPanel());
        return panel;
    }

    private JTextField inputNickNameField() {
        inputNickNameField = new JTextField(10);
        inputNickNameField.setText("이름을 입력해주세요");
        return inputNickNameField;
    }

    private JTextField inputIdField() {
        inputIdField = new JTextField(10);
        inputIdField.setText("새로 가입할 ID를 입력해주세요");
        return inputIdField;
    }

    private JTextField inputPasswordField() {
        inputPasswordField = new JTextField(10);
        inputPasswordField.setText("새로 가입할 비밀번호를 입력해주세요");
        return inputPasswordField;
    }

    private JPanel registerButtonsPanel() {
        JPanel panel = new JPanel();
        panel.add(registerButton());
        panel.add(cancelButton());
        return panel;
    }

    private JButton registerButton() {      //TODO : 아이디 같을 경우 에러뜨게 하기
        JButton button = new JButton("회원 가입");
        button.addActionListener(event -> {
            String nickName = inputNickNameField.getText();
            String userName = inputIdField.getText();
            String password = inputPasswordField.getText();

            boolean isNickNameBlank = nickName.equals("");
            boolean isUserNameBlank = userName.equals("");
            boolean isPasswordBlank = password.equals("");
            boolean isUserNameOverlapped = false;

            for (User user : makaoTalk.users()) {
                if (user.userName().equals(userName)) {
                    isUserNameOverlapped = true;
                }
            }

            if (isNickNameBlank || isUserNameBlank || isPasswordBlank || isUserNameOverlapped) {
                inputNickNameField.setText("");
                inputIdField.setText("");
                inputPasswordField.setText("");

                String alertMessage = "";

                if (isUserNameOverlapped) {
                    alertMessage = "다른 회원이 사용중인 아이디입니다. 다른 아이디를 입력해주세요!";
                }

                if (!isUserNameOverlapped) {
                    alertMessage = "모든 빈칸을 채워주세요!";
                }

                JFrame alertWindow = new AlertFrame(alertMessage);
                alertWindow.setVisible(true);
            }

            if (!isNickNameBlank && !isUserNameBlank && !isPasswordBlank && !isUserNameOverlapped) {
                try {
                    makaoTalk.register(userName, password, nickName);

                    UserLoader userLoader = new UserLoader();

                    userLoader.saveUsers(makaoTalk.users());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                this.dispose();
            }
        });
        return button;
    }

    private JButton cancelButton() {
        JButton button = new JButton("취소");
        button.addActionListener(event -> {
            this.dispose();
        });
        return button;
    }
}
