package com.entity;

import java.sql.SQLException;
import java.util.Scanner;

public class Program4 {
    private DB db = DB.getInstance();

    public Program4() {
    }
    public void chooseOption() throws SQLException {
        Scanner scann = new Scanner(System.in);
        System.out.println("Wybierz jedną z opcji: \n\n" +
                "add - Dodanie zadania \n" +
                "view - Edycja zadania \n" +
                "quit - Wyjscie z programu");
        String chooseOption = scann.next();
        if (chooseOption.equals("add")) {
            User[] users = User.loadAllUsers(db.getConnection());
            for(User u : users) {
                System.out.println("ID: " + u.getId() + "; Username: " + u.getUsername());
            }
            System.out.println("Podaj ID użytkownika: ");
            int getIdUser = scann.nextInt();
            System.out.println("Lista zadań: ");
            Exercise[] exercises = Exercise.loadAllExercise(db.getConnection());
            for(Exercise e : exercises) {
                System.out.println("ID: " + e.getId() + "; Title: " + e.getTitle() + "; Description: " + e.getDescription());
            }
            System.out.println("Podaj ID zadania: ");
            int getIdExercise = scann.nextInt();

            Solution solution = new Solution();



        } else if (chooseOption.equals("view")) {
            System.out.println("Podaj id zadania do zmiany: ");
            int setId = scann.nextInt();
            System.out.println("Wprowadz nową nazwę zadania: ");
            String setTitle = scann.next();
            System.out.println("Podaj nowy opis zadania: ");
            String setDescription = scann.next();
            Exercise exercise = new Exercise(setTitle, setDescription, setId);
            exercise.saveOrUpdateToDb(db.getConnection());

          } else if (chooseOption.equals("quit")) {
            System.out.println("Wyszedłeś z programu!");

        }

    }

    }

