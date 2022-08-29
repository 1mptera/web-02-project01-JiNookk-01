package models;

import models.Relation.UsersRelation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersRelationTest {
    @Test
    void relationBetweenUser() {
        long myId = 1;
        long otherId = 5;

        UsersRelation usersRelation = new UsersRelation(myId,otherId);

        assertEquals(1, usersRelation.myId());
        assertEquals(5, usersRelation.otherId());
    }

    @Test
    void findFriendsId() {
        List<UsersRelation> userRelations = List.of(
                new UsersRelation(1, 5),
                new UsersRelation(2, 5),
                new UsersRelation(7, 3),
                new UsersRelation(8, 5),
                new UsersRelation(2, 7),
                new UsersRelation(3, 5),
                new UsersRelation(7, 5)
        );


//        assertEquals(List.of(3,5),userRelations.findFriendsId(1));
    }
}