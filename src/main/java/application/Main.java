package application;

import frames.MainFrame;
import models.MakaoTalk;
import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    private MakaoTalk makaoTalk;

    public static void main(String[] args) throws IOException {
        Main application = new Main();
        application.run();
    }

    private void run() throws IOException {
        inputUserInformation();

        JFrame mainFrame = new MainFrame(makaoTalk);

        mainFrame.setVisible(true);
    }

    private void inputUserInformation() throws IOException {
        makaoTalk = new MakaoTalk();

        makaoTalk.loadUsers();
        System.out.println(makaoTalk.users());

        makaoTalk.relation().loadUserRelations();
        System.out.println(makaoTalk.relation().usersRelations());

        makaoTalk.relation().loadUserChattingRoomRelation();
        System.out.println(makaoTalk.relation().userChattingRoomRelations());

        makaoTalk.relation().loadChattingRoomMessageRelation();
        System.out.println(makaoTalk.relation().chattingRoomMessageRelations());

        makaoTalk.loadChattingRooms();
        System.out.println(makaoTalk.chattingRooms());

        makaoTalk.loadMessages();

//        System.out.println(makaoTalk.friends());
//        System.out.println(makaoTalk.chattingRooms());
    }

    // TODO : 친구 창
}
