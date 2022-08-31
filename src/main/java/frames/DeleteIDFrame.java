package frames;

import models.MakaoTalk;
import models.User;
import panels.LoginPanel;
import utils.loader.UserLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.IOException;

public class DeleteIDFrame extends JFrame {
    private MakaoTalk makaoTalk;
    private JPanel imagePanel;
    private JPanel contentPanel;

    public DeleteIDFrame(MakaoTalk makaoTalk, JPanel imagePanel, JPanel contentPanel) {
        this.makaoTalk = makaoTalk;
        this.imagePanel = imagePanel;
        this.contentPanel = contentPanel;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(new JLabel("정말로 삭제하시겠습니까? 삭제후엔 복구가 불가능합니다."), BorderLayout.NORTH);
        this.add(deleteButtonPanel());
        this.setLocationRelativeTo(null);
        this.pack();
    }

    private JPanel deleteButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(confirmDeleteButton());
        panel.add(calcelButton());
        return panel;
    }

    private JButton confirmDeleteButton() {
        JButton button = new JButton("확인");
        button.addActionListener(event -> {
            User loginUser = makaoTalk.user(makaoTalk.loginUserId());

            loginUser.deleteID();

            try {
                new UserLoader().saveUsers(makaoTalk.users());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            dispose();

            logout();
        });
        return button;
    }

    private void logout() {
        imagePanel.removeAll();
        contentPanel.removeAll();

        JPanel loginPanel = new LoginPanel(makaoTalk, imagePanel, contentPanel);
        contentPanel.add(loginPanel);
        contentPanel.setOpaque(false);

        imagePanel.add(contentPanel);
        imagePanel.setVisible(false);
        imagePanel.setVisible(true);
    }

    private JButton calcelButton() {
        JButton button = new JButton("취소");
        button.addActionListener(event -> {
            dispose();
        });
        return button;
    }
}
