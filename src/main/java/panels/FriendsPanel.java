package panels;

import models.MakaoTalk;
import models.Relation.UsersRelation;
import models.User;
import utils.MouseEventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class FriendsPanel extends JPanel {
    private MakaoTalk makaoTalk;
    private final MouseEventListener mouseListener = new MouseEventListener();
    private JPanel friendsContainer;

    public FriendsPanel(MakaoTalk makaoTalk) {
        this.makaoTalk = makaoTalk;

        this.add(friendsContainer());

        for (UsersRelation usersRelation : makaoTalk.loginUserFriends()) {      //TODO:유저의 친구를 표시하는 메서드
            User friend = makaoTalk.user(usersRelation.friendId());

            friendsContainer.add(friendPanel(friend));
        }
    }

    private JPanel friendsContainer() {
        friendsContainer = new JPanel();
        friendsContainer.setLayout(new GridLayout(makaoTalk.loginUserFriends().size(), 1));
        return friendsContainer;
    }

    private JPanel friendPanel(User friend) {
        JPanel panel = new JPanel();
        panel.addMouseListener(mouseListener.openFriendProfileWindow(friend));
        panel.add(friendProfilePicturePanel(friend));
        panel.add(friendDescriptionPanel(friend));
        return panel;
    }


    private JPanel friendProfilePicturePanel(User friend) {
        return new JPanel();
    }

    private JPanel friendDescriptionPanel(User friend) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(new JLabel(friend.name()));
        panel.add(new JLabel(friend.profile().profileMessage()));
        return panel;
    }

}
