package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("Head First", 300);
        books[1] = new Book("Spring in action", 700);
        books[2] = new Book("Clean Code", 257);
        books[3] = new Book("Thinking in Java", 900);

        for (int i = 0; i < books.length; i++) {
            System.out.println("Title " + books[i].getTitle() + " pages " + books[i].getCount());
        }

        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;

        System.out.println("Replace books");

        for (int i = 0; i < books.length; i++) {
            System.out.println("Title " + books[i].getTitle() + " pages " + books[i].getCount());
        }

        System.out.println("Show only book with title Clean Code");

        for (int i = 0; i < books.length; i++) {
            if ("Clean Code".equals(books[i].getTitle())) {
                System.out.println("Title " + books[i].getTitle()
                        + " pages " + books[i].getCount());
            }
        }

    }
}
