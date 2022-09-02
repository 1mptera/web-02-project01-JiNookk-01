package frames;

import models.ChattingRoom.ChattingRoom;
import models.Invitation;
import models.MakaoTalk;
import models.Profile;
import models.Relation.UsersRelation;
import models.User;
import utils.loader.ChattingRoomLoader;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChattingRoomAddFrame extends JFrame {
    private final MakaoTalk makaoTalk;
    private List<Invitation> invitations;
    private String type;

    public ChattingRoomAddFrame(MakaoTalk makaoTalk, String type) throws IOException {
        this.makaoTalk = makaoTalk;
        invitations = initInvitations(makaoTalk);
        this.type = type;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 480);

        this.add(inviteContainer());
        this.setLocationRelativeTo(null);
    }

    private List<Invitation> initInvitations(MakaoTalk makaoTalk) {
        invitations = new ArrayList<>();

        Invitation loginUserInvitation = new Invitation(makaoTalk.loginUserId());
        loginUserInvitation.defalutInvitation();

        invitations.add(loginUserInvitation);
        return invitations;
    }

    private JPanel inviteContainer() throws IOException {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(45,45,45));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());

        panel.add(searchPanel(), BorderLayout.NORTH);
        panel.add(friendListPanel());
        panel.add(inviteButtonPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel friendListPanel() throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(friendListContainer());
        return panel;
    }

    private JPanel friendListContainer() throws IOException {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(makaoTalk.relation().loginUserFriends().size(), 1));

        for (UsersRelation usersRelation : makaoTalk.relation().loginUserFriends()) {
            User friend = makaoTalk.user(usersRelation.friendId());

            Invitation invitation = new Invitation(friend.id());

            invitations.add(invitation);

            panel.add(friendPanel(friend, invitation));
        }

        return panel;
    }

    private JPanel friendPanel(User friend, Invitation invitation) throws IOException {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(240,40));
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.add(friendCheckBox(friend, invitation),BorderLayout.WEST);
        panel.add(profilePicture(friend),BorderLayout.EAST);
        return panel;
    }

    private JCheckBox friendCheckBox(User friend, Invitation invitation) {
        JCheckBox checkBox = new JCheckBox("   " + friend.name());
        checkBox.setFont(new Font("Serif", Font.PLAIN, 12));
        checkBox.setForeground(new Color(0xEFEBEB));
        checkBox.setHorizontalAlignment(JCheckBox.LEFT);
        checkBox.addActionListener(event -> {
            if (checkBox.isSelected()) {
                invitation.setChecked(true);
            }

            if (!checkBox.isSelected()) {
                invitation.setChecked(false);
            }
        });
        return checkBox;
    }

    private JLabel profilePicture(User friend) throws IOException {
        ImageIcon profileIcon = friend.profile()
                .picture().profilePicture(Profile.PROFILEWIDTH,Profile.PROFILEHEIGHT);

        JLabel label = new JLabel(profileIcon);
        label.setHorizontalAlignment(JLabel.RIGHT);
        return label;
    }

    private JPanel searchPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(2,1));
        panel.add(inviteTitleLabel());
        return panel;
    }

    private JLabel inviteTitleLabel() {
        JLabel label = new JLabel("대화상대 선택");
        label.setFont(new Font("Serif", Font.BOLD, 14));
        label.setForeground(new Color(0xEFEBEB));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private JPanel inviteButtonPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(exitButton());
        panel.add(inviteButton());
        return panel;
    }

    private JButton inviteButton() {
        JButton button = new JButton("확인");
        button.addActionListener(event -> {
            List<Invitation> invitationList = invitations.stream()
                    .filter(Invitation::checked)
                    .toList();

            if (invitations.size() > 1) {
                try {
                    ChattingRoom newChattingRoom = makaoTalk.newChatting(invitationList,type);

                    ChattingRoomLoader chattingRoomLoader = new ChattingRoomLoader();

                    chattingRoomLoader.saveUserChattingRoomRelations(makaoTalk.relation().userChattingRoomRelations());
                    chattingRoomLoader.saveChattingRooms(makaoTalk.chattingRooms());

                    makaoTalk.openChattingRoom(newChattingRoom.id());
                    JFrame chattingRoomWindow = new ChattingRoomWindow(makaoTalk, newChattingRoom);
                    chattingRoomWindow.setVisible(true);

                    dispose();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return button;
    }

    private JButton exitButton() {
        JButton button = new JButton("취소");
        button.addActionListener(event -> {
            dispose();
        });
        return button;
    }
}
