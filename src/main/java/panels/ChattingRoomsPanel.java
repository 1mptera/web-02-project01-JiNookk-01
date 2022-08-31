package panels;

import frames.ChattingRoomWindow;
import models.ChattingRoom.ChattingRoom;
import models.MakaoTalk;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChattingRoomsPanel extends JPanel {
    private MakaoTalk makaoTalk;

    public ChattingRoomsPanel(MakaoTalk makaoTalk) {
        this.makaoTalk = makaoTalk;

        this.add(chattingRoomsContainer());
    }

    private JPanel chattingRoomsContainer() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(makaoTalk.relativeChattingRooms().size(),1));
        for (ChattingRoom chattingRoom : makaoTalk.relativeChattingRooms()) {
            panel.add(chattingRoomPanel(chattingRoom));
        }
        return panel;
    }

    private JPanel chattingRoomPanel(ChattingRoom chattingRoom) {
        JPanel panel = new JPanel();
        panel.add(chattingRoomProfilePicturePanel(chattingRoom));
        panel.add(chattingRoomDescriptionPanel(chattingRoom));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makaoTalk.openChattingRoom(chattingRoom.id());

                JFrame chattingRoomWindow = new ChattingRoomWindow(makaoTalk, chattingRoom);

                chattingRoomWindow.setVisible(true);
            }
        });
        return panel;
    }

    private JPanel chattingRoomDescriptionPanel(ChattingRoom chattingRoom) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(new JLabel(chattingRoom.title()));
        panel.add(new JLabel(chattingRoom.previewMessage()));
        return panel;
    }

    private JPanel chattingRoomProfilePicturePanel(ChattingRoom chattingRoom) {
        return new JPanel();
    }
}
