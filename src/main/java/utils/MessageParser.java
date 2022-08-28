package utils;

import models.Message;

public class MessageParser {

    public Message parseMessage(String content, String time, String id) {
        return new Message(content, time, id);
    }
}
