package cs0524;

import cs0524.day.holiday.AbsoluteHoliday;
import cs0524.day.holiday.Instance;
import cs0524.day.holiday.RelativeHoliday;
import cs0524.tool.Tool;
import cs0524.tool.ToolType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @BeforeAll
    static void setUp() {
        Configuration config = Configuration.singleton();
        config.addHoliday(new AbsoluteHoliday("Independence Day", 4, Month.JULY));
        config.addHoliday(new RelativeHoliday("Labor Day", Instance.FIRST, DayOfWeek.MONDAY, Month.SEPTEMBER));

        config.addToolType(new ToolType("Ladder", "1.99", false, false, true));
        config.addToolType(new ToolType("Chainsaw", "1.49", false, true, false));
        config.addToolType(new ToolType("Jackhammer", "2.99", false, true, true));

        config.addTool(new Tool("CHNS", "Chainsaw", "Stihl"));
        config.addTool(new Tool("LADW", "Ladder", "Werner"));
        config.addTool(new Tool("JAKD", "Jackhammer", "DeWalt"));
        config.addTool(new Tool("JAKR", "Jackhammer", "Ridgid"));
    }

    @AfterAll
    static void tearDown() {
        Configuration.singleton().clear();
    }

    @Test
    void test1() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Rental("JAKR", "2015-09-03", 5, 101));
        assertEquals("Discount must not be greater than 100%", exception.getMessage());
    }

    @Test
    void test2() {
        Rental rental = new Rental("LADW", "2020-07-02", 3, 10);
        rental.printRentalAgreement();
        assertEquals(new BigDecimal("3.58"), rental.calculateFinalTotal());
    }

    @Test
    void test3() {
        Rental rental = new Rental("CHNS", "2015-07-02", 5, 25);
        rental.printRentalAgreement();
        assertEquals(new BigDecimal("3.35"), rental.calculateFinalTotal());
    }

    @Test
    void test4() {
        Rental rental = new Rental("JAKD", "2015-09-03", 6, 0);
        rental.printRentalAgreement();
        assertEquals(new BigDecimal("8.97"), rental.calculateFinalTotal());
    }

    @Test
    void test5() {
        Rental rental = new Rental("JAKR", "2015-07-02", 9, 0);
        rental.printRentalAgreement();
        assertEquals(new BigDecimal("14.95"), rental.calculateFinalTotal());
    }

    @Test
    void test6() {
        Rental rental = new Rental("JAKR", "2020-07-02", 4, 50);
        rental.printRentalAgreement();
        assertEquals(new BigDecimal("1.49"), rental.calculateFinalTotal());
    }
}
