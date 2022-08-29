package frames;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class AlertFrame extends JFrame {
    public AlertFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(new JLabel("모든 빈칸을 채워주세요!"), BorderLayout.NORTH);
        this.add(exitButton());
        this.pack();
    }

    private JButton exitButton() {
        JButton button = new JButton("확인");
        button.addActionListener(event -> {
            this.dispose();
        });
        return button;
    }
}
