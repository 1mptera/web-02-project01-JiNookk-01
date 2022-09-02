package models;

public class Invitation {
    private boolean checked = false;
    private final long userId;

    public Invitation(long userId) {
        this.userId = userId;
    }

    public long userId() {
        return userId;
    }

    public boolean checked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void defalutInvitation() {
        checked = true;
    }

    @Override
    public String toString() {
        return "초대된 유저 ID : " + userId;
    }
}
