package cs0524;

import cs0524.day.holiday.AbsoluteHoliday;
import cs0524.tool.Tool;
import cs0524.tool.ToolType;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {
    private static Rental rental;

    @BeforeAll
    static void setUp() {
        Configuration config = Configuration.singleton();
        config.addHoliday(new AbsoluteHoliday("Independence Day", 4, Month.JULY));
        ToolType trencher = new ToolType("Trencher", 100, false, true, true);
        config.addToolType(trencher);
        Tool braveTrencher = new Tool("TRNC", trencher, "Brave");
        config.addTool(braveTrencher);
        rental = new Rental(braveTrencher, LocalDate.of(2024, 7, 1), 7, 25);
    }

    @AfterAll
    static void tearDown() {
        Configuration.singleton().clear();
    }

    @Test
    void test_Rental() {
        assertEquals(rental, new Rental("TRNC", "2024-07-01", 7, 25));
    }

    @Test
    void test_Rental_invalidTool() {
        assertThrows(IllegalArgumentException.class,
                () -> new Rental("TRENCHER", "2024-07-01", 7, 25));
    }

    @Test
    void test_Rental_invalidDate() {
        assertThrows(DateTimeParseException.class,
                () -> new Rental("TRNC", "2024-06-31", 7, 25));
    }

    @Test
    void test_Rental_invalidNumberOfDays() {
        assertThrows(IllegalArgumentException.class,
                () -> new Rental("TRNC", "2024-07-01", 0, 25));
        assertThrows(IllegalArgumentException.class,
                () -> new Rental("TRNC", "2024-07-01", -1, 25));
    }

    @Test
    void test_Rental_invalidDiscount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Rental("TRNC", "2024-07-01", 7, -1));
        assertThrows(IllegalArgumentException.class,
                () -> new Rental("TRNC", "2024-07-01", 7, 101));
    }

    @Test
    void test_getTool() {
    }

    @Test
    void test_getCheckoutDate() {
        assertEquals("2024-07-01", rental.getCheckoutDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Test
    void test_getDuration() {
        assertEquals(7, rental.getDuration());
    }

    @Test
    void test_getDiscount() {
        assertEquals(25, rental.getDiscount());
        assertEquals(0, new Rental("TRNC", "2024-07-01", 7, 0).getDiscount());
    }

    /*
    TODO: Requirements unclear, "Charge days" specifies to not exclude the checkout day
    Therefore, we will not include the checkout day as one of the rental days
     */
    @Test
    void test_getDueDate() {
        assertEquals("2024-07-08", rental.getDueDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Test
    void test_getRentalDays() {
        assertArrayEquals(new String[]{"2024-07-02", "2024-07-03", "2024-07-04", "2024-07-05", "2024-07-06", "2024-07-07", "2024-07-08"},
                rental.getRentalDays().stream().map(d -> d.format(DateTimeFormatter.ISO_LOCAL_DATE)).toArray());
    }

    @Test
    void test_getChargeDays() {
        assertArrayEquals(new String[]{"2024-07-02", "2024-07-03", "2024-07-05", "2024-07-08"},
                rental.getChargeDays().stream().map(d -> d.format(DateTimeFormatter.ISO_LOCAL_DATE)).toArray());
    }

    /*
    TODO: Variable rates are not a requirement and therefore not tested.
     */
    @Test
    void test_getRate() {
        assertEquals(100, rental.getRate());
    }

    @Test
    void test_calculateSubTotal() {
        assertEquals(400, rental.calculateSubTotal());
    }

    @Test
    void test_calculateDiscount() {
        assertEquals(100, rental.calculateDiscount());
    }

    @Test
    void test_calculateFinalTotal() {
        assertEquals(300, rental.calculateFinalTotal());
    }

    @Test
    void test_getRates() {
        Configuration config = Configuration.singleton();
        ToolType rototiller = new ToolType("Rototiller", 200, 400, 100);
        config.addToolType(rototiller);
        Tool husqvarnaTiller = new Tool("ROTO", rototiller, "Husqvarna");
        config.addTool(husqvarnaTiller);

        Rental variableRateRental = new Rental(husqvarnaTiller, LocalDate.of(2024, 7, 1), 7, 0);
        assertArrayEquals(new Double[]{200.0, 200.0, 100.0, 200.0, 400.0, 400.0, 200.0},
                variableRateRental.getRates().toArray());
    }

    @Test
    void test_roundedSum() {
        assertEquals(0.01, Rental.roundedSum(Stream.of(0.001, 0.002, 0.0015, 0.0005)));
        assertEquals(0.00, Rental.roundedSum(Stream.of(0.001, 0.002, 0.0015, 0.0004)));
        assertEquals(1.45, Rental.roundedSum(Stream.of(0.161, 0.282, 0.0415, 0.9608)));
    }

    @Test
    void test_roundHalfUp() {
        assertEquals(0.00, Rental.roundHalfUp(0.000));
        assertEquals(0.00, Rental.roundHalfUp(0.001));
        assertEquals(0.00, Rental.roundHalfUp(0.00499999));
        assertEquals(0.01, Rental.roundHalfUp(0.005));
        assertEquals(0.01, Rental.roundHalfUp(0.00999999));
    }

    @Test
    void test_format() {
        assertEquals("$12.34", Rental.format(12.34));
    }
}