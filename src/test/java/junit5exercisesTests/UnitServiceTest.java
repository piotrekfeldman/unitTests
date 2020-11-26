package junit5exercisesTests;

import junit5exercisesClasses.*;
import org.apache.poi.ss.formula.functions.T;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UnitServiceTest {

    @InjectMocks
    private UnitService unitService;

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private UnitRepository unitRepository;

    @Test
    void addedCargoShouldBeLoadedOnUnit () {

        //given
        Cargo cargo = new Cargo("vegetables",5);
        Unit unit = new Unit(new Coordinates(0,0),10,10);

        given(cargoRepository.findCargoByName("vegetables")).willReturn(Optional.of(cargo));

        //when
        unitService.addCargoByName(unit, "vegetables");

        //then
        verify(cargoRepository).findCargoByName("vegetables");
        assertThat(unit.getLoad(), is(5));
        assertThat(unit.getCargo().get(0), equalTo(cargo));

    }


    @Test
     void shouldThrowExceptionIfNoCargoIsFoundToAdd(){

        //given
       // Cargo cargo = new Cargo("chuj");
        given(cargoRepository.findCargoByName("vegetables")).willReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, ()-> unitService.addCargoByName((new Unit(new Coordinates(0,0),10,10)), "vegetables"));


    }

    @Test
    void shouldReturnUnitByCoordinates() {

        //given
        Unit unit = new Unit(new Coordinates(0,0),10,10);

        given(unitRepository.getUnitByCoordinates(new Coordinates(0,0))).willReturn(unit);

        //when
        Unit unitResult = unitService.getUnitOn(new Coordinates(0,0));

        //then
        assertThat(unitResult, sameInstance(unit));

    }

    @Test
    void shouldThrowExceptionIfUnitNotFound() {
        //given

        given(unitRepository.getUnitByCoordinates(new Coordinates(0,0))).willReturn(null);


        //when
        //then
        assertThrows(NoSuchElementException.class, ()-> unitService.getUnitOn(new Coordinates(0,0)));


    }




}
