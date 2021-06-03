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
        String answerSting;
        switch (answer) {
            case 0:
                answerSting = "Да";
                break;
            case 1:
                answerSting = "Нет";
                break;
            default:
                answerSting = "Может быть";
        }
        return answerSting;
    }
}
