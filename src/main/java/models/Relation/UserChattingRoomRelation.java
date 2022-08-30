package models.Relation;

//TODO : 관계를 관리
//  유저 - 채팅방

public class UserChattingRoomRelation {
    private long userId;
    private long chattingRoomId;

    public UserChattingRoomRelation(long userId, long chattingRoomId) {
        this.userId = userId;
        this.chattingRoomId = chattingRoomId;
    }

    public long userId() {
        return userId;
    }

    public long chattingRoomId() {
        return chattingRoomId;
    }

    public String toCsvRow() {
        return userId + "," + chattingRoomId;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        UserChattingRoomRelation otherUserChattingRoomRelation =
                (UserChattingRoomRelation) other;

        return this.userId == otherUserChattingRoomRelation.userId &&
                this.chattingRoomId == otherUserChattingRoomRelation.chattingRoomId;
    }

    @Override
    public String toString() {
        return "유저아이디: " + userId + " - 채팅방 아이디: " + chattingRoomId;
    }
}
