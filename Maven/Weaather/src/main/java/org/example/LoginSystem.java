package org.example;

import java.util.Scanner;

public class LoginSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDatabase userDatabase = new UserDatabase();
    public static boolean login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDatabase.getUserByUsername(username);

        if (user == null) {
            System.out.println("Invalid username. Please try again.");
        } else if (!user.getPassword().equals(password)) {
            System.out.println("Invalid password. Please try again.");
        } else {
//            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            return true;
            // You can start the Weather App here
        }
        return false;
    }
    static boolean signUp() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        User newUser = new User(name, username, password);

        if (userDatabase.addUser(newUser)) {
//            System.out.println("Sign up successful. Welcome, " + newUser.getName() + "!");
            // You can start the Weather App here
            return true;
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
        return false;
    }
}

class User {
    private String name;
    private String username;
    private String password;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class UserDatabase {
    private User[] users;
    private int size;

    public UserDatabase() {
        users = new User[100];
        size = 0;
    }

    public boolean addUser(User user) {
        if (getUserByUsername(user.getUsername()) != null) {
            return false;
        }

        users[size++] = user;
        return true;
    }

    public User getUserByUsername(String username) {
        for (int i = 0; i < size; i++) {
            if (users[i].getUsername().equals(username)) {
                return users[i];
            }
        }

        return null;
    }
}
