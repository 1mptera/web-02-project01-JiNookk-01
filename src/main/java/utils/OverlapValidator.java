package utils;

import models.MakaoTalk;
import models.Relation.UsersRelation;
import models.User;

public class OverlapValidator {
    public boolean validateUserRelations(User friend, MakaoTalk makaoTalk) {
        for (UsersRelation usersRelation : makaoTalk.loginUserFriends()) {
            if (usersRelation.friendId() == friend.id()) {
                return true;
            }
        }

        return false;
    }
}
