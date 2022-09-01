package panels;

import frames.FriendProfileWindow;
import models.MakaoTalk;
import models.Profile;
import models.Relation.UsersRelation;
import models.User;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class FriendsPanel extends JPanel {
    private final MakaoTalk makaoTalk;
    private JPanel friendsContainer;

    public FriendsPanel(MakaoTalk makaoTalk) throws IOException {
        this.makaoTalk = makaoTalk;
        this.setBackground(new Color(38, 38, 38));
        this.setLayout(new BorderLayout());
        this.add(myProfilePanel(), BorderLayout.NORTH);
        this.add(container());

        addFriendPanels(makaoTalk);
    }

    private JPanel container() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(friendsContainer());
        return panel;
    }

    private JPanel myProfilePanel() throws IOException {
        User loginUser = makaoTalk.user(makaoTalk.loginUserId());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(60,60,60)));
        panel.setPreferredSize(new Dimension(0, 100));
        panel.add(myProfilePictureLabel(loginUser), BorderLayout.WEST);
        addDescription(loginUser, panel);
        return panel;
    }

    private JLabel myProfilePictureLabel(User loginUser) throws IOException {
        ImageIcon profilePicture = loginUser.profile().picture().profilePicture(60, 60);
        return new JLabel(profilePicture);
    }

    private JPanel friendsContainer() {
        friendsContainer = new JPanel();
        friendsContainer.setOpaque(false);
        friendsContainer.setLayout(new GridLayout(makaoTalk.relation().loginUserFriends().size(), 1));
        return friendsContainer;
    }

    private void addFriendPanels(MakaoTalk makaoTalk) throws IOException {
        for (UsersRelation usersRelation : makaoTalk.relation().loginUserFriends()) {      //TODO:유저의 친구를 표시하는 메서드
            User friend = makaoTalk.user(usersRelation.friendId());

            friendsContainer.add(friendPanel(friend));
        }
    }

    private JPanel friendPanel(User friend) throws IOException {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 55));
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.add(friendProfilePicturePanel(friend), BorderLayout.WEST);
        addDescription(friend, panel);
        return panel;
    }

    private void addDescription(User friend, JPanel panel) {
        panel.add(friendDescriptionPanel(friend));
        panel.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            JFrame friendProfileWindow = new FriendProfileWindow(friend);
                            friendProfileWindow.setVisible(true);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );
    }

    private JPanel friendProfilePicturePanel(User friend) throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(profilePictureLabel(friend));
        return panel;
    }

    private JLabel profilePictureLabel(User friend) throws IOException {
        ImageIcon image = friend.profile().picture()
                .profilePicture(Profile.PROFILEWIDTH, Profile.PROFILEHEIGHT);
        return new JLabel(image);
    }

    private JPanel friendDescriptionPanel(User friend) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        panel.add(nameLabel(friend));
        panel.add(profileMessagePanel(friend));
        return panel;
    }

    private JLabel nameLabel(User friend) {
        JLabel label = new JLabel(friend.name());
        label.setFont(new Font("Serif", Font.PLAIN, 14));
        label.setForeground(new Color(0xEFEBEB));
        return label;
    }

    private JLabel profileMessagePanel(User friend) {
        JLabel label = new JLabel(friend.profile().message());
        label.setForeground(new Color(0x9B9B9B));
        label.setFont(new Font("Serif", Font.PLAIN, 10));
        return label;
    }
}
