package application;

import frames.MainFrame;
import models.ChattingRoom.ChattingRoom;
import models.ChattingRoom.SingleChatting;
import models.MakaoTalk;
import models.User;
import utils.MouseEventListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.FileNotFoundException;

public class Main {
    private MakaoTalk makaoTalk;

    public static void main(String[] args) throws FileNotFoundException {
        Main application = new Main();
        application.run();
    }

    private void run() throws FileNotFoundException {
        inputUserInformation();

        JFrame mainFrame = new MainFrame(makaoTalk);

        mainFrame.setVisible(true);
    }

    private void inputUserInformation() throws FileNotFoundException {
        makaoTalk = new MakaoTalk();
//        makaoTalk.loadStatus();

        makaoTalk.loadUsers();


//        List<Message> messages = new ArrayList<>();
//        ChattingRoom chattingRoom = new ChattingRoom(
//                makaoTalk.users(),
//                makaoTalk.currentUser(),
//                messages
//        );
//        makaoTalk.addChattingRoom(chattingRoom);

        System.out.println(makaoTalk.users());
//        System.out.println(makaoTalk.friends());
//        System.out.println(makaoTalk.chattingRooms());
    }


    // TODO : 친구 창


}
