package frames;

import models.MakaoTalk;
import panels.ImagePanel;
import panels.LoginPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
    private JPanel imagePanel;
    private JPanel contentPanel;
    private MakaoTalk makaoTalk;

    public MainFrame(MakaoTalk makaoTalk) {
        this.makaoTalk = makaoTalk;

        this.setTitle("Makao Talk");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(490, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        initImagePanel();

        initContentPanel();

        initLoginMenus();
    }

    private void initImagePanel() {
        imagePanel = new ImagePanel("./src/main/resources/images/makaoTalk.png");
        imagePanel.setOpaque(false);
        imagePanel.setLayout(new BorderLayout());
        this.add(imagePanel);
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
    }

    private void initLoginMenus() {
        JPanel loginPanel = new LoginPanel(makaoTalk,imagePanel,contentPanel);

        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(loginPanel);

        imagePanel.add(contentPanel);
    }
}
