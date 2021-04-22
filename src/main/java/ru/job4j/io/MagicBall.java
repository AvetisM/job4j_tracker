package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class MagicBall {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Я великий Оракул. Что ты хочешь узнать?");
        scanner.nextLine();
        System.out.println(getAnswer(new Random().nextInt(3)));
    }

    public static String getAnswer(int answer) {
        return switch (answer) {
            case 0 -> "Да";
            case 1 -> "Нет";
            default -> "Может быть";
        };
    }
}
