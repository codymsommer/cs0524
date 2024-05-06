package cs0524.tool;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import cs0524.day.DayType;

public class ToolType {
    private final String name;
    private final EnumMap<DayType, BigDecimal> rates;

    public ToolType(String name, String rate) {
        this(name, rate, false, false, false);
    }

    public ToolType(String name, String rate, boolean freeOnWeekdays, boolean freeOnWeekends, boolean freeOnHolidays) {
        this(name,
                freeOnWeekdays ? BigDecimal.ZERO : new BigDecimal(rate),
                freeOnWeekends ? BigDecimal.ZERO : new BigDecimal(rate),
                freeOnHolidays ? BigDecimal.ZERO : new BigDecimal(rate)
        );
    }

    public ToolType(String name, BigDecimal weekdayRate, BigDecimal weekendRate, BigDecimal holidayRate) {
        this(name, new EnumMap<>(Map.of(
                DayType.WEEKDAY, weekdayRate,
                DayType.WEEKEND, weekendRate,
                DayType.HOLIDAY, holidayRate
        )));
    }

    public ToolType(String name, EnumMap<DayType, BigDecimal> rates) {
        this.name = name;
        for (DayType dayType : DayType.values()) {
            if (!rates.containsKey(dayType)) {
                throw new IllegalArgumentException("Missing rate for " + dayType);
            }
        }
        this.rates = rates;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRate(LocalDate date) {
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
