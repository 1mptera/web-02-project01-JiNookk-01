package utils;

import models.MakaoTalk;
import models.Profile;
import models.Relation.UsersRelation;
import models.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OverlapValidatorTest {
    @Test
    void validateUserRelations() throws IOException {
        OverlapValidator overlapValidator = new OverlapValidator();

        boolean deleted = false;

        User user1 = new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));
        User user2 = new User(2, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));
        User user3 = new User(3, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));
        User user4 = new User(4, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));
        User user5 = new User(5, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));
        User user6 = new User(6, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));

        MakaoTalk makaoTalk = new MakaoTalk();

        makaoTalk.login(1);

        makaoTalk.relation().addUsersRelation(new UsersRelation(1,4));
        makaoTalk.relation().addUsersRelation(new UsersRelation(2,3));
        makaoTalk.relation().addUsersRelation(new UsersRelation(1,2));
        makaoTalk.relation().addUsersRelation(new UsersRelation(3,1));
        makaoTalk.relation().addUsersRelation(new UsersRelation(1,6));
        makaoTalk.relation().addUsersRelation(new UsersRelation(2,5));

        assertTrue(overlapValidator.
                validateUserRelations(user2, makaoTalk));
        assertFalse(overlapValidator.
                validateUserRelations(user3, makaoTalk));
        assertTrue(overlapValidator.
                validateUserRelations(user4, makaoTalk));
        assertFalse(overlapValidator.
                validateUserRelations(user5, makaoTalk));
        assertTrue(overlapValidator.
                validateUserRelations(user6, makaoTalk));


    }

}