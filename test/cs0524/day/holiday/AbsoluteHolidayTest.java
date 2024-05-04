package cs0524.day.holiday;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class AbsoluteHolidayTest {
    private static final AbsoluteHoliday newYearsDay = new AbsoluteHoliday("New Year's Day", 1, Month.JANUARY);
    private static final AbsoluteHoliday independenceDay = new AbsoluteHoliday("Independence Day", 4, Month.JULY);
    private static final AbsoluteHoliday christmas = new AbsoluteHoliday("Christmas Day", 25, Month.DECEMBER);

    @Test
    public void test_AbsoluteHoliday() {
        assertEquals(newYearsDay, new AbsoluteHoliday("New Year's Day", 1, "JANUARY"));
    }

    @Test
    public void test_AbsoluteHoliday_caseInsensitive() {
        assertEquals(independenceDay, new AbsoluteHoliday("Independence Day", 4, "July"));
        assertEquals(christmas, new AbsoluteHoliday("Christmas Day", 25, "december"));
    }

    @Test
    public void test_AbsoluteHoliday_invalidDay() {
        assertThrows(IllegalArgumentException.class,
                () -> new AbsoluteHoliday("Day 0", 0, "MAY"));
        assertThrows(IllegalArgumentException.class,
                () -> new AbsoluteHoliday("Negative Day", -1, "MAY"));
        assertThrows(IllegalArgumentException.class,
                () -> new AbsoluteHoliday("February 30th", 30, "FEBRUARY"));
        assertThrows(IllegalArgumentException.class,
                () -> new AbsoluteHoliday("April 31st", 31, "APRIL"));
    }

    @Test
    public void test_AbsoluteHoliday_invalidMonth() {
        assertThrows(IllegalArgumentException.class,
                () -> new AbsoluteHoliday("Star Wars Day", 4, "MEH"));
    }

    @Test
    public void test_name() {
        assertEquals("New Year's Day", newYearsDay.name());
        assertEquals("Independence Day", independenceDay.name());
    }

    @Test
    public void test_is() {
        assertTrue(independenceDay.is(LocalDate.of(2024, 7, 4)));
        assertTrue(christmas.is(LocalDate.of(2023, 12, 25)));
        assertTrue(newYearsDay.is(LocalDate.of(2030, 1, 1)));
    }

    @Test
    public void test_is_false() {
        assertFalse(independenceDay.is(LocalDate.of(2022, 4, 7)));
        assertFalse(christmas.is(LocalDate.of(2024, 1, 1)));
    }

    @Test
    public void test_isObserved() {
        assertAll("Holiday on Saturday, observed the Friday before",
                () -> assertTrue(independenceDay.isObserved(LocalDate.of(2020, 7, 3))),
                () -> assertFalse(independenceDay.isObserved(LocalDate.of(2020, 7, 4))),
                () -> assertFalse(independenceDay.isObserved(LocalDate.of(2020, 7, 5))),
                () -> assertFalse(independenceDay.isObserved(LocalDate.of(2020, 7, 6)))
        );
        assertAll("Holiday on Sunday, observed the Sunday after",
                () -> assertFalse(independenceDay.isObserved(LocalDate.of(2021, 7, 2))),
                () -> assertFalse(independenceDay.isObserved(LocalDate.of(2021, 7, 3))),
                () -> assertFalse(independenceDay.isObserved(LocalDate.of(2021, 7, 4))),
                () -> assertTrue(independenceDay.isObserved(LocalDate.of(2021, 7, 5)))
        );
    }

    @Test
    public void test_isObserved_holidayNotOnWeekend() {
        assertAll("Holiday on Friday",
                () -> assertFalse(independenceDay.isObserved(LocalDate.of(2019, 7, 3))),
                () -> assertTrue(independenceDay.isObserved(LocalDate.of(2019, 7, 4))),
                () -> assertFalse(independenceDay.isObserved(LocalDate.of(2019, 7, 5)))
        );
        assertAll("Holiday on Monday",
                () -> assertFalse(independenceDay.isObserved(LocalDate.of(2022, 7, 3))),
                () -> assertTrue(independenceDay.isObserved(LocalDate.of(2022, 7, 4))),
                () -> assertFalse(independenceDay.isObserved(LocalDate.of(2022, 7, 5)))
        );
    }

    @Test
    public void test_isDayAfter() {
        assertTrue(independenceDay.isDayAfter(LocalDate.of(2024, 7, 3)));
    }

    @Test
    public void test_isDayAfter_differentMonth() {
        assertTrue(newYearsDay.isDayAfter(LocalDate.of(2023, 12, 31)));
        assertTrue(newYearsDay.isDayAfter(LocalDate.of(2022, 12, 31)));
    }

    @Test
    public void test_isDayAfter_false() {
        assertFalse(independenceDay.isDayAfter(LocalDate.of(2024, 7, 4)));
        assertFalse(independenceDay.isDayAfter(LocalDate.of(2024, 7, 5)));
    }

    @Test
    public void test_isDayBefore() {
        assertTrue(independenceDay.isDayBefore(LocalDate.of(2024, 7, 5)));
    }

    @Test
    public void test_isDayBefore_differentMonth() {
        AbsoluteHoliday newYearsEve = new AbsoluteHoliday("New Year's Eve", 31, Month.DECEMBER);
        assertTrue(newYearsEve.isDayBefore(LocalDate.of(2024, 1, 1)));
        assertTrue(newYearsEve.isDayBefore(LocalDate.of(2023, 1, 1)));
    }

    @Test
    public void test_isDayBefore_false() {
        assertFalse(independenceDay.isDayBefore(LocalDate.of(2024, 7, 3)));
        assertFalse(independenceDay.isDayBefore(LocalDate.of(2024, 7, 4)));
    }

    @Test
    public void test_equals() {
        assertEquals(newYearsDay, new AbsoluteHoliday("New Year's Day", 1, Month.JANUARY));
    }

    @Test
    public void test_equals_ignoreName() {
        assertEquals(newYearsDay, new AbsoluteHoliday("New Years", 1, Month.JANUARY));
    }

    @Test
    public void test_equals_false() {
        assertNotEquals(christmas, newYearsDay);
        assertNotEquals(independenceDay, new AbsoluteHoliday("Independence Day", 1, Month.JULY));
    }
}
