package cs0524.tool;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import cs0524.day.DayType;

public class ToolType {
    private final String name;
    private final EnumMap<DayType, Double> rates;

    public ToolType(String name, double rate) {
        this(name, rate, false, false, false);
    }

    public ToolType(String name, double rate, boolean freeOnWeekdays, boolean freeOnWeekends, boolean freeOnHolidays) {
        this(name,
                freeOnWeekdays ? 0 : rate,
                freeOnWeekends ? 0 : rate,
                freeOnHolidays ? 0 : rate
        );
    }

    public ToolType(String name, double weekdayRate, double weekendRate, double holidayRate) {
        this(name, new EnumMap<>(Map.of(
                DayType.WEEKDAY, weekdayRate,
                DayType.WEEKEND, weekendRate,
                DayType.HOLIDAY, holidayRate
        )));
    }

    public ToolType(String name, EnumMap<DayType, Double> rates) {
        this.name = name;
        this.rates = rates;
    }

    public String getName() {
        return name;
    }

    public double getRate(LocalDate date) {
        return rates.get(DayType.from(date));
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ToolType toolType
                && name.equals(toolType.name) && rates == toolType.rates;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rates);
    }
}
