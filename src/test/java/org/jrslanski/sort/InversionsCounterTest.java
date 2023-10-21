package org.jrslanski.sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InversionsCounterTest {

    record Grade(
            String name,
            Integer score
    ){}
    @Test
    void test1(){
        var list = List.of(1,3,5,2,4,6);
        var inversionsCounter = new InversionsCounter();
        var result = inversionsCounter.countInversions(list, Comparator.comparingInt(Integer::intValue));
        assertEquals(3, result);
    }

    @Test
    void test2(){
        var list = List.of(
                new Grade("Melissa", 95),
                new Grade("Jorge", 50),
                new Grade("Alex", 43),
                new Grade("Lenny", 56)
        );
        var inversionsCounter = new InversionsCounter();
        var result = inversionsCounter.countInversions(list, Comparator.comparingInt(InversionsCounterTest.Grade::score));
        assertEquals(4, result);
    }

    @Test
    void test3(){
        var list = List.of(
                new Grade("Melissa", 95),
                new Grade("Jorge", 50),
                new Grade("Alex", 43),
                new Grade("Lenny", 56),
                new Grade("John", 91)
        );
        var inversionsCounter = new InversionsCounter();
        var result = inversionsCounter.countInversions(list, Comparator.comparingInt(InversionsCounterTest.Grade::score));
        assertEquals(5, result);
    }

    @Test
    void test4(){
        List<Grade> list = new ArrayList<>();
        var inversionsCounter = new InversionsCounter();
        var result = inversionsCounter.countInversions(list, Comparator.comparingInt(InversionsCounterTest.Grade::score));
        assertEquals(0, result);
    }
}
