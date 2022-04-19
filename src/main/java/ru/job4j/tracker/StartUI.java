package ru.job4j.tracker;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store memTracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = (input.askInt("Select: "));
            if (select < 0 || select >= actions.length) {
                out.println("Wrong input, you can select: 0 .. " + (actions.length - 1));
                continue;
            }
            UserAction action = actions[select];
            run = action.execute(input, memTracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        out.println("Menu:");
        for (int index = 0; index < actions.length; index++) {
            out.println(index + ". " + actions[index].name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());

        /*try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            UserAction[] actions = {
                    new CreateAction(output),
                    new ShowAllItems(output),
                    new ReplaceItem(output),
                    new DeleteItem(output),
                    new FindItemById(output),
                    new FindItemByName(output),
                    new ExitProgram()
            };
            new StartUI(output).init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        MemTracker memTracker = new MemTracker();
        for (int i = 0; i < 170; i++) {
            memTracker.add(new Item("Name" + i));
        }
        List<Item> items = memTracker.findAll();
        if (items.size() > 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Хранилище еще не содержит заявок");
        }
        for (int i = 0; i < 50; i++) {
            memTracker.delete(i);
        }
        UserAction[] actions = {
                new CreateAction(output),
                new ShowAllItems(output),
                new ReplaceItem(output),
                new DeleteItem(output),
                new FindItemById(output),
                new FindItemByName(output),
                new ExitProgram()
        };
        new StartUI(output).init(input, memTracker, actions);
    }
}