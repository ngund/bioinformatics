package ru.ngundobin.bio.dnarepl.pmp;

import org.testng.annotations.Test;
import ru.ngundobin.bio.model.Genome;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class PatternFinderTest {

    @Test
    public void testFindPatternPositions_smallTest()
            throws Exception {
        String text = "GATATATGCATATACTT";
        Genome genome = new Genome(text);
        String pattern = "ATAT";
        PatternFinder finder = new PatternFinder(genome);
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(3);
        expected.add(9);

        Set<Integer> result = finder.findPatternPositions(pattern);

        assertEquals(result, expected);
    }

    @Test
    public void testFindPatternPositions_bigTest()
            throws Exception {
        String text = "CCGAACACCCGTACACCGAACACCACACCACACCTTGCACACCACACCTACACCACACACCACACCGGACACCCACACCCACACCACGAACACC" +
                      "GAGAGTACACCTACACCTGACACCGGGGATCGTCACACCAAGTGGTGATACACCCACACCCTTTACACCTACACCACACCCGTACACCCTGAAC" +
                      "ACCACACCTAGAGAGTTGCACACCTCACACCGAAGGCACACCACACCATCCACACCATAAACACCGTTAACACCGTAGAACACCCAGCACACCC" +
                      "TTACCGCATACACCGACGTTAGACACCCACACCGGCAGTCACACCGTACACCCATTCGGTCCACACCCTACACCGCCTGCCACACCTACTGAGT" +
                      "TACACCGCATGACACCATTATCCGAACACACCAATATACACCAACACCATACACCATTTAACACCCCAAAACACCGACACCGACACCGCAAGCC" +
                      "CACACCACACCCACACCACAGACACCTACACCGTTTAGACACCAACACCGACACCACACCCCACACCCAAGACACCGCTACACCCTGCTGGACA" +
                      "CCGACACCTACACCTCACACCGGACACCGCACACACCGCCACACCAATCACACCACACCACACCAGTACAACACCGACACCTACACCACACCAC" +
                      "ACCCAGATACACCCACACCGGACACCACACCAAACACCATTACACCCACACCGGTACACCACACCTCGTACACCAAGTAGACACCCAACACACC" +
                      "ACACCTTGATGACACCTGACACCATACACCAAACACCACACCGAGGTAGACACCACACCGCCATCGACCACACCCTGACACCATACACCACACC" +
                      "ACACCTAGTCGACACCCACACCCTCACACCTGACACCCGCGGCATACACCCACACCACTTACACCTACACCGGGGGAAACACCGAAACACCTCA" +
                      "ACACCGGACACCACACCTAAGACACCGGGCGATACACCTGACCCTGACACCACACCACACCCAACACCCGAACACCACACCCAAACCTTGACAC" +
                      "CCACACCAAAACACCCTTTATTAAAACACCCCGACCACCAAACACCACACCCCACACCGAACACCCACACCGCATACACCGGTCACACCTTATC" +
                      "TCGCCCACACCCTACACCCCACACCACACCACACCACACCGTACCACACCACACCCCCACACCAAAACACCACACCACACCGGTTACACCCCAC" +
                      "ACCAACACCCACACCATTACACCTACACCGCAACACCTGCACACCACACCAAGACTGGAGACACCTACCACACCCTCGTTTACACCACCTGACA" +
                      "CCTTACACCTCCGACACCAAAAACCCGTTGGGTCATCGGATCAGGACACCTTTACACCACACCTTCGAGGACACCACGGACACCACACCCCACA" +
                      "CCACACCGGTACACCGCGTTCACACCTCACACCGACACCACACCCCCTGAACTGTATACACCACACCACACCAACCCAACACCCTAGAAGACAC" +
                      "CTGCCACACCTTACACCACACCACCGACACCAACACCCAAACACCTTTGACACACCACACCAACACCGTACACCGCAACACCCGCATTACACCT" +
                      "TACACCACACCACACCCCCCTACACCCACACCACACCCTCGGACACCAGTACACCACACCACAGATAGACACCATACACCTTACACCACATACA" +
                      "CCTTTCACACCACACCCACACCCCGCTTAGACACCGACACCACACCACACCTGACACCACACCTCGCACACCGCCCTTACACCACACCCCAGCA" +
                      "GAAAACGAACACCCACACCACACCACACACCACACCACACCACACCGACACCTGACACCTAAACACCCCCACACCACACCTCTCCAACACCACA" +
                      "CCAACACCTACACCAGAAAGACACCGACACCCGACACCCGCTGTTGTACACCCACACCATCGACACCACACCACACCACACCCTACACCGGCAC" +
                      "ACCATGCAAACACCACACACCTGGACACCCACACCACACCGCACACCACACCACACCTACACCACCGACACCACACCACACACCTACTCCACAA" +
                      "CACCTACACCAAACACCCTACACCTACACCTACACCTACATACACCTACACCTAATATTATGGACACCACACCTTCAGACACCGTACACCACAC" +
                      "ACCCTATGTTACACCACAGGCAGAATTTGACACCTCACACCCACACCCACACCCGCACACCACACCAACACCACACCACACCCCCAACACCGCT" +
                      "CTTACACCTTACACCGACACCAACACCGACACCGACACCACACCCCAATATCCCTCACACCACACCTAACCAGTATACACCGTTGACAACACCC" +
                      "CAATTTACACCCCATACACCTCAGACCACACACCGGACGGGCAACACCTACACCGATGTTACTTTACACCGGGCTCGCGGACACCACTCGACAC" +
                      "CAACACCCGACACCTTACACCACACCAGCTGCGTGAACACCTACACCATCCCAACACCACACCGACACCGTATGGACACCTACACCTCGAGAGT" +
                      "TCCGCTAGAACACCACACCCATACACCATACACCGCGTACACCGAACACCGACACCCACACCACACCCAATGACACCGATGACACCGGCTCGAT" +
                      "ACACCTACACCGAACACCATCAGACACCGCGTACACCCAACACCTGACACCAACACCGCGGCACACCTAGTGACACCTACACCTACACCACACC" +
                      "ATACACCCTACACCGATGAACACCAACACCACTCTAAACACCCAGGACACCAACACACCTAGACACCACACCAACGACAGAGACACCCTACACC" +
                      "TGCCAAGCTTTACACCATTGGTGAATCACACACCACACCAACACCACACCACACCGCTTACACCCGACCCGAAAACACCCACACCACACCAACA" +
                      "CCACACCACATTACTCCCGTTACACCTACACCAACACCACACCTTTACACCACACCCAGCAACACCACACCAAATGGACACCACACCACACCAC" +
                      "ACCTTAGCCGATGTGCCGACACCGCTGTCGTCACACCAGTGACACCTTAGCGTACACACCACACCCAACACCTACACCACACCCGAAACACCTG" +
                      "ACACCACACCACACCACACCCTACACCACACCATGACCACACACCAGCCGACACCACACCATACACCTACACCGAAACACCTTTCTACACCACA" +
                      "CCACACCTGAACACCTAGTCACACCACGACACCAACACCTGACCACACCGGGGGACACCTTTGGAACGACACCTAACACCGCCACACCACACCA" +
                      "CACCCGACACCTATAACACCACACCACACCACACCAAAGGCACACCTTAACACCCACACCAAGGGCTACACCACACCACACCTCCAAAACAAGG" +
                      "GACACCACACCCAACACCACACCACACCGCGTGGACACCACACCTTGACACCAAATTGTGCACACCACACCTGCACACCTTAAGAACGACACCG" +
                      "TCAGTACACCGAAACCCTATGACACCTGGGACACCTGGCACACCAACTACACCACACCCACACCACACACCTGGACACCGTTTCGCGAGTGTGG" +
                      "GTTGCTTGACACCACACCACACCGCGGCCTTACACCGCACACCGTAAACACCGTTGACACCTCATTACTCGACACCACACCGCACACCCACACC" +
                      "CGACACCGAACACCACACCTGGGCATACACACCACACCGTACACCTACACCACACCTGTGCTACACCAGGGGTACACCACACCTAGTACACCAC" +
                      "ACCGATACACCCACACCACACCACACCCACCAACACCACACCATCAAGAACACCCTATACACCCACACCACACCTACACCACACCCTACACCA" +
                      "CACCACACCACACCATCGACACCTACACCACACCAACACCACACCAAACACCACACCCACACCCGGACACCACACCCACACCACACCATAACAC" +
                      "CTAACACCACACACCTACACCTACTCTGCTAAACACCCAACACCTCTACACCCTGCCGACACCGCGACACCGGCGACACCCTGTTACACCACAC" +
                      "CTCACACCTTCGACACCAGCCAGAGACACCGGACACCGACACCCCGAACACCAACACACCCGA";
        String pattern = "ACACCA";
        Genome genome = new Genome(text);
        PatternFinder finder = new PatternFinder(genome);
        Set<Integer> expected = new HashSet<>();
        expected.add(19);
        expected.add(24);
        expected.add(38);
        expected.add(49);
        expected.add(56);
        expected.add(80);
        expected.add(128);
        expected.add(164);
        expected.add(186);
        expected.add(225);
        expected.add(230);
        expected.add(239);
        expected.add(387);
        expected.add(403);
        expected.add(413);
        expected.add(419);
        expected.add(426);
        expected.add(471);
        expected.add(482);
        expected.add(482);
        expected.add(508);
        expected.add(520);
        expected.add(604);
        expected.add(613);
        expected.add(618);
        expected.add(623);
        expected.add(646);
        expected.add(651);
        expected.add(679);
        expected.add(684);
        expected.add(691);
        expected.add(713);
        expected.add(727);
        expected.add(747);
        expected.add(770);
        expected.add(777);
        expected.add(784);
        expected.add(801);
        expected.add(829);
        expected.add(836);
        expected.add(841);
        expected.add(897);
        expected.add(947);
        expected.add(986);
        expected.add(991);
        expected.add(1011);
        expected.add(1036);
        expected.add(1075);
        expected.add(1148);
        expected.add(1153);
        expected.add(1158);
        expected.add(1173);
        expected.add(1186);
        expected.add(1194);
        expected.add(1199);
        expected.add(1220);
        expected.add(1232);
        expected.add(1262);
        expected.add(1267);
        expected.add(1303);
        expected.add(1329);
        expected.add(1369);
        expected.add(1386);
        expected.add(1395);
        expected.add(1407);
        expected.add(1444);
        expected.add(1467);
        expected.add(1472);
        expected.add(1477);
        expected.add(1516);
        expected.add(1521);
        expected.add(1530);
        expected.add(1555);
        expected.add(1560);
        expected.add(1599);
        expected.add(1604);
        expected.add(1625);
        expected.add(1640);
        expected.add(1648);
        expected.add(1653);
        expected.add(1666);
        expected.add(1680);
        expected.add(1698);
        expected.add(1728);
        expected.add(1733);
        expected.add(1745);
        expected.add(1770);
        expected.add(1800);
        expected.add(1805);
        expected.add(1812);
        expected.add(1817);
        expected.add(1822);
        expected.add(1856);
        expected.add(1872);
        expected.add(1877);
        expected.add(1889);
        expected.add(1933);
        expected.add(1942);
        expected.add(1947);
        expected.add(1952);
        expected.add(1972);
        expected.add(1983);
        expected.add(2004);
        expected.add(2016);
        expected.add(2021);
        expected.add(2032);
        expected.add(2041);
        expected.add(2046);
        expected.add(2073);
        expected.add(2131);
        expected.add(2153);
        expected.add(2172);
        expected.add(2218);
        expected.add(2223);
        expected.add(2229);
        expected.add(2234);
        expected.add(2272);
        expected.add(2290);
        expected.add(2312);
        expected.add(2430);
        expected.add(2440);
        expected.add(2460);
        expected.add(2465);
        expected.add(2486);
        expected.add(2497);
        expected.add(2547);
        expected.add(2560);
        expected.add(2595);
        expected.add(2645);
        expected.add(2678);
        expected.add(2716);
        expected.add(2721);
        expected.add(2745);
        expected.add(2751);
        expected.add(2772);
        expected.add(2788);
        expected.add(2793);
        expected.add(2831);
        expected.add(2849);
        expected.add(2854);
        expected.add(2860);
        expected.add(2865);
        expected.add(2900);
        expected.add(2905);
        expected.add(2911);
        expected.add(2916);
        expected.add(2941);
        expected.add(2947);
        expected.add(2960);
        expected.add(2975);
        expected.add(2980);
        expected.add(2991);
        expected.add(2996);
        expected.add(3001);
        expected.add(3040);
        expected.add(3063);
        expected.add(3081);
        expected.add(3102);
        expected.add(3107);
        expected.add(3112);
        expected.add(3124);
        expected.add(3129);
        expected.add(3142);
        expected.add(3152);
        expected.add(3157);
        expected.add(3188);
        expected.add(3193);
        expected.add(3216);
        expected.add(3224);
        expected.add(3279);
        expected.add(3284);
        expected.add(3305);
        expected.add(3310);
        expected.add(3315);
        expected.add(3320);
        expected.add(3345);
        expected.add(3357);
        expected.add(3362);
        expected.add(3385);
        expected.add(3397);
        expected.add(3402);
        expected.add(3418);
        expected.add(3431);
        expected.add(3445);
        expected.add(3517);
        expected.add(3526);
        expected.add(3537);
        expected.add(3580);
        expected.add(3585);
        expected.add(3643);
        expected.add(3675);
        expected.add(3694);
        expected.add(3712);
        expected.add(3728);
        expected.add(3739);
        expected.add(3753);
        expected.add(3772);
        expected.add(3777);
        expected.add(3792);
        expected.add(3797);
        expected.add(3824);
        expected.add(3835);
        expected.add(3847);
        expected.add(3852);
        expected.add(3857);
        expected.add(3862);
        expected.add(3877);
        expected.add(3882);
        expected.add(3888);
        expected.add(3893);
        expected.add(3900);
        expected.add(3919);
        expected.add(3930);
        expected.add(3935);
        expected.add(3950);
        expected.add(4032);
        expected.add(4053);
        expected.add(4088);

        Set<Integer> result = finder.findPatternPositions(pattern);

        assertEquals(result, expected);
    }

    @Test
    public void testFindPatternMismatches_smallTest()
            throws Exception {
        String text = "CGCCCGAATCCAGAACGCATTCCCATATTTCGGGACCACTGGCCTCCACGGTACGGACGTCAATCAAAT";
        Genome genome = new Genome(text);
        String pattern = "ATTCTGGA";
        int mismatches = 3;
        PatternFinder finder = new PatternFinder(genome);
        Set<Integer> expected = new HashSet<>();
        expected.add(6);
        expected.add(7);
        expected.add(26);
        expected.add(27);

        Set<Integer> result = finder.findPatternPositions(pattern, mismatches);

        assertEquals(result, expected);
    }

}
