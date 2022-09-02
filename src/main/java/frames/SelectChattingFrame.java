package frames;

import models.MakaoTalk;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SelectChattingFrame extends JFrame {
    private MakaoTalk makaoTalk;

    public SelectChattingFrame(MakaoTalk makaoTalk) {
        this.makaoTalk = makaoTalk;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(normalChatting(), BorderLayout.NORTH);
        this.add(secretChatting());
        this.add(openChatting(), BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.pack();
    }

    private JButton normalChatting() {
        JButton button = new JButton("일반 채팅");
        button.addActionListener(event -> {
            openChattingRoom("일반");
        });
        return button;
    }

    private JButton secretChatting() {
        JButton button = new JButton("비밀 채팅");
        button.addActionListener(event -> {
            openChattingRoom("비밀");
        });
        return button;
    }

    private void openChattingRoom(String type) {
        JFrame chattingRoomAddWindow = new ChattingRoomAddFrame(makaoTalk, type);

        chattingRoomAddWindow.setVisible(true);

        chattingRoomAddWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dispose();
            }
        });
    }
}
