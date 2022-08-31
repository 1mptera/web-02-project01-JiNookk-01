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

    @Test
    void toCsvRow() {
        ChattingRoomMessageRelation chattingRoomMessageRelation1 =
                new ChattingRoomMessageRelation(1, 2);

        ChattingRoomMessageRelation chattingRoomMessageRelation2 =
                new ChattingRoomMessageRelation(1, 1);

        String line1 = chattingRoomMessageRelation1.toCsvRow();
        String line2 = chattingRoomMessageRelation2.toCsvRow();

        assertEquals("1,2",line1);
        assertEquals("1,1",line2);
    }
}
