package cs0524.day.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class RelativeHoliday implements Holiday {
    private final String name;
    private final Instance instance;
    private final DayOfWeek dayOfWeek;
    private final Month month;

    public RelativeHoliday(String name, String instance, String dayOfWeek, String month) {
        this(name, Instance.valueOf(instance.toUpperCase()),
                DayOfWeek.valueOf(dayOfWeek.toUpperCase()), Month.valueOf(month.toUpperCase()));
    }

    public RelativeHoliday(String name, Instance instance, DayOfWeek dayOfWeek, Month month) {
        this.name = name;
        this.instance = instance;
        this.dayOfWeek = dayOfWeek;
        this.month = month;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean is(LocalDate date) {
        return Month.from(date) == month
                && DayOfWeek.from(date) == dayOfWeek
                && instance.is(date);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RelativeHoliday holiday
                && instance == holiday.instance && dayOfWeek == holiday.dayOfWeek && month == holiday.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(instance, dayOfWeek, month);
    }
}
