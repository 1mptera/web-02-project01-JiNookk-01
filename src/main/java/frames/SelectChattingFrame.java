package frames;

import models.MakaoTalk;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class SelectChattingFrame extends JFrame {
    private MakaoTalk makaoTalk;

    public SelectChattingFrame(MakaoTalk makaoTalk) {
        this.makaoTalk = makaoTalk;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(normalChatting(), BorderLayout.NORTH);
        this.add(secretChatting());
        this.add(openChatting(),BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.pack();
    }

    private JButton normalChatting() {
        JButton button = new JButton("일반 채팅");
        button.addActionListener(event -> {
            JFrame chattingRoomAddWindow = new ChattingRoomAddFrame(makaoTalk);

            chattingRoomAddWindow.setVisible(true);

            dispose();
        });
        return button;
    }

    private JButton secretChatting() {
        JButton button = new JButton("비밀 채팅");
        return button;
    }

    private JButton openChatting() {
        JButton button = new JButton("오픈 채팅");
        return button;
    }
}
