package com.entity;

import java.sql.SQLException;
import java.util.Scanner;

public class Program2 {
    private DB db = DB.getInstance();
    public Program2() {
    }

    public void chooseOption() throws SQLException {
        Scanner scann = new Scanner(System.in);
        System.out.println("Wybierz jedną z opcji: \n\n" +
                "add - Dodanie zadania \n" +
                "edit - Edycja zadania \n" +
                "delete - Usuniecie zadania \n" +
                "quit - Wyjscie z programu");
        String chooseOption = scann.next();
        if (chooseOption.equals("add")) {
            System.out.println("Podaj tytuł zadania: ");
            String setTtile = scann.next();
            System.out.println("Podaj opis zadania: ");
            String setDescription = scann.next();
            Exercise exercise = new Exercise(setTtile, setDescription, 0);
            exercise.saveOrUpdateToDb(db.getConnection());

        } else if (chooseOption.equals("edit")) {
            System.out.println("Podaj id zadania do zmiany: ");
            int setId = scann.nextInt();
            System.out.println("Wprowadz nową nazwę zadania: ");
            String setTitle = scann.next();
            System.out.println("Podaj nowy opis zadania: ");
            String setDescription = scann.next();
            Exercise exercise = new Exercise(setTitle, setDescription, setId);
            exercise.saveOrUpdateToDb(db.getConnection());

        } else if (chooseOption.equals("delete")) {
            System.out.println("Podaj id zadania do usunięcia: ");
            int setId = scann.nextInt();
            Exercise exercise = new Exercise().setId(setId);
            exercise.delete(db.getConnection());

        } else if (chooseOption.equals("quit")) {
            System.out.println("Wyszedłeś z programu!");

        }

    }
}
