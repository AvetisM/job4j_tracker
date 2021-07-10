package ru.job4j.stream;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void getListOfAddresses() {

        Address address1 = new Address("Пятигорск", "Никольская", 4, 18);
        Address address2 = new Address("Москва", "Никольская", 4, 18);
        Address address3 = new Address("Тверь", "Московская", 7, 12);
        Address address4 = new Address("Москва", "Никольская", 4, 18);

        List<Address> expected = Arrays.asList(
                address2,
                address1,
                address3

        );

        List<Profile> profiles = Arrays.asList(
                new Profile(address1),
                new Profile(address2),
                new Profile(address3),
                new Profile(address4)

        );

        Profiles prof = new Profiles();
        List<Address> result = prof.collect(profiles);

        assertThat(result, is(expected));
    }
}