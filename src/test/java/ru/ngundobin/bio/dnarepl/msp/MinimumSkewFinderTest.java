package ru.ngundobin.bio.dnarepl.msp;

import org.testng.annotations.Test;
import ru.ngundobin.bio.model.Genome;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class MinimumSkewFinderTest {

    @Test
    public void testFindMinimumSkewIndexes_smallTest()
            throws Exception {

        String text = "TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT";
        Genome genome = new Genome(text);
        List<Integer> expected = new ArrayList<>();
        expected.add(11);
        expected.add(24);
        MinimumSkewFinder finder = new MinimumSkewFinder(genome);

        List<Integer> result = finder.findMinimumSkew();

        assertEquals(result, expected);
    }
    //
    //    @Test
    //    public void testFindMinimumSkewIndexes_bigTest()
    //            throws Exception {
    //
    //        String text = "TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT";
    //        Genome genome = new Genome(text);
    //        List<Integer> expected = new ArrayList<>();
    //        expected.add(11);
    //        expected.add(24);
    //        MinimumSkewFinder finder = new MinimumSkewFinder(genome);
    //
    //        List<Integer> result = finder.findMinimumSkew();
    //
    //        assertEquals(result, expected);
    //    }
}
