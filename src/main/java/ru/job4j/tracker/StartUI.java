package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StartUI {
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("First");
        tracker.add(firstItem);

        Item secondItem = new Item("Second");
        tracker.add(secondItem);

        Item foundItem = tracker.findById(secondItem.getId());
        if (foundItem != null) {
            System.out.println("Id " + foundItem.getId() + " name " + foundItem.getName());
        }
    }
}
