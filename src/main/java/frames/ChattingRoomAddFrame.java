package frames;

import models.ChattingRoom.ChattingRoom;
import models.Invitation;
import models.MakaoTalk;
import models.Relation.UsersRelation;
import models.User;
import utils.loader.ChattingRoomLoader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChattingRoomAddFrame extends JFrame {
    private final MakaoTalk makaoTalk;
    private List<Invitation> invitations;

    private JTextField searchFriendNameField;

    public ChattingRoomAddFrame(MakaoTalk makaoTalk) {
        invitations = initInvitations(makaoTalk);

        this.makaoTalk = makaoTalk;
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

    private JPanel inviteContainer() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setLayout(new BorderLayout());

        panel.add(searchPanel(), BorderLayout.NORTH);
        panel.add(friendListPanel());
        panel.add(inviteButtonPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel friendListPanel() {
        JPanel panel = new JPanel();
        panel.add(friendListContainer());
        return panel;
    }

    private JPanel friendListContainer() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(makaoTalk.relation().loginUserFriends().size(), 1));

        for (UsersRelation usersRelation : makaoTalk.relation().loginUserFriends()) {
            User friend = makaoTalk.user(usersRelation.friendId());

            Invitation invitation = new Invitation(friend.id());

            invitations.add(invitation);

            panel.add(friendPanel(friend, invitation));
        }

        return panel;
    }

    private JPanel friendPanel(User friend, Invitation invitation) {
        JPanel panel = new JPanel();
        panel.add(friendCheckBox(friend, invitation));
        panel.add(new JLabel("프사"));
        return panel;
    }

    private JCheckBox friendCheckBox(User friend, Invitation invitation) {
        JCheckBox checkBox = new JCheckBox("   " + friend.name());
        checkBox.addActionListener(event -> {
            if (checkBox.isSelected()) {
                invitation.setChecked(true);
            }

            if (!checkBox.isSelected()) {
                invitation.setChecked(false);
            }
            new AlertFrame(Boolean.toString(invitation.checked()));
        });
        return checkBox;
    }

    private JPanel searchPanel() {
        JPanel panel = new JPanel();
        panel.add(inviteTitleLabel());
        panel.add(searchFriendNameField());
        return panel;
    }

    private JLabel inviteTitleLabel() {
        JLabel label = new JLabel("대화상대 선택");
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private JTextField searchFriendNameField() {
        searchFriendNameField = new JTextField(10);
        searchFriendNameField.setText("이름으로 검색");
        return searchFriendNameField;
    }

    private JPanel inviteButtonPanel() {
        JPanel panel = new JPanel();
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
                    ChattingRoom newChattingRoom = makaoTalk.newChatting(invitationList);
//                    new AlertFrame(newChattingRoom.toString());
                    new AlertFrame(makaoTalk.relation().userChattingRoomRelations().toString());

                    ChattingRoomLoader chattingRoomLoader = new ChattingRoomLoader();
                    chattingRoomLoader.saveUserChattingRoomRelations(makaoTalk.relation().userChattingRoomRelations());
                    chattingRoomLoader.saveChattingRooms(makaoTalk.chattingRooms());

                    dispose();

                    JFrame chattingRoomWindow = new ChattingRoomWindow(makaoTalk, newChattingRoom);
                    chattingRoomWindow.setVisible(true);
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
