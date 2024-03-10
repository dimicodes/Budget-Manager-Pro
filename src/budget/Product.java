/**
 * Product is a class that creates purchase/product objects that each have
 * a name, price, and category.
 */

package budget;

class Product {
    private final String name;
    private final double price;
    private final String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
