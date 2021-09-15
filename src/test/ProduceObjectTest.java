package test;

import model.Produce;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProduceObjectTest {

    private Produce testProduce;

    @Test
    void calculateProducePrice() {

        testProduce = new Produce("A");

        testProduce.addPricing(1, 1.25);
        testProduce.addPricing(3, 3.0);
        assertEquals(testProduce.calculateProducePrice(7).doubleValue(), 7.25);

        testProduce.addPricing(10, 5.0);
        assertEquals(testProduce.calculateProducePrice(17).doubleValue(), 12.25);
    }
}