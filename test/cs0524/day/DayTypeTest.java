package cs0524.day;

import cs0524.Configuration;
import cs0524.day.holiday.AbsoluteHoliday;
import cs0524.day.holiday.Instance;
import cs0524.day.holiday.RelativeHoliday;
import org.junit.jupiter.api.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DayTypeTest {

    @BeforeAll
    static void setUp() {
        Configuration config = Configuration.singleton();
        config.addHoliday(new RelativeHoliday("Labor Day", Instance.FIRST, DayOfWeek.MONDAY, Month.SEPTEMBER));
        config.addHoliday(new AbsoluteHoliday("Independence Day", 4, Month.JULY));
    }

    @AfterAll
    static void tearDown() {
        Configuration.singleton().clear();
    }

    @Test
    void test_from_holiday() {
        assertEquals(DayType.HOLIDAY, DayType.from(LocalDate.of(2024, 9, 2)));
        assertEquals(DayType.HOLIDAY, DayType.from(LocalDate.of(2024, 7, 4)));
    }

    @Test
    void test_from_weekday() {
        assertEquals(DayType.WEEKDAY, DayType.from(LocalDate.of(2024, 5, 3)));
        assertEquals(DayType.WEEKDAY, DayType.from(LocalDate.of(2025, 8, 19)));
    }

    @Test
    void test_from_weekend() {
        assertEquals(DayType.WEEKEND, DayType.from(LocalDate.of(2024, 5, 4)));
        assertEquals(DayType.WEEKEND, DayType.from(LocalDate.of(2024, 5, 5)));
    }
}
