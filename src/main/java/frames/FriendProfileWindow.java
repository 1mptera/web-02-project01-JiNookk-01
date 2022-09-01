package frames;

import models.User;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

public class FriendProfileWindow extends JFrame {
    private User friend;

    public FriendProfileWindow(User friend) throws IOException {
        this.friend = friend;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 300);

        this.add(profileContainer());
    }

    private JPanel profileContainer() throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(46,46,46));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(profilePicturePanel());
        panel.add(nameAndProfileMessagePanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel profilePicturePanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));
        panel.add(profilePictureLabel());
        return panel;
    }

    private JLabel profilePictureLabel() throws IOException {
        return new JLabel(friend.profile().picture().profilePicture(90, 90));
    }

    private JPanel nameAndProfileMessagePanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(2, 1, 0, 10));
        panel.add(nameLabel());
        panel.add(profileMessageLabel());
        return panel;
    }

    private JLabel nameLabel() {
        JLabel label = new JLabel(friend.name());
        label.setFont(new Font("Serif", Font.PLAIN, 14));
        label.setForeground(new Color(0xEFEBEB));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private JLabel profileMessageLabel() {
        JLabel label = new JLabel(friend.profile().message());
        label.setFont(new Font("Serif", Font.PLAIN, 10));
        label.setForeground(new Color(0x9B9B9B));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
}
