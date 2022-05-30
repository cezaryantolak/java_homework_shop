package configuration.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Product {

    public static List<Product> productList = new ArrayList<>();
    private static Logger log = LoggerFactory.getLogger("Product.class");
    private String productName;
    private double productPrice;
    private int quantity;

    public Product(String productName, double productPrice, int quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public static double getAllOrderCost() {
        double total = 0;
        for (Product product : productList) {
            int quantity = product.getQuantity();
            double price = product.getProductPrice();
            total += quantity * price;
        }
        return total;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }


    @Override
    public String toString() {
        return "Product: " +
                productName +
                ", productPrice: " + productPrice +
                ", quantity: " + quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.productPrice, productPrice) == 0 && Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice);
    }
}
