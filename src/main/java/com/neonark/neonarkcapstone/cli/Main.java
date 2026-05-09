package com.neonark.neonarkcapstone.cli;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            printMenu();

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    listAllCreatures();
                    break;

                case "2":
                    viewCreatureById(scanner);
                    break;

                case "3":
                    System.out.println("Register new creature coming soon.");
                    break;

                case "4":
                    System.out.println("Rename creature coming soon.");
                    break;

                case "5":
                    System.out.println("Remove creature coming soon.");
                    break;

                case "6":
                    System.out.println("View creature observations/notes coming soon.");
                    break;

                case "7":
                    System.out.println("Find creatures by feeding time coming soon.");
                    break;

                case "8":
                    System.out.println("View all system users coming soon.");
                    break;

                case "0":
                    System.out.print("Are you sure you want to exit? (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        System.out.println("Exiting program...");
                        running = false;
                    } else {
                        System.out.println("Exit cancelled.");
                    }
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== Neon Ark Intake Tracker ===");
        System.out.println("1. List all creatures");
        System.out.println("2. View creature by ID");
        System.out.println("3. Register new creature");
        System.out.println("4. Rename creature");
        System.out.println("5. Remove creature");
        System.out.println("6. View creature observations/notes");
        System.out.println("7. Find creatures by feeding time");
        System.out.println();
        System.out.println("Admin Only");
        System.out.println("8. View all system users");
        System.out.println();
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void listAllCreatures() {
        sendGetRequest("http://localhost:8080/api/creatures", "=== Creatures ===");
    }

    private static void viewCreatureById(Scanner scanner) {
        System.out.print("Enter creature ID: ");
        String id = scanner.nextLine();

        sendGetRequest("http://localhost:8080/api/creatures/" + id, "=== Creature Details ===");
    }

    private static void sendGetRequest(String urlText, String heading) {

        try {

            URL url = new URL(urlText);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();

            if (statusCode == 404) {
                System.out.println("Not found.");
                connection.disconnect();
                return;
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            String line;

            System.out.println();
            System.out.println(heading);

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
            connection.disconnect();

        } catch (Exception e) {
            System.out.println("Error connecting to API.");
        }
    }
}