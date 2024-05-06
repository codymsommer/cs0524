package cs0524.tool;

import cs0524.Configuration;
import cs0524.day.DayType;
import cs0524.day.holiday.AbsoluteHoliday;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ToolTypeTest {
    private static final ToolType hammer = new ToolType("Hammer", BigDecimal.ONE, BigDecimal.TWO, BigDecimal.valueOf(3));
    private static final LocalDate weekday = LocalDate.of(2024, 5, 3);
    private static final LocalDate weekend = LocalDate.of(2024, 5, 4);
    private static final LocalDate holiday = LocalDate.of(2024, 7, 4);

    @BeforeAll
    static void setUp() {
        Configuration config = Configuration.singleton();
        config.addHoliday(new AbsoluteHoliday("Independence Day", 4, Month.JULY));
    }

    @AfterAll
    static void tearDown() {
        Configuration.singleton().clear();
    }

    @Test
    void test_ToolType() {
        ToolType toolType = new ToolType("tool", "1");
        assertAll("never free",
                () -> assertEquals(BigDecimal.ONE, toolType.getRate(weekday)),
                () -> assertEquals(BigDecimal.ONE, toolType.getRate(weekend)),
                () -> assertEquals(BigDecimal.ONE, toolType.getRate(holiday))
        );
    }

    @Test
    void test_ToolType_freeOnWeekdays() {
        ToolType toolType = new ToolType("tool", "1", true, false, false);
        assertAll("free on weekdays",
                () -> assertEquals(BigDecimal.ZERO, toolType.getRate(weekday)),
                () -> assertEquals(BigDecimal.ONE, toolType.getRate(weekend)),
                () -> assertEquals(BigDecimal.ONE, toolType.getRate(holiday))
        );
    }

    /*
    TODO: If Independence Day falls on a Saturday, should that July 4th be considered a weekend or a holiday,
     per requirements, a holiday will never fall on the weekend since it is always observed on the closest weekday
     This could cause unexpected behavior in the scenario below where a tool is free on the weekend but not holidays
     */
    @Test
    void test_ToolType_freeOnWeekends() {
        ToolType toolType = new ToolType("tool", "1", false, true, false);
        assertAll("free on weekends",
                () -> assertEquals(BigDecimal.ONE, toolType.getRate(weekday)),
                () -> assertEquals(BigDecimal.ZERO, toolType.getRate(weekend)),
                () -> assertEquals(BigDecimal.ONE, toolType.getRate(holiday))
        );
    }

    @Test
    void test_ToolType_freeOnHolidays() {
        ToolType toolType = new ToolType("tool", "1", false, false, true);
        assertAll("free on holidays",
                () -> assertEquals(BigDecimal.ONE, toolType.getRate(weekday)),
                () -> assertEquals(BigDecimal.ONE, toolType.getRate(weekend)),
                () -> assertEquals(BigDecimal.ZERO, toolType.getRate(holiday))
        );
    }

    @Test
    void test_ToolType_missingWeekdayRate() {
        assertThrows(IllegalArgumentException.class, () -> new ToolType("tool", new EnumMap<>(Map.of(
                DayType.WEEKEND, BigDecimal.ONE,
                DayType.HOLIDAY, BigDecimal.ONE
        ))));
    }

    @Test
    void test_ToolType_missingWeekendRate() {
        assertThrows(IllegalArgumentException.class, () -> new ToolType("tool", new EnumMap<>(Map.of(
                DayType.WEEKDAY, BigDecimal.ONE,
                DayType.HOLIDAY, BigDecimal.ONE
        ))));
    }

    @Test
    void test_ToolType_missingHolidayRate() {
        assertThrows(IllegalArgumentException.class, () -> new ToolType("tool", new EnumMap<>(Map.of(
                DayType.WEEKDAY, BigDecimal.ONE,
                DayType.WEEKEND, BigDecimal.ONE
        ))));
    }

    @Test
    void test_getName() {
        assertEquals("Hammer", hammer.getName());
    }

    @Test
    void test_getRate_weekday() {
        assertEquals(BigDecimal.ONE, hammer.getRate(weekday));
    }

    @Test
    void test_getRate_weekend() {
        assertEquals(BigDecimal.TWO, hammer.getRate(weekend));
    }

    @Test
    void test_getRate_holiday() {
        assertEquals(BigDecimal.valueOf(3), hammer.getRate(holiday));
    }

    @Test
    void test_getRate_holidayObserved() {
        assertEquals(BigDecimal.valueOf(3), hammer.getRate(LocalDate.of(2027, 7, 5)));
    }

    /*
    TODO: Requirements unclear, need to inquire business
     if holiday is observed on the closest weekday, then the holiday rate does not apply on the weekend
     */
    @Test
    void test_getRate_weekendHoliday() {
        assertEquals(BigDecimal.TWO, hammer.getRate(LocalDate.of(2027, 7, 4)));
    }
}
