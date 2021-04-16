package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {

    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Активная: " + active);
        System.out.println("Статус: " + status);
        System.out.println("Ошибка: " + message);
    }

    public static void main(String[] args) {
        Error emptyError = new Error();
        emptyError.printInfo();
        Error activeError = new Error(true, 1, "Ошибка");
        activeError.printInfo();
        Error notActiveError = new Error(false, 0, "Ошибка при записи");
        notActiveError.printInfo();
    }
}
