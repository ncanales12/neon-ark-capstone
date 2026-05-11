package com.neonark.neonarkcapstone.cli;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
                    registerNewCreature(scanner);
                    break;

                case "4":
                    renameCreature(scanner);
                    break;

                case "5":
                    removeCreature(scanner);
                    break;

                case "6":
                    viewCreatureObservations(scanner);
                    break;

                case "7":
                    findCreaturesByFeedingTime(scanner);
                    break;

                case "8":
                    viewAllSystemUsers();
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
        System.out.println("=====================================");
        System.out.println("       NEON ARK CLI SYSTEM");
        System.out.println("=====================================");
        System.out.println();
        System.out.println("1. List all creatures");
        System.out.println("2. View creature by ID");
        System.out.println("3. Register new creature");
        System.out.println("4. Rename creature");
        System.out.println("5. Remove creature");
        System.out.println("6. View creature observations/notes");
        System.out.println("7. Find creatures by feeding time");
        System.out.println();
        System.out.println("--- Admin Only ---");
        System.out.println("8. View all system users");
        System.out.println();
        System.out.println("0. Exit");
        System.out.println("-------------------------------------");
        System.out.print("Select an option: ");
    }

    private static void listAllCreatures() {
        String response = sendGetRequest("http://localhost:8080/api/creatures");

        if (response == null) {
            return;
        }

        printCreatureTable(response, "All Creatures");
    }

    private static void viewCreatureById(Scanner scanner) {
        System.out.print("Enter creature ID: ");
        String id = scanner.nextLine();

        String response = sendGetRequest("http://localhost:8080/api/creatures/" + id);

        if (response == null) {
            return;
        }

        printCreatureTable("[" + response + "]", "Creature Details");
    }

    private static void registerNewCreature(Scanner scanner) {
        System.out.print("Enter creature name: ");
        String name = scanner.nextLine();

        String jsonBody = "{\"name\":\"" + name + "\"}";

        String response = sendPostRequest("http://localhost:8080/api/creatures", jsonBody);

        if (response == null) {
            return;
        }

        printCreatureTable("[" + response + "]", "Creature Registered");
    }

    private static void renameCreature(Scanner scanner) {
        System.out.print("Enter creature ID to rename: ");
        String id = scanner.nextLine();

        System.out.print("Enter new creature name: ");
        String newName = scanner.nextLine();

        System.out.print("Are you sure you want to rename this creature? (Y/N): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Rename cancelled.");
            return;
        }

        String jsonBody = "{\"name\":\"" + newName + "\"}";

        String response = sendPutRequest(
                "http://localhost:8080/api/creatures/" + id + "/name",
                jsonBody
        );

        if (response == null) {
            return;
        }

        printRenameTable(response);
    }

    private static void removeCreature(Scanner scanner) {
        System.out.print("Enter creature ID to remove: ");
        String id = scanner.nextLine();

        System.out.print("Are you sure you want to remove this creature? (Y/N): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Remove cancelled.");
            return;
        }

        String response = sendDeleteRequest("http://localhost:8080/api/creatures/" + id);

        if (response == null) {
            return;
        }

        printCreatureTable("[" + response + "]", "Creature Removed");
    }

    private static void viewCreatureObservations(Scanner scanner) {
        System.out.print("Enter creature ID: ");
        String id = scanner.nextLine();

        String response = sendGetRequest("http://localhost:8080/api/creatures/" + id + "/observations");

        if (response == null) {
            return;
        }

        printObservationTable(response);
    }

    private static void findCreaturesByFeedingTime(Scanner scanner) {
        System.out.print("Enter feeding time (HH:MM): ");
        String time = scanner.nextLine();

        String response = sendGetRequest("http://localhost:8080/api/feedings?time=" + time);

        if (response == null) {
            return;
        }

        printFeedingTable(response, time);
    }

    private static void viewAllSystemUsers() {
        String response = sendGetRequest("http://localhost:8080/api/admin/users");

        if (response == null) {
            return;
        }

        printUserTable(response);
    }

    private static String sendGetRequest(String urlText) {

        try {

            URL url = new URL(urlText);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();

            if (statusCode == 404) {
                System.out.println("Not found.");
                connection.disconnect();
                return null;
            }

            if (statusCode == 400) {
                System.out.println("Bad request. Check your input.");
                connection.disconnect();
                return null;
            }

            String response = readResponse(connection);

            connection.disconnect();

            return response;

        } catch (Exception e) {
            System.out.println("Error connecting to API.");
            return null;
        }
    }

    private static String sendPostRequest(String urlText, String jsonBody) {

        try {

            URL url = new URL(urlText);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonBody.getBytes());
            outputStream.flush();
            outputStream.close();

            int statusCode = connection.getResponseCode();

            if (statusCode == 400) {
                System.out.println("Bad request. Check your input.");
                connection.disconnect();
                return null;
            }

            if (statusCode == 409) {
                System.out.println("Conflict. That creature name already exists.");
                connection.disconnect();
                return null;
            }

            String response = readResponse(connection);

            connection.disconnect();

            return response;

        } catch (Exception e) {
            System.out.println("Error connecting to API.");
            return null;
        }
    }

    private static String sendPutRequest(String urlText, String jsonBody) {

        try {

            URL url = new URL(urlText);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonBody.getBytes());
            outputStream.flush();
            outputStream.close();

            int statusCode = connection.getResponseCode();

            if (statusCode == 400) {
                System.out.println("Bad request. Check your input.");
                connection.disconnect();
                return null;
            }

            if (statusCode == 404) {
                System.out.println("Not found.");
                connection.disconnect();
                return null;
            }

            if (statusCode == 409) {
                System.out.println("Conflict. That creature name already exists.");
                connection.disconnect();
                return null;
            }

            String response = readResponse(connection);

            connection.disconnect();

            return response;

        } catch (Exception e) {
            System.out.println("Error connecting to API.");
            return null;
        }
    }

    private static String sendDeleteRequest(String urlText) {

        try {

            URL url = new URL(urlText);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("DELETE");

            int statusCode = connection.getResponseCode();

            if (statusCode == 404) {
                System.out.println("Not found.");
                connection.disconnect();
                return null;
            }

            if (statusCode != 200) {
                System.out.println("Unexpected response: " + statusCode);
                connection.disconnect();
                return null;
            }

            String response = readResponse(connection);

            connection.disconnect();

            return response;

        } catch (Exception e) {
            System.out.println("Error connecting to API.");
            return null;
        }
    }

    private static String readResponse(HttpURLConnection connection) throws Exception {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        return response.toString();
    }

    private static void printCreatureTable(String json, String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
        System.out.printf("%-6s %-24s %-24s %-12s%n", "ID", "Name", "Habitat", "Status");
        System.out.println("----------------------------------------------------------------------");

        String[] objects = splitJsonObjects(json);

        if (objects.length == 0) {
            System.out.println("No creatures found.");
            return;
        }

        for (String object : objects) {
            String id = getValue(object, "id");
            String name = getValue(object, "name");
            String habitatName = getValue(object, "habitatName");
            String status = getValue(object, "status");

            if (habitatName == null || habitatName.equals("null") || habitatName.isBlank()) {
                habitatName = "None";
            }

            if (status == null || status.equals("null") || status.isBlank()) {
                status = "ACTIVE";
            }

            System.out.printf("%-6s %-24s %-24s %-12s%n", id, name, habitatName, status);
        }
    }

    private static void printRenameTable(String json) {
        System.out.println();
        System.out.println("=== Creature Renamed ===");
        System.out.printf("%-6s %-24s %-24s %-24s %-12s%n", "ID", "Old Name", "New Name", "Habitat", "Status");
        System.out.println("----------------------------------------------------------------------------------------------");

        String id = getValue(json, "id");
        String oldName = getValue(json, "oldName");
        String newName = getValue(json, "newName");
        String habitatName = getValue(json, "habitatName");
        String status = getValue(json, "status");

        if (habitatName == null || habitatName.equals("null") || habitatName.isBlank()) {
            habitatName = "None";
        }

        if (status == null || status.equals("null") || status.isBlank()) {
            status = "ACTIVE";
        }

        System.out.printf("%-6s %-24s %-24s %-24s %-12s%n", id, oldName, newName, habitatName, status);
    }

    private static void printObservationTable(String json) {
        System.out.println();
        System.out.println("=== Creature Observations ===");
        System.out.printf("%-6s %-12s %-24s %-32s %-40s%n", "ID", "Creature ID", "Author", "Created At", "Note");
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        String[] objects = splitJsonObjects(json);

        if (objects.length == 0) {
            System.out.println("No observations found.");
            return;
        }

        for (String object : objects) {
            String id = getValue(object, "id");
            String creatureId = getValue(object, "creatureId");
            String authorName = getValue(object, "authorName");
            String createdAt = getValue(object, "createdAt");
            String note = getValue(object, "note");

            System.out.printf("%-6s %-12s %-24s %-32s %-40s%n", id, creatureId, authorName, createdAt, note);
        }
    }

    private static void printFeedingTable(String json, String time) {
        System.out.println();
        System.out.println("=== Feedings At " + time + " ===");
        System.out.printf("%-6s %-12s %-24s %-12s %-24s%n", "ID", "Time", "Food", "Creature ID", "Creature Name");
        System.out.println("--------------------------------------------------------------------------------------");

        String[] objects = splitJsonObjects(json);

        if (objects.length == 0) {
            System.out.println("None need attending at this time.");
            return;
        }

        for (String object : objects) {
            String id = getValue(object, "id");
            String feedingTime = getValue(object, "feedingTime");
            String foodType = getValue(object, "foodType");
            String creatureId = getValue(object, "creatureId");
            String creatureName = getValue(object, "creatureName");

            System.out.printf("%-6s %-12s %-24s %-12s %-24s%n", id, feedingTime, foodType, creatureId, creatureName);
        }
    }

    private static void printUserTable(String json) {
        System.out.println();
        System.out.println("=== System Users ===");
        System.out.printf("%-6s %-24s %-28s %-18s %-18s%n", "ID", "Full Name", "Email", "Phone", "Role");
        System.out.println("------------------------------------------------------------------------------------------------");

        String[] objects = splitJsonObjects(json);

        if (objects.length == 0) {
            System.out.println("No system users found.");
            return;
        }

        for (String object : objects) {
            String id = getValue(object, "id");
            String fullName = getValue(object, "fullName");
            String email = getValue(object, "email");
            String phone = getValue(object, "phone");
            String roleName = getValue(object, "roleName");

            System.out.printf("%-6s %-24s %-28s %-18s %-18s%n", id, fullName, email, phone, roleName);
        }
    }

    private static String[] splitJsonObjects(String json) {
        if (json == null || json.equals("[]") || json.isBlank()) {
            return new String[0];
        }

        String cleaned = json.trim();

        if (cleaned.startsWith("[")) {
            cleaned = cleaned.substring(1);
        }

        if (cleaned.endsWith("]")) {
            cleaned = cleaned.substring(0, cleaned.length() - 1);
        }

        if (cleaned.isBlank()) {
            return new String[0];
        }

        return cleaned.split("\\},\\{");
    }

    private static String getValue(String object, String key) {
        String cleaned = object.replace("{", "").replace("}", "");
        String search = "\"" + key + "\":";

        int start = cleaned.indexOf(search);

        if (start == -1) {
            return "";
        }

        start = start + search.length();

        if (start >= cleaned.length()) {
            return "";
        }

        if (cleaned.charAt(start) == '"') {
            start++;
            int end = cleaned.indexOf("\"", start);

            if (end == -1) {
                return "";
            }

            return cleaned.substring(start, end);
        }

        int end = cleaned.indexOf(",", start);

        if (end == -1) {
            end = cleaned.length();
        }

        return cleaned.substring(start, end).trim();
    }
}