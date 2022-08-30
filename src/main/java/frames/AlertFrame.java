package frames;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class AlertFrame extends JFrame {
    public AlertFrame(String alertMessage) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(new JLabel(alertMessage), BorderLayout.NORTH);
        this.add(exitButton());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JButton exitButton() {
        JButton button = new JButton("확인");
        button.addActionListener(event -> {
            this.dispose();
        });
        return button;
    }
}
