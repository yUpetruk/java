package org.example;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Variant18Test {

    @Test
    public void testInteger() {
        int expected = 4;
        int actual = Variant18.integerTask(823874234);
        assertEquals(actual, expected);
    }

    @Test
    public void testBooleanTrue() {
        assertTrue(Variant18.booleanTask(1, 1, 2));
    }

    @Test
    public void testBooleanFalse() {
        assertFalse(Variant18.booleanTask(1, 2, 3));
    }

    @Test
    public void testIf1() {
        assertEquals(Variant18.if_task(1, 2, 2), 1);
    }

    @Test
    public void testIf2() {
        assertEquals(Variant18.if_task(2, 1, 2), 2);
    }

    @Test
    public void testIf3() {
        assertEquals(Variant18.if_task(2, 2, 1), 3);
    }

    @Test
    public void testCase() {
        assertEquals(Variant18.caseTask(119), "сто дев'ятнадцять");
    }

    @Test
    public void testFor() {
        assertEquals(Variant18.forTask(4, 6), -819.0);
    }

    @Test
    public void testWhile() {
        var expected = new int[] {27, 5};
        var actual = Variant18.whileTask(57654);
        assertEquals(actual, expected);
    }

    @Test
    public void testMinmax0() {
        assertEquals(Variant18.minmaxTask(10, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), 0);
    }

    @Test
    public void testMinmax() {
        assertEquals(Variant18.minmaxTask(10, new int[] {1, 2, 3, 4, 10, 6, 7, 8, 9, 10}), 4);
    }

    @Test
    public void testArray() {
        assertEquals(Variant18.arrayTask(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), 1);
    }

    @Test
    public void testArray0() {
        assertEquals(Variant18.arrayTask(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 1}), 0);
    }

    @Test
    public void testMatrix() {
        var matrix = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        var expected = new int[] { 18, 120 };

        assertEquals(Variant18.matrixTask(matrix, 1), expected);
    }
}