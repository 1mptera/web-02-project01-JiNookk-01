package models.Relation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChattingRoomMessageRelationTest {
    @Test
    void chattingRoomMessageRelationTest() {
        ChattingRoomMessageRelation chattingRoomMessageRelation =
                new ChattingRoomMessageRelation(1, 4);

        assertEquals(1, chattingRoomMessageRelation.chattingRoomId());
        assertEquals(4, chattingRoomMessageRelation.messageId());
    }
}
