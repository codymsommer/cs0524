package cs0524.day.holiday;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

class HolidayTest {

    @Test
    void isObserved() {
        new RelativeHoliday("Labor Day", Instance.FIRST, DayOfWeek.MONDAY, Month.OCTOBER)
                .isObserved(LocalDate.of(2024, 9, 2));
    }
}