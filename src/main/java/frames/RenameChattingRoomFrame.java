package frames;

import models.ChattingRoom.ChattingRoom;

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
import java.awt.Font;
import java.io.IOException;

public class RenameChattingRoomFrame extends JFrame {
    private final ChattingRoom chattingRoom;
    private JTextField renameField;

    public RenameChattingRoomFrame(ChattingRoom chattingRoom, JButton button) throws IOException {
        this.chattingRoom = chattingRoom;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 360);
        this.setLocationRelativeTo(button);
        this.add(container());
    }

    private JPanel container() throws IOException {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(50,50,50));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.add(titlePanel(),BorderLayout.NORTH);
        panel.add(renamePanel(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel titlePanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 150));
        panel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        panel.setOpaque(false);
        panel.add(titleLabel(),BorderLayout.NORTH);
        panel.add(chattingRoomProfileLabel(),BorderLayout.SOUTH);
        return panel;
    }

    private JLabel titleLabel() {
        JLabel label = new JLabel("채팅방 정보 설정");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 16));
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
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension());
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        panel.add(renameField(),BorderLayout.NORTH);
        panel.add(renameButton(),BorderLayout.SOUTH);
        return panel;
    }

    private JTextField renameField() {
        renameField = new JTextField(10);
        renameField.setText(chattingRoom.title());
        return renameField;
    }

    private JButton renameButton() {
        JButton button = new JButton("확인");
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
