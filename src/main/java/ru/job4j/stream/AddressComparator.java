package ru.job4j.stream;

import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {
    @Override
    public int compare(Address o1, Address o2) {
        return o1.getCiy().compareTo(o2.getCiy());
    }
}
