package models.Relation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserChattingRoomRelationTest {
    @Test
    void relationUserChattingRoom() {
        UserChattingRoomRelation userChattingRoomRelation =
                new UserChattingRoomRelation(1, 4);

        assertEquals(1, userChattingRoomRelation.userId());
        assertEquals(4, userChattingRoomRelation.chattingRoomId());
    }
}
