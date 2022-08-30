package models.Relation;

//TODO : 관계를 관리
// 유저 - 유저, 유저 - 메시지, 유저 - 채팅방, 채팅방 - 메시지

public class UsersRelation {
    private long myId;
    private long friendId;

    public UsersRelation(long myId, long friendId) {
        this.myId = myId;
        this.friendId = friendId;
    }

    public long myId() {
        return myId;
    }

    public long friendId() {
        return friendId;
    }

    public String toCsvRow() {
        return myId + "," + friendId;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        UsersRelation otherUserRelation = (UsersRelation) other;

        return this.myId == otherUserRelation.myId &&
                this.friendId == otherUserRelation.friendId;
    }

    @Override
    public String toString() {
        return myId + " - " + friendId;
    }
}
