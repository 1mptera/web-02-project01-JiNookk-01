package utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {
    @Test
    void currentTime() {
        Timer timer = new Timer();

        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.format(DateTimeFormatter.ofPattern("yy.MM.dd.HH.mm.ss"));

        assertEquals(currentTime, timer.currentTime());
    }
}