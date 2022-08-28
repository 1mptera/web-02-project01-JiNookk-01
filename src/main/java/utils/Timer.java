package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timer {

    public String currentTime() {
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.format(
                DateTimeFormatter.ofPattern("yy.MM.dd.HH.mm.ss"));
        return currentTime;
    }
}
