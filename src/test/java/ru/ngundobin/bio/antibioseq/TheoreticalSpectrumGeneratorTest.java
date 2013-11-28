package ru.ngundobin.bio.antibioseq;

import org.testng.annotations.Test;
import ru.ngundobin.bio.model.AminoAcidMassMapBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TheoreticalSpectrumGeneratorTest {

    @Test
    public void testGenerate_smallTest()
            throws Exception {
        String peptide = "LEQN";
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(113);
        expected.add(114);
        expected.add(128);
        expected.add(129);
        expected.add(227);
        expected.add(242);
        expected.add(242);
        expected.add(257);
        expected.add(355);
        expected.add(356);
        expected.add(370);
        expected.add(371);
        expected.add(484);
        AminoAcidMassMapBuilder builder = new AminoAcidMassMapBuilder();
        TheoreticalSpectrumGenerator generator = new TheoreticalSpectrumGenerator(builder.map());

        List<Integer> result = generator.generate(peptide);

        assertEquals(result, expected);
    }
}
