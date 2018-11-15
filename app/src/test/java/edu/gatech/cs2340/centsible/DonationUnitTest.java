package edu.gatech.cs2340.centsible;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

import edu.gatech.cs2340.centsible.model.Donation;
import edu.gatech.cs2340.centsible.model.Location;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DonationUnitTest {
    Donation donation;
    Date currentDate;

    @Before
    public void setUp() {
        currentDate = new Date();
        donation = new Donation("1", "Buzz", "A GT Mascot",
                "The best Mascot out there!", 1000.0,
                "Mascot", "vvjeieervjhfgeifjr", currentDate);
    }

    @Test
    public void testDonationGetName() {
        assertEquals("Fields didn't match", donation.getName(), "Buzz");
    }

    @Test
    public void testDonationGetLocation() {
        assertEquals("1", donation.getLocation());
    }

    @Test
    public void testDonationGetShortDescription() {
        assertEquals("A GT Mascot", donation.getShortDescription());
    }

    @Test
    public void testDonationGetLongDescription() {
        assertEquals("The best Mascot out there!", donation.getLongDescription());
    }

    @Test
    public void testDonationGetValue() {
        assertEquals(1000.0, donation.getValue(), 0);
    }

    @Test
    public void testDonationGetCategory() {
        assertEquals("Mascot", donation.getCategory());
    }

    @Test
    public void testDonationGetEnteredBy() {
        assertEquals("vvjeieervjhfgeifjr", donation.getEnteredBy());
    }

    @Test
    public void testDonationGetLastUpdated() {
        assertEquals(currentDate, donation.getLastUpdated());
    }

}