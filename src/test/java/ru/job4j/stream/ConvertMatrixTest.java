package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConvertMatrixTest {

    @Test
    public void whenConvertMatrixToList() {

        Integer[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };

        ConvertMatrix cm = new ConvertMatrix();
        List<Integer> rls = cm.convert(matrix);
        List<Integer> exp = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(rls, is(exp));
    }
}