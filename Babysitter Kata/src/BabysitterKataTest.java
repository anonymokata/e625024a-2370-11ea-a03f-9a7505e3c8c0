import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BabysitterKataTest {

    private BabysitterKata babysitterKata;

    @Before
    public void setUp(){ babysitterKata = new BabysitterKata(); }

    // Testing partial hours (applicable to all families)
    @Test
    public void NoPartialHours() {
        assertEquals("$60", babysitterKata.sitterPay("12:06", "AM", "3:30", "AM", 'A')); // A
        assertEquals("$135", babysitterKata.sitterPay("6:04", "PM", "2:16", "AM", 'A'));
        assertEquals("$44", babysitterKata.sitterPay("9:41", "PM", "1:03", "AM", 'B')); // B
        assertEquals("$124", babysitterKata.sitterPay("5:22", "PM", "3:45", "AM", 'B'));
        assertEquals("$117", babysitterKata.sitterPay("7:55", "PM", "2:01", "AM", 'C')); // C
        assertEquals("$129", babysitterKata.sitterPay("5:26", "PM", "12:01", "AM", 'C'));
    }

    // Testing boundaries (applicable to all families)
    @Test
    public void StartTimeBeforeFivePM() {
        assertEquals("Enter a valid start time", babysitterKata.sitterPay("4:00","PM","12:00", "AM", 'A'));
    }
    @Test
    public void EndTimeAfterFourAM() {
        assertEquals("Enter a valid end time", babysitterKata.sitterPay("5:00","PM","5:00", "AM", 'B'));
    }
    @Test
    public void StartTimeIsBeforeEndTime() { // AM, PM and both
        assertEquals("Invalid: End time is before start time", babysitterKata.sitterPay("2:00", "AM", "12:00", "AM", 'A'));
        assertEquals("Invalid: End time is before start time", babysitterKata.sitterPay("11:00", "PM", "5:00", "PM", 'B'));
        assertEquals("Invalid: End time is before start time", babysitterKata.sitterPay("4:00", "AM", "5:00", "PM", 'C'));
    }

    // Family A tests
    @Test
    public void HoursBeforeElevenPMPayFifteenDollars() {
        assertEquals("$90", babysitterKata.sitterPay("5:00", "PM", "11:00", "PM", 'A'));
        assertEquals("$75", babysitterKata.sitterPay("6:00", "PM", "11:00", "PM", 'A'));
        assertEquals("$15", babysitterKata.sitterPay("8:00", "PM", "9:00", "PM", 'A'));
    }
    @Test
    public void HoursAfterElevenPMPayTwentyDollars() {
        assertEquals("$100", babysitterKata.sitterPay("11:00", "PM", "4:00", "AM", 'A'));
        assertEquals("$60", babysitterKata.sitterPay("12:00", "AM", "3:00", "AM", 'A'));
        assertEquals("$20", babysitterKata.sitterPay("1:00", "AM", "2:00", "AM", 'A'));
    }

    // Family B tests
    @Test
    public void HoursBeforeTenPMPayTwelveDollars() {
        assertEquals("$60", babysitterKata.sitterPay("5:00", "PM", "10:00", "PM", 'B'));
        assertEquals("$36", babysitterKata.sitterPay("6:00", "PM", "9:00", "PM", 'B'));
        assertEquals("$12", babysitterKata.sitterPay("8:00", "PM", "9:00", "PM", 'B'));
    }
    @Test
    public void HoursBetweenTenAndTwelvePayEightDollars() {
        assertEquals("$16", babysitterKata.sitterPay("10:00", "PM", "12:00", "AM", 'B'));
        assertEquals("$8", babysitterKata.sitterPay("10:00", "PM", "11:00", "PM", 'B'));
        assertEquals("$8", babysitterKata.sitterPay("11:00", "PM", "12:00", "AM", 'B'));
    }
    @Test
    public void HoursAfterTwelveAMPaySixteenDollars() {
        assertEquals("$64", babysitterKata.sitterPay("12:00", "AM", "4:00", "AM", 'B'));
        assertEquals("$32", babysitterKata.sitterPay("1:00", "AM", "3:00", "AM", 'B'));
        assertEquals("$16", babysitterKata.sitterPay("3:00", "AM", "4:00", "AM", 'B'));
    }

    // Family C tests
    @Test
    public void HoursBeforeNinePMPayTwentyOneDollars() {
        assertEquals("$84", babysitterKata.sitterPay("5:00", "PM", "9:00", "PM", 'C'));
        assertEquals("$42", babysitterKata.sitterPay("6:00", "PM", "8:00", "PM", 'C'));
        assertEquals("$21", babysitterKata.sitterPay("5:00", "PM", "6:00", "PM", 'C'));
    }
    @Test
    public void HoursAfterNinePMPayFifteenDollars() {
        assertEquals("$105", babysitterKata.sitterPay("9:00", "PM", "4:00", "AM", 'C'));
        assertEquals("$30", babysitterKata.sitterPay("12:00", "AM", "2:00", "AM", 'C'));
        assertEquals("$15", babysitterKata.sitterPay("9:00", "PM", "10:00", "PM", 'C'));
    }
}
