package panels;

import frames.ChattingRoomWindow;
import models.ChattingRoom.ChattingRoom;
import models.MakaoTalk;
import models.Profile;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChattingRoomsPanel extends JPanel {
    private MakaoTalk makaoTalk;

    public ChattingRoomsPanel(MakaoTalk makaoTalk) throws IOException {
        this.makaoTalk = makaoTalk;

        this.setOpaque(false);
        this.add(chattingRoomsContainer());
    }

    private JPanel chattingRoomsContainer() throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(makaoTalk.relativeChattingRooms().size(),1));

        for (ChattingRoom chattingRoom : makaoTalk.relativeChattingRooms()) {
            panel.add(chattingRoomPanel(chattingRoom));
        }
        return panel;
    }

    private JPanel chattingRoomPanel(ChattingRoom chattingRoom) throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(350,55));
        panel.add(chattingRoomProfilePicturePanel(chattingRoom),BorderLayout.WEST);
        panel.add(chattingRoomDescriptionPanel(chattingRoom));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makaoTalk.openChattingRoom(chattingRoom.id());

                try {
                    JFrame chattingRoomWindow = new ChattingRoomWindow(makaoTalk, chattingRoom);
                    chattingRoomWindow.setVisible(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return panel;
    }

    private JPanel chattingRoomDescriptionPanel(ChattingRoom chattingRoom) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(2, 1));
        panel.add(titleLabel(chattingRoom));
        panel.add(previewMessageLabel(chattingRoom));
        return panel;
    }

    private JLabel titleLabel(ChattingRoom chattingRoom) {
        JLabel label = new JLabel(chattingRoom.title());
        label.setFont(new Font("Serif", Font.PLAIN, 14));
        label.setForeground(new Color(0xEFEBEB));
        return label;
    }

    private JLabel previewMessageLabel(ChattingRoom chattingRoom) {
        JLabel label = new JLabel(chattingRoom.previewMessage());
        label.setFont(new Font("Serif", Font.PLAIN, 10));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private JPanel chattingRoomProfilePicturePanel(ChattingRoom chattingRoom) throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(profilePictureLabel(chattingRoom));
        return panel;
    }

    private JLabel profilePictureLabel(ChattingRoom chattingRoom) throws IOException {
        ImageIcon image = chattingRoom.invitedUsers().get(0)
                .profile().picture().profilePicture(Profile.PROFILEWIDTH, Profile.PROFILEHEIGHT);
        return new JLabel(image);
    }
}
