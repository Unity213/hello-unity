package com.dh.bootio.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getMonthValue());

        System.out.println(now.format(DateTimeFormatter.ISO_TIME));
        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }
}
