package models.Relation;

//TODO : 관계를 관리
// 채팅방 - 메시지

public class ChattingRoomMessageRelation {
    private long chattingRoomId;
    private long messageId;

    public ChattingRoomMessageRelation(long chattingRoomId, long messageId) {
        this.chattingRoomId = chattingRoomId;
        this.messageId = messageId;
    }

    public long chattingRoomId() {
        return chattingRoomId;
    }

    public long messageId() {
        return messageId;
    }
}
