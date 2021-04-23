package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StartUI {

    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            int select = Integer.valueOf(scanner.nextLine());
            if (select == 0) {
                createItem(scanner, tracker);
            } else if (select == 1) {
                showAllItems(tracker);
            } else if (select == 2) {
                editItem(scanner, tracker);
            } else if (select == 3) {
                deleteItem(scanner, tracker);
            } else if (select == 4) {
                findItem(scanner, tracker);
            } else if (select == 5) {
                findItemsByName(scanner, tracker);
            } else if (select == 6) {
                run = false;
            }
        }
    }

    public void createItem(Scanner scanner, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        Item item = new Item(name);
        tracker.add(item);
    }

    public void showAllItems(Tracker tracker) {
        Item[] items = tracker.findAll();
        System.out.println("=== All items ====");
        for (Item item : items) {
            System.out.println(item.toString());
        }
    }

    public void editItem(Scanner scanner, Tracker tracker) {
        System.out.println("=== Edit exist item ====");
        System.out.print("Enter id: ");
        int id = Integer.valueOf(scanner.nextLine());

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        Item item = new Item(name);
        tracker.add(item);

        if (tracker.replace(id, item)) {
            System.out.println("Success. Item changed");
        } else {
            System.out.println("Error. Invalid id " + id);
        }
    }

    public void deleteItem(Scanner scanner, Tracker tracker) {
        System.out.println("=== Delete exist item ====");
        System.out.print("Enter id: ");
        int id = Integer.valueOf(scanner.nextLine());

        if (tracker.delete(id)) {
            System.out.println("Success. Item deleted");
        } else {
            System.out.println("Error. Invalid id " + id);
        }
    }

    public void findItem(Scanner scanner, Tracker tracker) {
        System.out.print("Enter id: ");
        int id = Integer.valueOf(scanner.nextLine());

        Item item = tracker.findById(id);

        if (item != null) {
            System.out.println(item.toString());
        } else {
            System.out.println("Item with id " + id + " not found");
        }
    }

    public void findItemsByName(Scanner scanner, Tracker tracker) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        Item[] items = tracker.findByName(name);

        if (items.length > 0) {
            for (Item item : items) {
                if (item != null) {
                    System.out.println(item.toString());
                }
            }
        } else {
            System.out.println("Items with name " + name + " not found");
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}
