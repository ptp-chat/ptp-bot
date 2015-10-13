package me.imsean.ptpbot;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO: Catch any exceptions caused here.
        final Scanner in = new Scanner(System.in);
        System.out.println("Enter username and password (username:password):");

        String[] credentials = in.nextLine().split(":");
        String username = credentials[0];
        String password = credentials[1];

        PTPBot bot = new PTPBot();
        bot.login(username, password);
        bot.start();
    }

}
