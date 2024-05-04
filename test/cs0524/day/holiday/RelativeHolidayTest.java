package cs0524.day.holiday;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class RelativeHolidayTest {
    private static final RelativeHoliday presidentsDay = new RelativeHoliday("Presidents' Day", Instance.THIRD, DayOfWeek.MONDAY, Month.FEBRUARY);
    private static final RelativeHoliday memorialDay = new RelativeHoliday("Memorial Day", Instance.LAST, DayOfWeek.MONDAY, Month.MAY);
    private static final RelativeHoliday laborDay = new RelativeHoliday("Labor Day", Instance.FIRST, DayOfWeek.MONDAY, Month.SEPTEMBER);
    private static final RelativeHoliday columbusDay = new RelativeHoliday("Columbus Day", Instance.SECOND, DayOfWeek.MONDAY, Month.OCTOBER);
    private static final RelativeHoliday thanksgiving = new RelativeHoliday("Thanksgiving", Instance.FOURTH, DayOfWeek.THURSDAY, Month.NOVEMBER);

    @Test
    public void test_RelativeHoliday() {
        assertEquals(laborDay, new RelativeHoliday("Labor Day", "FIRST", "MONDAY", "SEPTEMBER"));
    }

    @Test
    public void test_RelativeHoliday_caseInsensitive() {
        assertEquals(memorialDay, new RelativeHoliday("Memorial Day", "Last", "Monday", "May"));
        assertEquals(thanksgiving, new RelativeHoliday("Thanksgiving", "fourth", "thursday", "november"));
    }

    @Test
    public void test_RelativeHoliday_invalidInstance() {
        assertThrows(IllegalArgumentException.class,
                () -> new RelativeHoliday("Test Day", "1st", "SUNDAY", "JUNE"));
    }

    @Test
    public void test_RelativeHoliday_invalidDay() {
        assertThrows(IllegalArgumentException.class,
                () -> new RelativeHoliday("Test Day", "FIRST", "SUNDAE", "JUNE"));
    }

    @Test
    public void test_RelativeHoliday_invalidMonth() {
        assertThrows(IllegalArgumentException.class,
                () -> new RelativeHoliday("Test Day", "FIRST", "SUNDAY", "JUME"));
    }

    @Test
    void test_name() {
        assertEquals("Memorial Day", memorialDay.name());
        assertEquals("Labor Day", laborDay.name());
    }

    @Test
    void test_is_first() {
        assertTrue(laborDay.is(LocalDate.of(2023, 9, 4)));
        assertTrue(laborDay.is(LocalDate.of(2024, 9, 2)));
        assertTrue(laborDay.is(LocalDate.of(2025, 9, 1)));
        assertTrue(laborDay.is(LocalDate.of(2026, 9, 7)));
    }

    @Test
    void test_is_second() {
        assertTrue(columbusDay.is(LocalDate.of(2023, 10, 9)));
        assertTrue(columbusDay.is(LocalDate.of(2024, 10, 14)));
        assertTrue(columbusDay.is(LocalDate.of(2025, 10, 13)));
        assertTrue(columbusDay.is(LocalDate.of(2026, 10, 12)));
    }

    @Test
    void test_is_third() {
        assertTrue(presidentsDay.is(LocalDate.of(2023, 2, 20)));
        assertTrue(presidentsDay.is(LocalDate.of(2024, 2, 19)));
        assertTrue(presidentsDay.is(LocalDate.of(2025, 2, 17)));
        assertTrue(presidentsDay.is(LocalDate.of(2026, 2, 16)));
    }

    @Test
    void test_is_forth() {
        assertTrue(thanksgiving.is(LocalDate.of(2023, 11, 23)));
        assertTrue(thanksgiving.is(LocalDate.of(2024, 11, 28)));
        assertTrue(thanksgiving.is(LocalDate.of(2025, 11, 27)));
        assertTrue(thanksgiving.is(LocalDate.of(2026, 11, 26)));
    }

    @Test
    void test_is_last() {
        assertTrue(memorialDay.is(LocalDate.of(2023, 5, 29)));
        assertTrue(memorialDay.is(LocalDate.of(2024, 5, 27)));
        assertTrue(memorialDay.is(LocalDate.of(2025, 5, 26)));
        assertTrue(memorialDay.is(LocalDate.of(2026, 5, 25)));
    }

    @Test
    public void test_equals() {
        assertEquals(presidentsDay, new RelativeHoliday("Presidents' Day", Instance.THIRD, DayOfWeek.MONDAY, Month.FEBRUARY));
    }

    @Test
    public void test_equals_ignoreName() {
        assertEquals(thanksgiving, new RelativeHoliday("Thanksgiving Day", Instance.FOURTH, DayOfWeek.THURSDAY, Month.NOVEMBER));
    }

    @Test
    public void test_equals_false() {
        assertNotEquals(columbusDay, memorialDay);
        assertNotEquals(thanksgiving, new RelativeHoliday("Thanksgiving", Instance.LAST, DayOfWeek.THURSDAY, Month.NOVEMBER));
    }
}