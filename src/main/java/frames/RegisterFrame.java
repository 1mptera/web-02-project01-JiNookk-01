package frames;

import models.MakaoTalk;
import models.User;
import utils.loader.ProfileLoader;
import utils.loader.UserLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.io.IOException;

public class RegisterFrame extends JFrame {
    private final MakaoTalk makaoTalk;

    private JTextField inputIdField;
    private JTextField inputPasswordField;
    private JTextField inputNickNameField;
    private JTextField inputPhoneNumberField;

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
        panel.setLayout(new GridLayout(5, 1));
        panel.add(inputNickNameField());
        panel.add(inputIdField());
        panel.add(inputPasswordField());
        panel.add(inputPhoneNumberField());
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

    private JTextField inputPhoneNumberField() {
        inputPhoneNumberField = new JTextField(10);
        inputPhoneNumberField.setText("전화번호를 입력하세요");
        return inputPhoneNumberField;
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
            String phoneNumber = inputPhoneNumberField.getText();

            boolean isNickNameBlank = nickName.equals("");
            boolean isUserNameBlank = userName.equals("");
            boolean isPasswordBlank = password.equals("");
            boolean isPhoneNumberBlank = phoneNumber.equals("");
            boolean isUserNameOverlapped = false;

            for (User user : makaoTalk.undeletedUsers()) {
                if (user.userName().equals(userName)) {
                    isUserNameOverlapped = true;
                }
            }

            if (isNickNameBlank || isUserNameBlank ||
                    isPasswordBlank || isPhoneNumberBlank || isUserNameOverlapped) {
                inputNickNameField.setText("");
                inputIdField.setText("");
                inputPasswordField.setText("");
                inputPhoneNumberField.setText("");

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

            if (!isNickNameBlank && !isUserNameBlank && !isPasswordBlank &&
                    !isPhoneNumberBlank && !isUserNameOverlapped) {
                try {
                    makaoTalk.register(userName, password, nickName, phoneNumber);

                    new UserLoader().saveUsers(makaoTalk.users());

                    new ProfileLoader().saveProfiles(makaoTalk.profiles());
//                    JFrame alertFrame = new AlertFrame("회원가입이 완료되었습니다!");
                    JFrame alertFrame = new AlertFrame(makaoTalk.undeletedProfiles().toString());
                    alertFrame.setVisible(true);
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
