package panels;

import frames.ChattingRoomWindow;
import models.ChattingRoom.ChattingRoom;
import models.MakaoTalk;
import models.Profile;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ChattingRoomsPanel extends JPanel {
    private MakaoTalk makaoTalk;
    private JPanel contentPanel;
    private JPanel chattingRoomsContainer;

    public ChattingRoomsPanel(MakaoTalk makaoTalk, JPanel contentPanel) throws IOException {
        this.makaoTalk = makaoTalk;
        this.contentPanel = contentPanel;

        this.setOpaque(false);
        this.add(chattingRoomsContainer());
    }

    private JPanel chattingRoomsContainer() throws IOException {
        chattingRoomsContainer = new JPanel();
        chattingRoomsContainer.setOpaque(false);
        chattingRoomsContainer.setLayout(new GridLayout(makaoTalk.relativeChattingRooms().size(),1));

        addChattingRoomPanels();

        return chattingRoomsContainer;
    }

    private void addChattingRoomPanels() throws IOException {
        chattingRoomsContainer.removeAll();
        for (ChattingRoom chattingRoom : makaoTalk.relativeChattingRooms()) {
            chattingRoomsContainer.add(chattingRoomPanel(chattingRoom));
        }
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
                    chattingRoomWindow.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            try {
                                addChattingRoomPanels();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                            contentPanel.setVisible(false);
                            contentPanel.setVisible(true);
                        }
                    });
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
        label.setForeground(new Color(0x9B9B9B));
        label.setFont(new Font("Serif", Font.PLAIN, 12));
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
