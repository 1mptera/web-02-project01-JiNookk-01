package application;

import models.Profile;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Practice {
    public static void main(String[] args) throws IOException {
        Practice practice = new Practice();
        practice.run();
    }

    private void run() throws IOException {
        JFrame frame = new JFrame();
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(northPanel(), BorderLayout.NORTH);
        frame.add(scrollPanePanel());
        frame.add(southPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel northPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("North"));
        return panel;
    }

    private JPanel scrollPanePanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new GridLayout(100,1));
        for (int i = 0; i < 30; i += 1) {

            scrollPanel.add(picturePanel());
        }
        JScrollPane scrollPane = new JScrollPane(scrollPanel);
//        scrollPane.add(scrollContent());
        panel.add(scrollPane);
        return panel;
    }

    private JPanel picturePanel() throws IOException {
        JPanel panel = new JPanel();
        BufferedImage myPicture = ImageIO.read(new File("./src/main/resources/images/junhyung.png"));
        Image profileImage = myPicture.getScaledInstance(
                100, 100, Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(profileImage));
        panel.add(picLabel);
        panel.add(new JLabel("MakaoTalk"));
        return panel;
    }

    private JPanel scrollContent() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Hi"));
        return panel;
    }

    private JPanel southPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("South"));
        return panel;
    }
}
