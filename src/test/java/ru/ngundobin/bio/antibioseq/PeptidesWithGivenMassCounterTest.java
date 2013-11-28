package ru.ngundobin.bio.antibioseq;

import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

public class PeptidesWithGivenMassCounterTest {

    @Test
    public void testCountForMass_smallTest()
            throws Exception {
        HashMap<String, Integer> massTable = new HashMap<>();
        massTable.put("A", 1);
        massTable.put("B", 2);
        massTable.put("C", 2);
        massTable.put("D", 3);
        int desiredMass = 4;
        PeptidesWithGivenMassCounter counter = new PeptidesWithGivenMassCounter(massTable);
        int expected = 13;

        int result = counter.countForMass(desiredMass);

        assertEquals(result, expected);
    }
}
