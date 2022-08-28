package models;

// TODO : 프로필 메시지, 프로필 사진, 프로필 음악 -> 엔티티

import java.util.Objects;
import java.util.UUID;

public class Profile {
    private String message = "";
    private Picture picture = null;
    private Music music = null;
    private String id;

    public Profile() {
        id = UUID.randomUUID().toString();
    }

    public String profileMessage() {
        return message;
    }

    public Picture profilePicture() {
        return picture;
    }

    public Music profileMusic() {
        return music;
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
