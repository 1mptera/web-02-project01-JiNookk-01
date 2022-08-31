package frames;

import models.MakaoTalk;
import models.User;
import utils.OverlapValidator;
import utils.loader.UserLoader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

public class FriendAddFrame extends JFrame {
    private JTextField inputNameField;
    private JTextField inputPhoneNumberField;
    private JPanel addFriendPanel;
    private JPanel inputFriendsInformationPanel;
    private JTextField inputIdField;

    private String mode = "PhoneNumber";
    private MakaoTalk makaoTalk;
    private JPanel contentPanel;

    public FriendAddFrame(MakaoTalk makaoTalk, JPanel contentPanel) {
        this.makaoTalk = makaoTalk;
        this.contentPanel = contentPanel;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 420);
        this.setResizable(false);
        this.setBackground(Color.darkGray);
        this.setLocationRelativeTo(null);
        this.add(addFriendPanel());
    }

    private JPanel addFriendPanel() {
        addFriendPanel = new JPanel();
        addFriendPanel.setLayout(new BorderLayout());
        addFriendPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        addFriendPanel.add(addFriendMenuPanel(), BorderLayout.NORTH);
        addFriendPanel.add(inputFriendsInformationPanel());
        addFriendPanel.add(addFriendButton(), BorderLayout.SOUTH);
        return addFriendPanel;
    }

    private JPanel addFriendMenuPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(0, 70));
        panel.setLayout(new GridLayout(2, 1));
        panel.add(addFriendTitleLabel());
        panel.add(friendRequestPanel());
        return panel;
    }

    private JLabel addFriendTitleLabel() {
        JLabel label = new JLabel("친구 추가");
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private JPanel friendRequestPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(addFriendByPhoneNumberButton());
        panel.add(addFriendByIdButton());
        return panel;
    }

    private JButton addFriendByPhoneNumberButton() {
        JButton button = new JButton("연락처");
        button.addActionListener(event -> {
            mode = "PhoneNumber";

            inputFriendsInformationPanel.removeAll();
            inputFriendsInformationPanel.add(inputInformationPanel(), BorderLayout.NORTH);
            inputFriendsInformationPanel.add(descriptionPanel("친구의 이름과 전화번호를 입력해주세요."));
            updateDisplay();
        });
        return button;
    }

    private JButton addFriendByIdButton() {
        JButton button = new JButton("ID");
        button.addActionListener(event -> {
            mode = "ID";

            inputFriendsInformationPanel.removeAll();
            inputFriendsInformationPanel.add(inputIdPanel(), BorderLayout.NORTH);
            inputFriendsInformationPanel.add(descriptionPanel("친구의 ID를 입력해주세요"));

            updateDisplay();
        });
        return button;
    }

    private void updateDisplay() {
        addFriendPanel.setVisible(false);
        addFriendPanel.setVisible(true);
        this.setVisible(true);
    }

    private JPanel inputIdPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 80));
        panel.add(inputIdField());
        return panel;
    }

    private JTextField inputIdField() {
        inputIdField = new JTextField(10);
        inputIdField.setText("친구 카카오톡 ID");
        return inputIdField;
    }

    private JPanel inputFriendsInformationPanel() {
        inputFriendsInformationPanel = new JPanel();
        inputFriendsInformationPanel.setOpaque(false);
        inputFriendsInformationPanel.setLayout(new BorderLayout());
        inputFriendsInformationPanel.add(inputInformationPanel(), BorderLayout.NORTH);
        inputFriendsInformationPanel.add(descriptionPanel("친구의 이름과 전화번호를 입력해주세요."));
        return inputFriendsInformationPanel;
    }

    private JPanel descriptionPanel(String description) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(description));
        return panel;
    }

    private JPanel inputInformationPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 80));
        panel.setLayout(new GridLayout(2, 1));
        panel.add(inputNameField());
        panel.add(inputPhoneNumberField());
        return panel;
    }

    private JTextField inputNameField() {
        inputNameField = new JTextField(10);
        inputNameField.setText("친구 이름");
        return inputNameField;
    }

    private JTextField inputPhoneNumberField() {
        inputPhoneNumberField = new JTextField(10);
        inputPhoneNumberField.setText("전화 번호");
        return inputPhoneNumberField;
    }

    private JButton addFriendButton() {
        JButton button = new JButton("친구 추가");
        button.setPreferredSize(new Dimension(0, 50));
        button.addActionListener(event -> {
            if (mode.equals("ID")) {
                String userName = inputIdField.getText();

                for (User user : makaoTalk.users()) {
                    if (userName.equals(user.userName())) {
                        // TODO : 관계 추가, 현재 유저의 상태도 아직 설정하지 않음.
                        addFriend(user);
                    }
                }
            }

            if (mode.equals("PhoneNumber")) {
                String name = inputNameField.getText();
                String phoneNumber = inputPhoneNumberField.getText();

                for (User user : makaoTalk.users()) {
                    if (name.equals(user.name()) && phoneNumber.equals(user.phoneNumber())) {
                        addFriend(user);
                    }
                }
            }
        });
        return button;
    }

    private void addFriend(User friend) {
        OverlapValidator overlapVaildator = new OverlapValidator();
        boolean friendAlreadyExist = overlapVaildator.
                validateUserRelations(friend, makaoTalk);

        if (!friendAlreadyExist) {
            makaoTalk.relation().requestFriend(friend.id());

            UserLoader userLoader = new UserLoader();

            try {
                userLoader.saveUsersRelations(makaoTalk.relation().usersRelations());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JFrame alertFrame = new AlertFrame(friend.name() + "님을 추가하였습니다.");
            alertFrame.setVisible(true);
            dispose();

            contentPanel.setVisible(false);
            contentPanel.setVisible(true);
        }

        if (friendAlreadyExist) {
            JFrame alertFrame = new AlertFrame(friend.name() + "님은 이미 친구입니다.");
            alertFrame.setVisible(true);
        }
    }
}
