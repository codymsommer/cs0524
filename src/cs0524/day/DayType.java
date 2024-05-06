package cs0524.day;

import cs0524.Configuration;
import cs0524.day.holiday.Holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum DayType {
    WEEKDAY, WEEKEND, HOLIDAY;

    public static DayType from(LocalDate date) {
        for (Holiday holiday : Configuration.singleton().getHolidays()) {
            if (holiday.isObserved(date)) {
                return HOLIDAY;
            }
        }
        return switch (DayOfWeek.from(date)) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> WEEKDAY;
            case SATURDAY, SUNDAY -> WEEKEND;
        };
    }
}
