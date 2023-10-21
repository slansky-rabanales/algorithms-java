package org.jrslanski.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    public static <T> List<T> sort(List<T> list, Comparator<T> compare){
        if(list.isEmpty()) return list;
        if(list.size() == 1) return list;
        var subListSize = list.size() / 2;
        var firstHalf = list.subList(0, subListSize);
        var secondHalf = list.subList(subListSize, list.size());
        var firstHalfSorted = sort(firstHalf, compare);
        var secondHalfSorted = sort(secondHalf, compare);
        return merge(firstHalfSorted, secondHalfSorted, compare);
    }

    private static <T> List<T> merge(List<T> firstList, List<T> secondList, Comparator<T> compare){
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
                j++;
            }
        }
        return newList;
    }
}
