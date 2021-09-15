package model;

import java.math.BigDecimal;
import java.util.*;

public class Produce {
    private String code;
    private TreeMap<Integer, Pricing> volumePricesList;

    public Produce(String code) {
        this.code = code;
        this.volumePricesList = new TreeMap<>();
    }

    private class Pricing {
        private int volumeQuantity;
        private double volumePrice;

        Pricing(int volumeQuantity, double volumePrice) {
            this.volumePrice = volumePrice;
            this.volumeQuantity = volumeQuantity;
        }
    }

    public void addPricing(int volume, double price) {
        volumePricesList.put(volume, new Pricing(volume, price));
    }

    /*
    This function calculates the cost for a given quantity of items, and checks/incorporates the volume price.
     */
    public BigDecimal calculateProducePrice(int numItem) {

        BigDecimal totalPrice = new BigDecimal(0.0);

        // Makes a sorted list of all of the volume prices
        Integer[] volumes = volumePricesList.keySet().toArray(new Integer[volumePricesList.size()]);

        int itemsRemaining = numItem;

        // Iterates backwards from the list of all of the volume prices
        for (int i = volumes.length - 1; i >= 0; i--) {
            if (itemsRemaining == 0) {
                break;
            }
            // Find the maximum number of items that can be part of the sale prices
            int numOfVolume = itemsRemaining / volumes[i];

            // Add that prices to the sum
            totalPrice = totalPrice.add(new BigDecimal(volumePricesList.get(volumes[i]).volumePrice * numOfVolume));

            // Finds the remaining number of items that couldn't be a part of a sale prices.
            itemsRemaining %= volumes[i];
        }

        return totalPrice;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Produce)) {
            return false;
        }

        return this.code.equals(((Produce) object).code);
    }
}
