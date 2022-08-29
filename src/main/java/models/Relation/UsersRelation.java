package models.Relation;

//TODO : 관계를 관리
// 유저 - 유저, 유저 - 메시지, 유저 - 채팅방, 채팅방 - 메시지

public class UsersRelation {
    private long myId;
    private long otherId;

    public UsersRelation(long myId, long otherId) {
        this.myId = myId;
        this.otherId = otherId;
    }

    public long myId() {
        return myId;
    }

    public long otherId() {
        return otherId;
    }
}
