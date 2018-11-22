package com.entity;


import java.sql.SQLException;
import java.util.Scanner;

public class Program1 {

    private DB db = DB.getInstance();

    public Program1() {

    }

    public void chooseOption() throws SQLException {
        Scanner scann = new Scanner(System.in);
        System.out.println("Wybierz jedną z opcji: \n\n" +
                "add - Dodanie użytkownika \n" +
                "edit - Edycja użytkownika \n" +
                "delete - Usuniecie uzytkownika \n" +
                "quit - Wyjscie z programu");
        String chooseOption = scann.next();
        if (chooseOption.equals("add")) {
            System.out.println("Podaj nazwę użytkownika: ");
            String setUsername = scann.next();
            System.out.println("Podaj email: ");
            String setEmail = scann.next();
            System.out.println("Podaj hasło: ");
            String setPassword = scann.next();
            User user = new User(setUsername, setEmail, setPassword, 0);
            user.saveOrUpdateToDb(db.getConnection());

        } else if (chooseOption.equals("edit")) {
            System.out.println("Podaj id do zmiany: ");
            int setId = scann.nextInt();
            System.out.println("Wprowadz nową nazwę uzytkownika: ");
            String setUsername = scann.next();
            System.out.println("Podaj nowy email: ");
            String setEmail = scann.next();
            System.out.println("Podaj nowe hasło: ");
            String setPassword = scann.next();
            User user = new User(setUsername, setEmail, setPassword, setId);
            user.saveOrUpdateToDb(db.getConnection());

        } else if (chooseOption.equals("delete")) {
            System.out.println("Podaj id do usunięcia: ");
            int setId = scann.nextInt();
            User user = new User().setId(setId);
            user.delete(db.getConnection());

        } else if (chooseOption.equals("quit")) {
            System.out.println("Wyszedłeś z programu!");

        }

    }
}
