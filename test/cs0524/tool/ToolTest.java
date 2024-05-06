package cs0524.tool;

import cs0524.Configuration;
import cs0524.day.holiday.AbsoluteHoliday;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToolTest {
    private static Tool tool;
    private static ToolType miter;

    @BeforeAll
    static void setUp() {
        Configuration config = Configuration.singleton();
        config.addHoliday(new AbsoluteHoliday("Independence Day", 4, Month.JULY));
        miter = new ToolType("Miter Saw", "10");
        config.addToolType(miter);
        tool = new Tool("MTRM", miter, "Milwaukee");
    }

    @AfterAll
    static void tearDown() {
        Configuration.singleton().clear();
    }

    @Test
    void test_Tool() {
        assertEquals(miter, new Tool("MTRD", "Miter Saw", "DeWalt").getType());
    }

    @Test
    void test_Tool_invalidType() {
        assertThrows(IllegalArgumentException.class, () -> new Tool("MTRD", "Miter", "DeWalt"));
    }

    @Test
    void test_getCode() {
        assertEquals("MTRM", tool.getCode());
    }

    @Test
    void test_getType() {
        assertEquals("Miter Saw", tool.getType().getName());
    }

    @Test
    void test_getBrand() {
        assertEquals("Milwaukee", tool.getBrand());
    }

    @Test
    void test_getRate() {
        assertEquals(BigDecimal.TEN, tool.getRate(LocalDate.of(2024, 5, 4)));
    }
}