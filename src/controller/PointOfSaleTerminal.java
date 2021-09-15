package controller;

import java.math.BigDecimal;
import java.util.*;
import java.lang.*;
import model.Produce;


/*
This Class implements a point of sale terminal that can take in new prices for an item, scan items in a cart, and
calculate the total price.

However, this implementation assumes perfect usage as there is no sort of safeguards.

Example - If only items A, B, C, and D have valid prices, and the user scans an item "E", then there will be an issue.
 */
public class PointOfSaleTerminal {

    // PriceMap is the hashmap tracking the various prices for a given product
    public HashMap<String, Produce> priceMap;

    // CartMap is the hashmap tracking the items and quantity in the user's "cart"
    public HashMap<String, Integer> cartMap;


    public PointOfSaleTerminal() {
        this.priceMap = new HashMap<>();
        this.cartMap = new HashMap<>();
    }

    public static void main(String args[]) throws Exception {

        PointOfSaleTerminal terminal = new PointOfSaleTerminal();
        terminal.setPricing("A", 1.25, 1);
        terminal.setPricing("A", 3.00, 3);
        terminal.setPricing("B", 4.25, 1);
        terminal.setPricing("C", 1.00, 1);
        terminal.setPricing("C", 5.00, 6);
        terminal.setPricing("D", 0.75, 1);

        // Test cases from the document below

//        terminal.scan("A");
//        terminal.scan("B");
//        terminal.scan("C");
//        terminal.scan("D");
//        terminal.scan("A");
//        terminal.scan("B");
//        terminal.scan("A");

//        terminal.scan("C");
//        terminal.scan("C");
//        terminal.scan("C");
//        terminal.scan("C");
//        terminal.scan("C");
//        terminal.scan("C");
//        terminal.scan("C");
//
//        terminal.scan("A");
//        terminal.scan("B");
//        terminal.scan("C");
//        terminal.scan("D");

        BigDecimal totalCost = terminal.calculateTotal();

        System.out.println("Your Total is: " + totalCost.toString());
    }

    /*
    This function sets the prices by adding it to a hashmap if it is a new item, or adding the new pricing to the
    existing code
     */
    public void setPricing(String code, double price, int volume) {

        if (!priceMap.containsKey(code)) {
            priceMap.put(code, new Produce("A"));
        }
        priceMap.get(code).addPricing(volume, price);
    }

    /*
    This function scans new items by adding it to a hashmap if its a new item, or incrementing the count of an existing
    item
     */
    public void scan(String code) {

        if (!cartMap.containsKey(code)) {
            cartMap.put(code, 1);
        } else {
            cartMap.put(code, cartMap.get(code) + 1);
        }
    }

    /*
    This function calculates the total price by adding each item's price (with volume prices accounted for) to a overall
    total price.
     */
    public BigDecimal calculateTotal() {

        BigDecimal totalPrice = new BigDecimal(0);

        for (String key : cartMap.keySet()) {

            // calculateProducePrice calculates the total price for a given produce, taking into account volume prices
            totalPrice = totalPrice.add(priceMap.get(key).calculateProducePrice(cartMap.get(key)));
        }

        return totalPrice;
    }
}