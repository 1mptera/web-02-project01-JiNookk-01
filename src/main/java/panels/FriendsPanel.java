package panels;

import models.MakaoTalk;
import models.Relation.UsersRelation;
import models.User;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FriendsPanel extends JPanel {
    private MakaoTalk makaoTalk;
    private JPanel friendsContainer;

    public FriendsPanel(MakaoTalk makaoTalk) {
        this.makaoTalk = makaoTalk;

        this.add(friendsContainer());

        for (UsersRelation usersRelation : makaoTalk.relation().loginUserFriends()) {      //TODO:유저의 친구를 표시하는 메서드
            User friend = makaoTalk.user(usersRelation.friendId());

            friendsContainer.add(friendPanel(friend));
        }
    }

    private JPanel friendsContainer() {
        friendsContainer = new JPanel();
        friendsContainer.setLayout(new GridLayout(makaoTalk.relation().loginUserFriends().size(), 1));
        return friendsContainer;
    }

    private JPanel friendPanel(User friend) {
        JPanel panel = new JPanel();
        panel.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFrame friendProfileWindow = new JFrame(friend.name());
                        friendProfileWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        friendProfileWindow.setSize(400, 300);

                        friendProfileWindow.setVisible(true);
                    }
                }
        );
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
