package cs0524.day.holiday;

import java.time.LocalDate;
import java.time.Month;

public enum Instance {
    FIRST(0),
    SECOND(1),
    THIRD(2),
    FOURTH(3),
    LAST(-1);

    final int index;

    Instance(int index) {
        this.index = index;
    }

    public boolean is(LocalDate date) {
        return switch (this) {
            case FIRST, SECOND, THIRD, FOURTH -> index == (date.getDayOfMonth() - 1) / 7;
            case LAST -> Month.from(date) != Month.from(date.plusDays(7));
        };
    }
}
