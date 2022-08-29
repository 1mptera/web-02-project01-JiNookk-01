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
}
