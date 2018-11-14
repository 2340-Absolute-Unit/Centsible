package edu.gatech.cs2340.centsible;

import org.junit.Test;

import java.lang.reflect.Field;

import edu.gatech.cs2340.centsible.model.Location;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testLocationSetName() throws NoSuchFieldException, IllegalAccessException {
    final Location locationName = new Location();
    locationName.setName("Wally World");
    final Field field = locationName.getClass().getDeclaredField("name");
    field.setAccessible(true);
    assertEquals("Fields didn't match", field.get(locationName), "Wally World");
    }
}