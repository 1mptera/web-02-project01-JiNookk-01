package models;

import models.ChattingRoom.ChattingRoom;
import models.ChattingRoom.SecretChatting;
import models.Relation.Relation;
import models.Relation.UserChattingRoomRelation;
import utils.IDGenerator;
import utils.loader.ProfileLoader;
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
    private List<Profile> profiles = new ArrayList<>();
    private List<ChattingRoom> chattingRooms = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    private final Relation relation = new Relation();
    private long loginUserId;

    public List<User> users() {
        return new ArrayList<>(users);
    }

    public List<User> undeletedUsers() {
        return users.stream()
                .filter(user -> !user.deleted())
                .toList();
    }

    public User user(long friendId) {
        return users.stream()
                .filter(user -> user.id() == friendId)
                .toList()
                .get(0);
    }

    public List<Profile> profiles() {
        return new ArrayList<>(profiles);
    }

    public List<Profile> undeletedProfiles() {
        return profiles.stream()
                .filter(profile -> !profile.deleted())
                .toList();
    }

    public List<ChattingRoom> chattingRooms() {
        return new ArrayList<>(chattingRooms);
    }

    public List<Message> messages() {
        return new ArrayList<>(messages);
    }

    public Relation relation() {
        return relation;
    }

    public long loginUserId() {
        return loginUserId;
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

        return new ArrayList<>(relativeChattingRooms);
    }

    public List<Message> currentChattingRoomMessages() {
        List<Message> currentChattingRoomMessages = messages.stream()
                .filter(message -> relation.currentChattingRoomMessageRelations().stream()
                        .anyMatch(chattingRoomMessageRelation
                                -> chattingRoomMessageRelation.messageId() == message.id()))
                .toList();
        return new ArrayList<>(currentChattingRoomMessages);
    }

    public void updateLoginUserId(long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public void loadUsers() throws IOException {
        UserLoader userLoader = new UserLoader();

        users = userLoader.loadUsers();
    }

    public void loadProfiles() throws IOException {
        ProfileLoader profileLoader = new ProfileLoader();

        profiles = profileLoader.loadProfiles();

        for (int i = 0, profilesSize = profiles.size(); i < profilesSize; i += 1) {
            users.get(i).loadProfile(profiles.get(i));
        }
    }

    public void loadChattingRooms() throws FileNotFoundException {
        ChattingRoomLoader chattingRoomLoader = new ChattingRoomLoader();

        chattingRooms = chattingRoomLoader.loadChattingRoom(users, relation.userChattingRoomRelations());
    }

    public void loadMessages() throws FileNotFoundException {
        MessageLoader messageLoader = new MessageLoader();

        messages = messageLoader.loadMessages();
    }

    public void addUser(User user) {
        users.add(user);
    }

    private void addProfile(Profile defaultProfile) {
        profiles.add(defaultProfile);
    }

    public void addChattingRoom(ChattingRoom chattingRoom) {
        chattingRooms.add(chattingRoom);
    }

    public void addMessage(Message newMessage) {
        messages.add(newMessage);
    }

    public ChattingRoom newChatting(List<Invitation> invitations,String type) throws FileNotFoundException {
        Parser parser = new Parser();

        IDGenerator idGenerator = new IDGenerator();

        long id = idGenerator.newChattingRoomId();

        List<User> invitedUsers = parser.parseInvitedUsers(invitations, users);

        String title = parser.parseChattingRoomTitle(invitedUsers);

        if (type.equals("비밀")) {
            ChattingRoom newChatting = new SecretChatting(id,title,invitedUsers);

            addChattingRoom(newChatting);

            relation.newUserChattingRoomRelations(newChatting);

            return newChatting;
        }

        ChattingRoom newChatting = new ChattingRoom(id,title,invitedUsers);

        addChattingRoom(newChatting);

        relation.newUserChattingRoomRelations(newChatting);

        return newChatting;
    }

    public void newMessage(Message newMessage, ChattingRoom chattingRoom) {
        addMessage(newMessage);

        chattingRoom.updatePreviewMessage(newMessage.content());

        relation.newChattingRoomMessageRelation(chattingRoom, newMessage);
    }

    public void login(long loginUserId) {
        this.loginUserId = loginUserId;

        relation.updateloginUserId(loginUserId);
    }

    public void register(String userName, String password, String nickName, String phoneNumber) throws IOException {
        Parser parser = new Parser();

        IDGenerator idGenerator = new IDGenerator();

        long id = idGenerator.newUserId();

        boolean deleted = false;

        String line = parser.parseLine(id, userName, password, nickName, phoneNumber, deleted);

        Profile defaultProfile = new Profile(id, deleted);

        User user = parser.parseUser(line,defaultProfile);

        addProfile(defaultProfile);

        addUser(user);
    }

    public void openChattingRoom(long currentChattingRoomId) {
        relation.updateCurrentChattingRoom(currentChattingRoomId);
    }
}
