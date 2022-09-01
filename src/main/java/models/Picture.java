package models;

// TODO : 이미지 사이즈 (상수로), 파일 url, imageIcon을 리턴
//  나중에 프로필 편집에서 이미지를 선택할 수 있도록 구현

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture {
    private String imagePath = "./src/main/resources/images/defaultProfileImage.png";
    private ImageIcon profileImageIcon = profilePicture(Profile.PROFILEWIDTH, Profile.PROFILEHEIGHT);

    public Picture() throws IOException {
    }

    public String imagePath() {
        return imagePath;
    }

    public void updateImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ImageIcon profilePicture(int width, int height) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
        Image profileImage = bufferedImage.getScaledInstance(
                width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(profileImage);
    }

    public ImageIcon profileImageIcon() {
        return profileImageIcon;
    }

    public void updateImage(String imagePath) throws IOException {
        updateImagePath(imagePath);
        profileImageIcon = profilePicture(Profile.PROFILEWIDTH, Profile.PROFILEHEIGHT);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        Picture otherPicture = (Picture) other;

        return this.imagePath.equals(otherPicture.imagePath);
    }

    @Override
    public String toString() {
        return "파일경로: " + imagePath;
    }
}
