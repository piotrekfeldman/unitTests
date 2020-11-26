package junit5exercisesTests;

import junit5exercisesClasses.Coordinates;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.interactions.Coordinates;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {


    @Test
    void constructorShouldFailIfAnyValueAbove100() {

        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(101, 0));
    }

    @Test
    void constructorShouldFailIfAnyValueBelow0() {

        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-1, 0));
        //assertDoesNotThrow(() -> new Coordinates(-1, 0));
    }

    @Test
    void copyShouldReturnNewObject() {
        //given
        Coordinates coordinates = new Coordinates(10,10);


        //when
        Coordinates copy = Coordinates.copy(coordinates, 0, 0);

        //then
        assertThat(copy, not(sameInstance(coordinates)));
        assertThat(copy, equalTo(coordinates));
    }

    @Test
    void copyShouldReturnAddCoordinates() {
        //given
        Coordinates coordinates = new Coordinates(10,10);

        //when
        Coordinates copy = Coordinates.copy(coordinates, 5, 6);

        //then
        assertThat(copy.getX(), equalTo(15));
        assertThat(copy.getY(), equalTo(16));

    }


}