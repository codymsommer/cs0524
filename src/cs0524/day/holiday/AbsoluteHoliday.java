package cs0524.day.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class AbsoluteHoliday implements Holiday {
    private final String name;
    private final int dayOfMonth;
    private final Month month;

    public AbsoluteHoliday(String name, int dayOfMonth, String month) {
        this(name, dayOfMonth, Month.valueOf(month.toUpperCase()));
    }

    public AbsoluteHoliday(String name, int dayOfMonth, Month month) {
        this.name = name;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean is(LocalDate date) {
        return Month.from(date) == month
                && date.getDayOfMonth() == dayOfMonth;
    }

    @Override
    public boolean isObserved(LocalDate date) {
        return switch (DayOfWeek.from(date)) {
            case TUESDAY, WEDNESDAY, THURSDAY -> is(date);
            case MONDAY -> is(date) || isDayBefore(date);
            case FRIDAY -> is(date) || isDayAfter(date);
            case SUNDAY, SATURDAY -> false;
        };
    }

    boolean isDayAfter(LocalDate date) {
        return is(date.plusDays(1));
    }

    boolean isDayBefore(LocalDate date) {
        return is(date.minusDays(1));
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof AbsoluteHoliday holiday
                && dayOfMonth == holiday.dayOfMonth && month == holiday.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfMonth, month);
    }
}
