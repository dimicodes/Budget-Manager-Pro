/**

 * Budget class contains the printMenu(), updateIncome(), addPurchase(), listPurchases(),
 * and showBalance() methods.

 */

package budget;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Budget {
    static double income = 0;
    static Scanner input = new Scanner(System.in);
    static ArrayList<Product> purchases = new ArrayList<>();

    /**
     * printMenu() prints the main menu of the Budget Manager Pro program
     */
    public static void printMenu() {
        boolean runMenu = true;

        while (runMenu) {
            System.out.println("\nChoose your action:");
            System.out.println("1) Add income");
            System.out.println("2) Add purchase");
            System.out.println("3) Show list of purchases");
            System.out.println("4) Balance");
            System.out.println("5) Save");
            System.out.println("6) Load");
            System.out.println("7) Analyze (Sort)");
            System.out.println("0) Exit");

            int userChoice = input.nextInt();

            switch (userChoice) {
                case 1:
                    updateIncome();
                    break;
                case 2:
                    addPurchase();
                    break;
                case 3:
                    listPurchases();
                    break;
                case 4:
                    showBalance();
                    break;
                case 5:
                    Database.saveData();
                    break;
                case 6:
                    Database.loadData();
                    break;
                case 7:
                    System.out.print("\n");
                    Analysis.sortMenu();
                    break;
                case 0:
                    System.out.println("\nBye!");
                    runMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.\n");
                    break;
            }
        }
    }

    /**
     * updateIncome() updates the static income variable
     */
    public static void updateIncome() {
        System.out.println("\nEnter income:");
        income += input.nextDouble();
        System.out.println("Income was added!");
    }

    /**
     * addPurchase() adds purchase name, category, and price to the ArrayList "purchases"
     */
    public static void addPurchase() {
        while (true) {
            System.out.println("\nChoose the type of purchase");
            System.out.println("1) Food");
            System.out.println("2) Clothes");
            System.out.println("3) Entertainment");
            System.out.println("4) Other");
            System.out.println("5) Back");

            int userChoice;
            try {
                userChoice = input.nextInt();
                input.nextLine(); // Consume the rest of the line
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                input.nextLine(); // Consume the invalid input
                continue; // Restart the loop
            }

            if (userChoice == 5) {
                return; // Exit the method
            }

            if (userChoice < 1 || userChoice > 5) {
                System.out.println("Please select a valid option.");
                continue; // Restart the loop for a valid option
            }

            System.out.println("\nEnter purchase name:");
            String purchaseName = input.nextLine();

            System.out.println("Enter its price:");
            double purchasePrice;
            try {
                purchasePrice = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid price.");
                input.nextLine(); // Consume the invalid input
                continue; // Restart the loop
            }

            String purchaseCategory = "";
            switch (userChoice) {
                case 1:
                    purchaseCategory = "Food";
                    break;
                case 2:
                    purchaseCategory = "Clothes";
                    break;
                case 3:
                    purchaseCategory = "Entertainment";
                    break;
                case 4:
                    purchaseCategory = "Other";
                    break;
            }

            purchases.add(new Product(purchaseName, purchasePrice, purchaseCategory));
            System.out.println("Purchase was added!");
        }
    }

    /**
     * listPurchases() iterates through the purchases array and prints purchases by category
     */
    public static void listPurchases() {
        while (true) {
            if (purchases.isEmpty()) {
                System.out.println("\nThe purchase list is empty\n");
                return;
            }

            System.out.println("\nChoose the type of purchases");
            System.out.println("1) Food");
            System.out.println("2) Clothes");
            System.out.println("3) Entertainment");
            System.out.println("4) Other");
            System.out.println("5) All");
            System.out.println("6) Back");

            int userChoice = input.nextInt();
            input.nextLine();  // Consume the rest of the line

            if (userChoice == 6) {
                return;
            }

            String category = "";
            switch (userChoice) {
                case 1:
                    category = "Food";
                    break;
                case 2:
                    category = "Clothes";
                    break;
                case 3:
                    category = "Entertainment";
                    break;
                case 4:
                    category = "Other";
                    break;
                case 5:
                    category = "All";
                    break;
            }

            double sumOfPurchases = 0;
            System.out.println("\n" + category + ":");
            for (Product purchase : purchases) {
                if (category.equals("All") || purchase.getCategory().equals(category)) {
                    System.out.println(purchase.getName() + " $" + String.format("%.2f", purchase.getPrice()));
                    sumOfPurchases += purchase.getPrice();
                }
            }
            System.out.println("Total Sum: $" + String.format("%.2f", sumOfPurchases));
        }
    }

    /**
     * showBalance() shows net income after subtracting expenses
     */
    public static void showBalance() {
        double expenses = 0;
        for (Product purchase : purchases) {
            expenses += purchase.getPrice();
        }
        double netIncome = income - expenses;
        if (netIncome < 0) {
            netIncome = 0;
        }
        String netIncomeString = String.format("%.2f", netIncome);
        System.out.println("\nBalance: $" + netIncomeString);
    }
}