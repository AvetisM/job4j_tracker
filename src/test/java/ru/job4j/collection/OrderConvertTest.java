package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrderConvertTest {
    @Test
    public void whenSingleOrder() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("35462", "Dress"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("35462"), is(new Order("35462", "Dress")));
    }

    @Test
    public void whenDuplicateKey() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("35462", "Dress"));
        orders.add(new Order("35462", "Pans"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.size(), is(1));
    }
}