package org.jrslanski.sort;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeSortTest {

    record Grade(
            String name,
            Integer score
    ){}
    @Test
    void firstTest(){
        var list = List.of(
                new Grade("Melissa", 95),
                new Grade("Jorge", 50),
                new Grade("Alex", 43),
                new Grade("Lenny", 56)
        );
        var result = MergeSort.sort(list, Comparator.comparingInt(studentA -> studentA.score));
        assertEquals(result.get(0).name, "Alex");
        assertEquals(result.get(1).name, "Jorge");
        assertEquals(result.get(2).name, "Lenny");
        assertEquals(result.get(3).name, "Melissa");
    }

    @Test
    void secondTest(){
        var list = List.of(
                new Grade("Melissa", 95),
                new Grade("Jorge", 50),
                new Grade("Alex", 43),
                new Grade("Lenny", 56),
                new Grade("John", 91)
        );
        var result = MergeSort.sort(list, Comparator.comparingInt(studentA -> studentA.score));
        assertEquals(result.get(0).name, "Alex");
        assertEquals(result.get(1).name, "Jorge");
        assertEquals(result.get(2).name, "Lenny");
        assertEquals(result.get(3).name, "John");
        assertEquals(result.get(4).name, "Melissa");
    }

    @Test
    void thirdTest(){
        List<Grade> list = List.of();
        var result = MergeSort.sort(list, Comparator.comparingInt(studentA -> studentA.score));
        assertEquals(0, result.size());
    }

}
