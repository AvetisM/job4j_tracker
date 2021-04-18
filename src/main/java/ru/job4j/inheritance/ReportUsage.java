package ru.job4j.inheritance;

public class ReportUsage {
    public static void main(String[] args) {
        JsonReport jsonReport = new JsonReport();
        String text = jsonReport.generate("name : name", "body : body");
        System.out.println(text);
    }
}
