package ru.job4j.tracker;

import java.util.List;

public class DeleteItemMultipleDeletion implements UserAction {
    private final Output out;

    public DeleteItemMultipleDeletion(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete all items";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Delete all items ===");
        List<Item> items = memTracker.findAll();
        if (items.size() > 0) {
            for (Item item : items) {
                memTracker.delete(item.getId());
            }
        } else {
            out.println("Хранилище еще не содержит заявок");
        }
        System.gc();
        return true;
    }
}
