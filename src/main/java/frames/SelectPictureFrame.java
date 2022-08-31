package frames;

import models.MakaoTalk;
import models.Profile;
import utils.loader.ProfileLoader;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SelectPictureFrame extends JFrame {
    private MakaoTalk makaoTalk;

    public SelectPictureFrame(MakaoTalk makaoTalk) throws IOException {
        this.makaoTalk = makaoTalk;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(270,200);

        this.add(picturesButtonPanel());
        this.setLocationRelativeTo(null);
    }

    private JPanel picturesButtonPanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        String pathName1 = "./src/main/resources/images/jinwook.png";
        String pathName2 = "./src/main/resources/images/jingseong.png";
        String pathName3 = "./src/main/resources/images/junhyung.png";
        String defaultPathName = "./src/main/resources/images/defaultProfileImage.png";

        panel.add(pictureButtonPanel("1번 프로필",pathName1));
        panel.add(pictureButtonPanel("2번 프로필",pathName2));
        panel.add(pictureButtonPanel("3번 프로필",pathName3));
        panel.add(pictureButtonPanel("기본 프로필",defaultPathName));;
        return panel;
    }

    private JPanel pictureButtonPanel(String title,String pathname) throws IOException {
        JPanel panel = new JPanel();
        panel.add(pictureLabel(pathname));
        panel.add(changeProfileButton(title,pathname));
        return panel;
    }

    private JLabel pictureLabel(String pathname) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(pathname));
        Image pictureImage = bufferedImage.getScaledInstance(
                Profile.PROFILEWIDTH,Profile.PROFILEHEIGHT,Image.SCALE_DEFAULT);

        JLabel label = new JLabel(new ImageIcon(pictureImage));
        label.addMouseListener(new MouseAdapter() {
        });
        return label;
    }

    private JButton changeProfileButton(String title, String pathName) {
        JButton button = new JButton(title);
        button.addActionListener(event -> {
            try {
                makaoTalk.user(makaoTalk.loginUserId()).profile().picture().updateImage(pathName);
                new ProfileLoader().saveProfiles(makaoTalk.profiles());
                dispose();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }
}
