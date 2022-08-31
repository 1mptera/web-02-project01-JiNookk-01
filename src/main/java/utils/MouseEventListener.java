//package utils;
//
//import frames.ChattingRoomWindow;
//import models.ChattingRoom.ChattingRoom;
//import models.User;
//
//import javax.swing.JFrame;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//public class MouseEventListener {
//    public MouseListener openChattingRoomWindow(ChattingRoom chattingRoom) {
//        return new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//
//                JFrame chattingRoomWindow = new ChattingRoomWindow(makaoTalk, chattingRoom);
//
//                chattingRoomWindow.setVisible(true);
//            }
//        };
//    }
//
//    public MouseListener openFriendProfileWindow(User friend) {
//        return new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                JFrame friendProfileWindow = new JFrame(friend.name());
//                friendProfileWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                friendProfileWindow.setSize(400, 300);
//
//                friendProfileWindow.setVisible(true);
//            }
//        };
//    }
//}
