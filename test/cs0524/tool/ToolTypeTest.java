package cs0524.tool;

import cs0524.Configuration;
import cs0524.day.holiday.AbsoluteHoliday;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ToolTypeTest {
    private static final ToolType hammer = new ToolType("Hammer", 1, 2, 3);
    private static final LocalDate weekday = LocalDate.of(2024, 5,3);
    private static final LocalDate weekend = LocalDate.of(2024, 5,4);
    private static final LocalDate holiday = LocalDate.of(2024, 7,4);

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
        ToolType toolType = new ToolType("tool", 1);
        assertAll("never free",
                () -> assertEquals(1, toolType.getRate(weekday)),
                () -> assertEquals(1, toolType.getRate(weekend)),
                () -> assertEquals(1, toolType.getRate(holiday))
        );
    }

    @Test
    void test_ToolType_freeOnWeekdays() {
        ToolType toolType = new ToolType("tool", 1, true, false, false);
        assertAll("free on weekdays",
                () -> assertEquals(0, toolType.getRate(weekday)),
                () -> assertEquals(1, toolType.getRate(weekend)),
                () -> assertEquals(1, toolType.getRate(holiday))
        );
    }

    /*
    TODO: If Independence Day falls on a Saturday, should that July 4th be considered a weekend or a holiday,
    per requirements, a holiday will never fall on the weekend since it is always observed on the closest weekday
    This could cause unexpected behavior in the scenario below where a tool is free on the weekend but not holidays
     */
    @Test
    void test_ToolType_freeOnWeekends() {
        ToolType toolType = new ToolType("tool", 1, false, true, false);
        assertAll("free on weekends",
                () -> assertEquals(1, toolType.getRate(weekday)),
                () -> assertEquals(0, toolType.getRate(weekend)),
                () -> assertEquals(1, toolType.getRate(holiday))
        );
    }

    @Test
    void test_ToolType_freeOnHolidays() {
        ToolType toolType = new ToolType("tool", 1, false, false, true);
        assertAll("free on holidays",
                () -> assertEquals(1, toolType.getRate(weekday)),
                () -> assertEquals(1, toolType.getRate(weekend)),
                () -> assertEquals(0, toolType.getRate(holiday))
        );
    }

    @Test
    void test_getName() {
        assertEquals("Hammer", hammer.getName());
    }

    @Test
    void test_getRate_weekday() {
        assertEquals(1, hammer.getRate(weekday));
    }

    @Test
    void test_getRate_weekend() {
        assertEquals(2, hammer.getRate(weekend));
    }

    @Test
    void test_getRate_holiday() {
        assertEquals(3, hammer.getRate(holiday));
    }
}
