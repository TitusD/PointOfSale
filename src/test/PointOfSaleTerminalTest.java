package test;

import controller.PointOfSaleTerminal;
import model.Produce;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointOfSaleTerminalTest {

    private PointOfSaleTerminal testTerminal;

    @Test
    void setPricing() {
        testTerminal = new PointOfSaleTerminal();
        Produce testProduce = new Produce("A");
        testProduce.addPricing(1, 1.25);

        assertEquals(testTerminal.priceMap.size(), 0);
        testTerminal.setPricing("A", 1.25, 1);
        assertEquals(testTerminal.priceMap.size(), 1);
        assertEquals(testTerminal.priceMap.get("A").equals(testProduce), true);
    }

    @Test
    void scan() {

        testTerminal = new PointOfSaleTerminal();
        assertEquals(testTerminal.cartMap.size(), 0);
        testTerminal.scan("A");
        assertEquals(testTerminal.cartMap.size(), 1);
        testTerminal.scan("B");
        assertEquals(testTerminal.cartMap.size(), 2);
        testTerminal.scan("A");
        assertEquals((int)testTerminal.cartMap.get("A"), 2);
    }

    @Test
    void calculateTotal() {

        testTerminal = new PointOfSaleTerminal();
        testTerminal.setPricing("A", 1.25, 1);
        testTerminal.setPricing("A", 3.00, 3);
        testTerminal.setPricing("B", 4.25, 1);
        testTerminal.setPricing("C", 1.00, 1);
        testTerminal.setPricing("C", 5.00, 6);
        testTerminal.setPricing("D", 0.75, 1);
        testTerminal.scan("A");
        testTerminal.scan("B");
        testTerminal.scan("C");
        testTerminal.scan("A");
        testTerminal.scan("A");
        testTerminal.scan("A");

        assertEquals(testTerminal.calculateTotal().doubleValue(), 9.5);
    }

    static class ProduceTest {

        private Produce testProduce;


        @Test
        void calculateProducePrice() {

            testProduce = new Produce("A");
            testProduce.addPricing(1, 1.25);
            testProduce.addPricing(3, 3.0);

            assertEquals(testProduce.calculateProducePrice(7).doubleValue(), 7.25);

            testProduce.addPricing(10, 5.0 );

            assertEquals(testProduce.calculateProducePrice(17).doubleValue(), 12.25);

        }
    }
}