package org.jrslanski.divideandconquer;

import java.util.Comparator;
import java.util.List;

public class ClosestPair {
    public record Pair(
            int x,
            int y
    ){}

    public record ClosestPairResult(
            Pair p,
            Pair q,
            Double distance
    ){}

    public ClosestPairResult getClosestPair(List<Pair> pairs){
        var sortedByX = pairs.stream().sorted(Comparator.comparingInt(Pair::x)).toList();
        var sortedByY = pairs.stream().sorted(Comparator.comparingInt(Pair::y)).toList();
        return getClosestPairResult(sortedByX, sortedByY);
    }

    private ClosestPairResult getClosestPairResult(List<Pair> pairsSortedByX, List<Pair> pairsSortedByY){
        if(pairsSortedByX.size() == 1)
            return new ClosestPairResult(pairsSortedByX.get(0), null, Double.MAX_VALUE);
        if(pairsSortedByX.size() == 2)
            return new ClosestPairResult(pairsSortedByX.get(0), pairsSortedByX.get(1), getDistance(pairsSortedByX.getFirst(), pairsSortedByX.getLast()));
        var halfSize = pairsSortedByX.size() / 2;
        var leftHalfPoints = pairsSortedByX.subList(0, halfSize);
        var rightHalfPoints = pairsSortedByX.subList(halfSize, pairsSortedByX.size());
        var leftResult = getClosestPairResult(leftHalfPoints, leftHalfPoints.stream().sorted(Comparator.comparingInt(Pair::y)).toList());
        var rightResult = getClosestPairResult(rightHalfPoints, rightHalfPoints.stream().sorted(Comparator.comparingInt(Pair::y)).toList());
        var delta = Math.min(leftResult.distance, rightResult.distance);
        var splitResult = getClosestSplitPair(pairsSortedByX, pairsSortedByY, delta, leftHalfPoints.getLast());
        return List.of(leftResult, rightResult, splitResult).stream().min(Comparator.comparingDouble(ClosestPairResult::distance)).get();
    }

    private ClosestPairResult getClosestSplitPair(List<Pair> pairsSortedByX, List<Pair> pairsSortedByY, double delta, Pair lastPointInLeft) {
            var sy = pairsSortedByY.stream().filter(pair -> {
                if(pair.x >= (lastPointInLeft.x - delta) || pair.x <= (lastPointInLeft.x + delta) )
                    return true;
                return false;
            }).toList();
            var closestPairResult = new ClosestPairResult(null, null, delta);
            for(int i = 0; i <= sy.size() - 2; i++){
                for(int j = 1; j <= Math.min(7, sy.size() - 1 - i); j++){
                    var p = sy.get(i);
                    var q = sy.get(j + i);
                    var distance = getDistance(p, q);
                    if(distance < closestPairResult.distance){
                        closestPairResult = new ClosestPairResult(p, q, distance);
                    }
                }
            }
            return closestPairResult;
    }

    private Double getDistance(Pair p, Pair q){
        return Math.sqrt(Math.pow(p.x - q.x, 2) + Math.pow(p.y - q.y, 2));
    }

}
