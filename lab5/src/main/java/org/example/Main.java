package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        DataBase db;

        try {
            db = new DataBase("test1.db");
            System.out.println("Connection established successfully!");
        } catch (RuntimeException e) {
            System.out.println("Something went wrong!");
            System.out.println(e.getMessage());
        }

        try {
            db = new DataBase("");
            System.out.println("Connection established successfully!");
        } catch (RuntimeException e) {
            System.out.println("Something went wrong!");
            System.out.println(e.getMessage());
        }
    }
}