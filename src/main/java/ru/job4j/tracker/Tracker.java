package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

    public Item[] findAll() {
        Item[] itemsNotNull = new Item[size];
        for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                itemsNotNull[i] = items[i];
            }
        }
        return itemsNotNull;
    }

    public Item[] findByName(String key) {
        Item[] foundItems = new Item[items.length];
        int newSize = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && key.equals(items[i].getName())) {
                foundItems[i] = items[i];
                newSize++;
            }
        }
        foundItems = Arrays.copyOf(foundItems, newSize);
        return foundItems;
    }
}