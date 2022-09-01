package models;

// TODO : 프로필 메시지, 프로필 사진, 프로필 음악 -> 엔티티

import java.io.IOException;
import java.util.Objects;

public class Profile {
    public static final int PROFILEWIDTH = 40;
    public static final int PROFILEHEIGHT = 40;
    public static final int MYPROFILEWIDTH = 55;
    public static final int MYPROFILEHEIGHT = 55;
    public static final int MODIFYPROFILEWIDTH = 110;
    public static final int MODIFYPROFILEHEIGHT = 90;

    private long id;
    private String message = "";
    private Picture picture;
    private Music music = null;
    private boolean deleted;

    public Profile(long id, boolean deleted) throws IOException {
        this.id = id;
        this.deleted = deleted;
        this.picture = new Picture();
    }

    public long id() {
        return id;
    }

    public String message() {
        return message;
    }

    public Picture picture() {
        return picture;
    }

    public Music music() {
        return music;
    }

    public boolean deleted() {
        return deleted;
    }

    public void updateProfileMessage(String message) {
        this.message = message;
    }

    public void delete() {
        deleted = true;
    }

    public String toCsvRow() {
        return id + "," + message + "," + picture.imagePath() + "," + deleted;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        Profile otherProfile = (Profile) other;

        return Objects.equals(this.id, otherProfile.id);
    }

    @Override
    public String toString() {
        return "프로필 메시지: " + message + ", 프로필 사진: " + picture +
                ", 프로필 음악: " + music;
    }
}
