package com;

import com.entity.*;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    private static DB db = DB.getInstance();
    public static void main(String[] args) throws SQLException {

        Scanner scann = new Scanner(System.in);
//        System.out.println("Get id to edit: ");
//        int getId = scann.nextInt();
//        System.out.println("Get title: ");
//        String getTitle = scann.next();
//        System.out.println("Get description: ");
//        String getDescrition = scann.next();

//            Exercise exercise = new Exercise(getTitle, getDescrition, getId);
//            exercise.saveOrUpdateToDb(db.getConnection());
        User[] users = User.loadAllUsers(db.getConnection());
        for(User u : users) {
            System.out.println(u);
        }

        Exercise[] exercises = Exercise.loadAllExercise(db.getConnection());
        for(Exercise e : exercises) {
            System.out.println(e);
        }



            //Widok główny ekranu - Wybór programu
            System.out.println("Wybierz program, podaj numer programu: \n\n" +
                    "[Program 1] - Zarządzanie użytkownikami \n" +
                    "[Program 2] - Zarządzanie zadaniami \n" +
                    "[Program 3] - Zarządzanie grupami \n" +
                    "[Program 4] - Przypisywanie zadan");
            int choose = scann.nextInt();
            if(choose == 1) {
                Program1 p1 = new Program1();
                p1.chooseOption();
            }else if(choose == 2) {
                Program2 p2 = new Program2();
                p2.chooseOption();
            }else if(choose == 3) {
                Program3 p3 = new Program3();
                p3.chooseOption();
            }else if(choose == 4) {
                Program4 p4 = new Program4();
                p4.chooseOption();
            }else {

                    System.out.println("You choose wrong number! Try again: ");
                    scann.next();

            }


    }
}
