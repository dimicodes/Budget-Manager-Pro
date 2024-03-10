/**
 * Analysis Class contains sortMenu(), sortAllPurchases(), sortByType(), and
 * sortByCertainType() methods and allows the user to view a spending analysis
 * based on total spending, spending by category, and spending within a category.
 */

package budget;

import java.util.Comparator;
import java.util.Scanner;

class Analysis {
    static Scanner input = new Scanner(System.in);

    /**
     * sortMenu() prints the sorting menu options available
     */
    public static void sortMenu() {
        boolean validChoice = false;
        int userChoice = 0;

        while (!validChoice) {
            System.out.println("How do you want to sort?");
            System.out.println("1) Sort all purchases");
            System.out.println("2) Sort by type");
            System.out.println("3) Sort certain type");
            System.out.println("4) Back");
            userChoice = input.nextInt();

            switch (userChoice) {
                case 1:
                    sortAllPurchases();
                    validChoice = true;
                    break;
                case 2:
                    sortByType();
                    validChoice = true;
                    break;
                case 3:
                    sortByCertainType();
                    validChoice = true;
                    break;
                case 4:
                    System.out.print("\n");
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.\n");
                    break;
            }
        }
    }

    /**
     * sortAllPurchases() sorts all purchases from most to least expensive
     */
    public static void sortAllPurchases() {
        // Sort the purchases list using a custom comparator
        Budget.purchases.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                // Compare prices of two products
                // If p1's price is less than p2's price, return a positive value, indicating p1 comes before p2
                // If p1's price is greater than p2's price, return a negative value, indicating p1 comes after p2
                // If prices are equal, return 0
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });

        double purchaseSum = 0;
        for (Product purchase : Budget.purchases) {
            purchaseSum += purchase.getPrice();
        }

        if (purchaseSum == 0) {
            System.out.println("\nThe purchase list is empty!\n");
            sortMenu();
        } else {
            System.out.println("\nAll:");
            for (Product purchase : Budget.purchases) {
                System.out.println(purchase.getName() + " $" + String.format("%.2f", purchase.getPrice()));
            }
            System.out.println("Total: $" + String.format("%.2f", purchaseSum) + "\n");
            sortMenu();
        }
    }

    /**
     * sortByType() totals purchase costs by type and lists the types in order of
     * most to least costly
     */
    public static void sortByType() {
        double foodSum = 0;
        double entertainmentSum = 0;
        double clothesSum = 0;
        double otherSum = 0;

        for (Product purchase : Budget.purchases) {
            switch (purchase.getCategory()) {
                case "Food":
                    foodSum += purchase.getPrice();
                    break;
                case "Entertainment":
                    entertainmentSum += purchase.getPrice();
                    break;
                case "Clothes":
                    clothesSum += purchase.getPrice();
                    break;
                case "Other":
                    otherSum += purchase.getPrice();
                    break;
            }
        }

        System.out.println("\nTypes:");
        System.out.println("Food - $" + String.format("%.2f", foodSum));
        System.out.println("Entertainment - $" + String.format("%.2f", entertainmentSum));
        System.out.println("Clothes - $" + String.format("%.2f", clothesSum));
        System.out.println("Other - $" + String.format("%.2f", otherSum));
        System.out.println("Total sum: $" + String.format("%.2f", foodSum + entertainmentSum + clothesSum + otherSum) + "\n");
        sortMenu();
    }

    /**
     * sortByCertainType() prints purchases within a category from most to least expensive
     */
    public static void sortByCertainType() {
        System.out.println("\nChoose the type of purchase:");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        int userChoice = input.nextInt();
        input.nextLine(); // Consume newline character

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
            default:
                System.out.println("Invalid option. Please try again.");
                return;
        }

        double categorySum = 0;
        for (Product purchase : Budget.purchases) {
            if (purchase.getCategory().equals(category)) {
                categorySum += purchase.getPrice();
            }
        }
        if (categorySum == 0) {
            System.out.println("\nThe purchase list is empty!\n");
            sortMenu();
        } else {
            System.out.println("\n" + category + ":");
            for (Product purchase : Budget.purchases) {
                if (purchase.getCategory().equals(category)) {
                    System.out.println(purchase.getName() + " $" + String.format("%.2f", purchase.getPrice()));
                }
            }
            System.out.println("Total sum: $" + String.format("%.2f", categorySum) + "\n");
            sortMenu();
        }
    }
}
