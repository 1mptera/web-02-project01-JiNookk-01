package frames;

import models.ChattingRoom.ChattingRoom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

public class RenameChattingRoomFrame extends JFrame {
    private ChattingRoom chattingRoom;
    private JTextField renameField;

    public RenameChattingRoomFrame(ChattingRoom chattingRoom, JButton button) throws IOException {
        this.chattingRoom = chattingRoom;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 390);
        this.setLocationRelativeTo(button);

        this.add(titlePanel());
        this.add(renamePanel());
    }

    private JPanel titlePanel() throws IOException {
        JPanel panel = new JPanel();
        panel.add(titleLabel());
        panel.add(chattingRoomProfileLabel());
        return panel;
    }

    private JLabel titleLabel() {
        JLabel label = new JLabel("채팅방 정보 설정");
        label.setFont(new Font("Serif", Font.PLAIN, 14));
        label.setForeground(new Color(0xEFEBEB));
        return label;
    }

    private JLabel chattingRoomProfileLabel() throws IOException {
        ImageIcon chattingRoomIcon = chattingRoom.invitedUsers().get(0)
                .profile().picture().profilePicture(90, 90);

        return new JLabel(chattingRoomIcon);
    }

    private JPanel renamePanel() {
        JPanel panel = new JPanel();
        panel.add(renameField());
        panel.add(renameButton());
        return panel;
    }

    private JTextField renameField() {
        renameField = new JTextField(10);
        return renameField;
    }

    private JButton renameButton() {
        JButton button = new JButton();
        button.addActionListener(event -> {
            String newTitle = renameField.getText();

            if (!newTitle.equals("")) {
                chattingRoom.updateTitle(newTitle);
                dispose();
            }
        });
        return button;
    }
}
