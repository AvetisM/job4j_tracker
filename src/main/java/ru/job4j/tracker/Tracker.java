package ru.job4j.tracker;

import java.util.Arrays;

import static java.lang.System.arraycopy;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            item.setId(id);
            items[index] = item;
        }
        return rsl;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            arraycopy(items, index + 1, items, index, size - index);
            items[size - 1] = null;
            size--;
        }
        return rsl;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
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
        Item[] foundItems = new Item[size];
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (items[i] != null && key.equals(items[i].getName())) {
                foundItems[i] = items[i];
                newSize++;
            }
        }
        foundItems = Arrays.copyOf(foundItems, newSize);
        return foundItems;
    }
}