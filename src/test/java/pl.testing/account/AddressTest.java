package pl.testing.account;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Fabryczna, 10", "Armii Krajowej, 57/11", "'Romka, Sromka, Atomka', 5"})
    void givenAdressesShouldNotBeEMptyAndHaveProperNames(String street, String number) {
        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(6));

        System.out.println(street + " " + number);


    }

    @ParameterizedTest
    @CsvFileSource(resources = "/examplescsv.csv")
    void givenAdressesShouldNotBeEMptyAndHaveProperNamesCsv(String street, String number) {
        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(6));
        System.out.println(street + " chuj " + number);


    }



}