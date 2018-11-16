package edu.gatech.cs2340.centsible;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import edu.gatech.cs2340.centsible.model.Location;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LocationUnitTest {
    Location location;
    Date currentDate;

    @Before
    public void setUp() {
        currentDate = new Date();
        location = new Location();
        location.setKey("1");
        location.setName("AFD Station 4");
        location.setLatitude("33.75416");
        location.setLongitude("-84.37742");
        location.setStAddress("309 EDGEWOOD AVE SE");
        location.setCity("Atlanta");
        location.setState("GA");
        location.setZip("30332");
        location.setType("Drop Off");
        location.setPhone("(404) 555 - 3456");
        location.setWebsite("www.afd04.atl.ga");
    }

    @Test
    public void testLocationGetKey() {
        assertEquals("Fields didn't match", location.getKey(), "1");
    }

    @Test
    public void testLocationGetName() {
        assertEquals("AFD Station 4", location.getName());
    }

    @Test
    public void testLocationGetLatitude() {
        assertEquals("33.75416", location.getLatitude());
    }

    @Test
    public void testLocationGetLongitude() {
        assertEquals("-84.37742", location.getLongitude());
    }

    @Test
    public void testLocationGetStAddress() {
        assertEquals("309 EDGEWOOD AVE SE", location.getStAddress());
    }

    @Test
    public void testLocationGetCity() {

        assertEquals("Atlanta", location.getCity());
    }

    @Test
    public void testLocationGetState() {
        assertEquals("GA", location.getState());
    }

    @Test
    public void testLocationGetZip() {
        assertEquals("30332", location.getZip());
    }

    @Test
    public void testLocationGetType() {
        assertEquals("Drop Off", location.getType());
    }

    @Test
    public void testLocationGetPhone() {
        assertEquals("(404) 555 - 3456", location.getPhone());
    }

    @Test
    public void testLocationGetWebsite() {
        assertEquals("www.afd04.atl.ga", location.getWebsite());
    }

}