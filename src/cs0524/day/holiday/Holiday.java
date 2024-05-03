package cs0524.day.holiday;

import java.time.LocalDate;

public interface Holiday {
    String name();

    boolean is(LocalDate date);

    default boolean isObserved(LocalDate date) {
        return is(date);
    }
}
