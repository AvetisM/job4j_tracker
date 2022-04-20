package ru.job4j.tracker;

public class CreateActionMultipleCreation implements UserAction {

    private final Output out;

    public CreateActionMultipleCreation(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new Items";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Create new Items ====");
        int number = input.askInt("Enter number of items: ");
        for (int i = 0; i <= number; i++) {
            memTracker.add(new Item("Name" + i));
        }
        out.println("Добавлены заявки: ");
        return true;
    }
}
