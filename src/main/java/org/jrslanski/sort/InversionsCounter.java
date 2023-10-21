package org.jrslanski.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InversionsCounter {

    record InversionsWithSortedList<T>(
            List<T> sortedList,
            int inversions
    ){}

    public <T> int countInversions(List<T> list, Comparator<T> compare){
        var result = countInversionsAndSort(list, compare);
        return result.inversions;
    }

    <T> InversionsWithSortedList<T> countInversionsAndSort(List<T> list, Comparator<T> compare){
        if(list.isEmpty()) return new InversionsWithSortedList<>(list, 0);
        if(list.size() == 1) return new InversionsWithSortedList<>(list, 0);
        var subListSize = list.size() / 2;
        var firstHalf = list.subList(0, subListSize);
        var secondHalf = list.subList(subListSize, list.size());
        var firstHalfListAndInversions = countInversionsAndSort(firstHalf, compare);
        var secondHalfListAndInversions = countInversionsAndSort(secondHalf, compare);
        var mergedListAndSplitInversions = mergeAndCountSplitInversions(
                firstHalfListAndInversions.sortedList,
                secondHalfListAndInversions.sortedList,
                compare);
        return new InversionsWithSortedList<>(
                mergedListAndSplitInversions.sortedList,
                firstHalfListAndInversions.inversions +
                        secondHalfListAndInversions.inversions +
                        mergedListAndSplitInversions.inversions
        );
    }

    <T> InversionsWithSortedList<T> mergeAndCountSplitInversions(List<T> firstList, List<T> secondList, Comparator<T> compare){
        int inversionsCount = 0;
        int i = 0;
        int j = 0;
        int totalSize = firstList.size() + secondList.size();
        List<T> newList = new ArrayList<>();
        for(int k=0; k <= totalSize - 1; k++){
            if(i == firstList.size()){
                for(int x = j; x <= secondList.size() - 1; x++){
                    newList.add(secondList.get(x));
                }
                break;
            }

            if(j == secondList.size()){
                for(int x = i; x <= firstList.size() - 1; x++){
                    newList.add(firstList.get(x));
                }
                break;
            }

            if(compare.compare(firstList.get(i), secondList.get(j)) <= 0){
                newList.add(firstList.get(i));
                i++;
            }else{
                newList.add(secondList.get(j));
                inversionsCount += firstList.size() - i;
                j++;
            }
        }
        return new InversionsWithSortedList<>(newList, inversionsCount);
    }



}
