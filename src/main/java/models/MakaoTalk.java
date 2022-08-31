package models;

import models.ChattingRoom.ChattingRoom;
import models.Relation.Relation;
import models.Relation.UserChattingRoomRelation;
import utils.IDGenerator;
import utils.loader.ChattingRoomLoader;
import utils.loader.MessageLoader;
import utils.loader.UserLoader;
import utils.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MakaoTalk {
    private List<User> users = new ArrayList<>();
    private List<ChattingRoom> chattingRooms = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    private Relation relation = new Relation();
    private long loginUserId;

    public List<User> users() {
        return users;
    }

    public List<ChattingRoom> chattingRooms() {
        return chattingRooms;
    }

    public List<Message> messages() {
        return messages;
    }

    public Relation relation() {
        return relation;
    }

    public long loginUserId() {
        return loginUserId;
    }

    public void updateLoginUserId(long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addChattingRoom(ChattingRoom chattingRoom) {
        chattingRooms.add(chattingRoom);
    }

    public void loadUsers() throws FileNotFoundException {
        UserLoader userLoader = new UserLoader();

        users = userLoader.loadUser();
    }

    public User user(long friendId) {
        return users.stream()
                .filter(user -> user.id() == friendId)
                .toList()
                .get(0);
    }

    public void register(String userName, String password, String nickName, String phoneNumber) throws IOException {
        Parser parser = new Parser();

        IDGenerator idGenerator = new IDGenerator();

        long id = idGenerator.newUserId();     // TODO -> 나중에 수정해야 함.

        String line = parser.parseLine(id, userName, password, nickName, phoneNumber);

        User user = parser.parseUser(line);

        addUser(user);
    }

    public void login(long loginUserId) {
        this.loginUserId = loginUserId;

        relation.updateloginUserId(loginUserId);
    }

    public ChattingRoom newChatting(List<Invitation> invitations) throws FileNotFoundException {
        Parser parser = new Parser();

        IDGenerator idGenerator = new IDGenerator();
// TODO -> 새로운 채팅방 생성(새로운 아이디, 새로운 제목, 초대인원들)
//  ChattingRoomWindow에 makaoTalk,chattingRoom 전달 (메서드 밖에서)
        long id = idGenerator.newChattingRoomId();

        List<User> invitedUsers = parser.parseInvitedUsers(invitations, users);

        String title = parser.parseChattingRoomTitle(invitedUsers);

        ChattingRoom newChatting = new ChattingRoom(id,title,invitedUsers);

        addChattingRoom(newChatting);

        relation.newUserChattingRoomRelations(newChatting);

        return newChatting;
    }

    public void loadChattingRooms() throws FileNotFoundException {
        ChattingRoomLoader chattingRoomLoader = new ChattingRoomLoader();

        chattingRooms = chattingRoomLoader.loadChattingRoom(users, relation.userChattingRoomRelations());
    }

    public void loadMessages() throws FileNotFoundException {
        MessageLoader messageLoader = new MessageLoader();

        messages = messageLoader.loadMessages();
    }

    public void addMessage(Message newMessage) {
        messages.add(newMessage);
    }

    public String messageOwnerName(Message message) {
        User messageOwner = users.stream()
                .filter(user -> user.id() == message.userId())
                .toList()
                .get(0);

        return messageOwner.name();
    }

    public List<ChattingRoom> relativeChattingRooms() {
        List<UserChattingRoomRelation> loginUserChattingRooms = relation.loginUserChattingRoomRelations();

        List<ChattingRoom> relativeChattingRooms = chattingRooms.stream()
                .filter(relativeChattingRoom -> loginUserChattingRooms.stream()
                        .anyMatch(loginUserChattingRoom ->
                                loginUserChattingRoom.chattingRoomId() == relativeChattingRoom.id()))
                .toList();

        return relativeChattingRooms;
    }

    public void newMessage(Message newMessage, ChattingRoom chattingRoom) {
        addMessage(newMessage);

        relation.newChattingRoomMessageRelation(chattingRoom, newMessage);
    }

    public List<Message> currentChattingRoomMessages() {
        return messages.stream()
                .filter(message -> relation.currentChattingRoomMessageRelations().stream()
                        .anyMatch(chattingRoomMessageRelation
                                -> chattingRoomMessageRelation.messageId() == message.id()))
                .toList();
    }

    public void openChattingRoom(long currentChattingRoomId) {
        relation.updateCurrentChattingRoom(currentChattingRoomId);
    }
}
