package models.Relation;

//TODO : 관계를 관리
// 채팅방 - 메시지

public class ChattingRoomMessageRelation {
    private final long chattingRoomId;
    private final long messageId;

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

    public String toCsvRow() {
        return chattingRoomId + "," + messageId;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        ChattingRoomMessageRelation otherChattingRoomMessageRelation =
                (ChattingRoomMessageRelation) other;

        return this.chattingRoomId == otherChattingRoomMessageRelation.chattingRoomId() &&
                this.messageId == otherChattingRoomMessageRelation.messageId;
    }

    @Override
    public String toString() {
        return chattingRoomId + "," + messageId;
    }
}
