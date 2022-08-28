package application;

import models.MakaoTalk;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Main {

    private JFrame frame;
    private JPanel imagePanel;
    private JPanel contentPanel;

    public static void main(String[] args) {
        Main application = new Main();
        application.run();
    }

    private void run() {
        MakaoTalk makaoTalk = new MakaoTalk();
//        makaoTalk.loadStatus();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(390, 637);

        initImagePanel();

        initContentPanel();

        start();

        frame.setVisible(true);
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
    }

    private void initImagePanel() {
        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(100, 0));
    }

    private void start() {
        frame.add(imagePanel, BorderLayout.WEST);

        imagePanel.add(friendsButton());
        imagePanel.add(chattingButton());

    }

    private JButton friendsButton() {
        JButton button = new JButton("친구목록");
        button.addActionListener(event -> {

        });
        return button;
    }

    private JButton chattingButton() {
        JButton button = new JButton();
        return button;
    }
}
