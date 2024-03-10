/**
 * Database class contains saveData() and loadData() methods to save the "purchases"
 * ArrayList to a .txt file, and to load the products/purchases from the .txt file
 * back into the "purchases" ArrayList when loaded.
 */

package budget;

import java.io.*;

class Database {
    private static final String FILE_PATH = "purchases.txt";

    /**
     * saveData() uses PrintWriter to iterate through the purchases ArrayList
     * and write them to the "purchases.txt" file
     */
    public static void saveData() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(FILE_PATH))) {
            printWriter.println(Budget.income);
            for (Product purchase : Budget.purchases) {
                printWriter.println(purchase.getName() + "," + purchase.getPrice() + "," + purchase.getCategory());
            }
            System.out.println("\nPurchases were saved!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving purchases to file: " + e.getMessage());
        }
    }

    /**
     * loadData() uses BufferedReader to read the lines of the "purchases.txt" file
     * and add them to the "purchases" ArrayList
     */
    public static void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 1) {
                    Budget.income = Double.parseDouble(parts[0]);
                }
                if (parts.length == 3) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    String category = parts[2];
                    Budget.purchases.add(new Product(name, price, category));
                }
            }
            System.out.println("\nPurchases were loaded!");
        } catch (IOException | NumberFormatException e) {
            System.out.println("An error occurred while loading purchases from file: " + e.getMessage());
        }
    }
}
