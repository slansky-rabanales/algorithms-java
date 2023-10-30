package org.jrslanski.divideandconquer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ClosestPairTest {
    @Test
    void test1(){
        var listOfPoints = List.of(
                new ClosestPair.Pair(-4, -4),
                new ClosestPair.Pair(-1, -1),
                new ClosestPair.Pair(-1, 1),
                new ClosestPair.Pair(1, 1),
                new ClosestPair.Pair(2, 1),
                new ClosestPair.Pair(4,4),
                new ClosestPair.Pair(-1,4),
                new ClosestPair.Pair(3,3),
                new ClosestPair.Pair(4,2),
                new ClosestPair.Pair(2,-1),
                new ClosestPair.Pair(2,-3),
                new ClosestPair.Pair(2,-5),
                new ClosestPair.Pair(1,-4)
        );

        var closestPair = new ClosestPair();
        var result = closestPair.getClosestPair(listOfPoints);
        Assertions.assertEquals(Double.valueOf("1"), result.distance());
    }
}
