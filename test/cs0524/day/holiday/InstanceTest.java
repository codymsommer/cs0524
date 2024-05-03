package cs0524.day.holiday;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InstanceTest {

    @Test
    void test_is_first() {
        assertTrue(Instance.FIRST.is(LocalDate.of(2024, 1, 1)));
        assertTrue(Instance.FIRST.is(LocalDate.of(2024, 1, 2)));
        assertTrue(Instance.FIRST.is(LocalDate.of(2024, 1, 3)));
        assertTrue(Instance.FIRST.is(LocalDate.of(2024, 1, 4)));
        assertTrue(Instance.FIRST.is(LocalDate.of(2024, 1, 5)));
        assertTrue(Instance.FIRST.is(LocalDate.of(2024, 1, 6)));
        assertTrue(Instance.FIRST.is(LocalDate.of(2024, 1, 7)));
    }

    @Test
    void test_is_second() {
        assertTrue(Instance.SECOND.is(LocalDate.of(2024, 5, 8)));
        assertTrue(Instance.SECOND.is(LocalDate.of(2024, 5, 9)));
        assertTrue(Instance.SECOND.is(LocalDate.of(2024, 5, 10)));
        assertTrue(Instance.SECOND.is(LocalDate.of(2024, 5, 11)));
        assertTrue(Instance.SECOND.is(LocalDate.of(2024, 5, 12)));
        assertTrue(Instance.SECOND.is(LocalDate.of(2024, 5, 13)));
        assertTrue(Instance.SECOND.is(LocalDate.of(2024, 5, 14)));
    }

    @Test
    void test_is_third() {
        assertTrue(Instance.THIRD.is(LocalDate.of(2024, 7, 15)));
        assertTrue(Instance.THIRD.is(LocalDate.of(2024, 7, 16)));
        assertTrue(Instance.THIRD.is(LocalDate.of(2024, 7, 17)));
        assertTrue(Instance.THIRD.is(LocalDate.of(2024, 7, 18)));
        assertTrue(Instance.THIRD.is(LocalDate.of(2024, 7, 19)));
        assertTrue(Instance.THIRD.is(LocalDate.of(2024, 7, 20)));
        assertTrue(Instance.THIRD.is(LocalDate.of(2024, 7, 21)));
    }

    @Test
    void test_is_fourth() {
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 11, 22)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 11, 23)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 11, 24)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 11, 25)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 11, 26)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 11, 27)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 11, 28)));
    }

    @Test
    void test_is_last() {
        assertTrue(Instance.LAST.is(LocalDate.of(2023, 12, 25)));
        assertTrue(Instance.LAST.is(LocalDate.of(2023, 12, 26)));
        assertTrue(Instance.LAST.is(LocalDate.of(2023, 12, 27)));
        assertTrue(Instance.LAST.is(LocalDate.of(2023, 12, 28)));
        assertTrue(Instance.LAST.is(LocalDate.of(2023, 12, 29)));
        assertTrue(Instance.LAST.is(LocalDate.of(2023, 12, 30)));
        assertTrue(Instance.LAST.is(LocalDate.of(2023, 12, 31)));
    }

    @Test
    void test_is_february() {
        assertTrue(Instance.FOURTH.is(LocalDate.of(2025, 2, 22)));
        assertTrue(Instance.LAST.is(LocalDate.of(2025, 2, 22)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2025, 2, 23)));
        assertTrue(Instance.LAST.is(LocalDate.of(2025, 2, 23)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2025, 2, 24)));
        assertTrue(Instance.LAST.is(LocalDate.of(2025, 2, 24)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2025, 2, 25)));
        assertTrue(Instance.LAST.is(LocalDate.of(2025, 2, 25)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2025, 2, 26)));
        assertTrue(Instance.LAST.is(LocalDate.of(2025, 2, 26)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2025, 2, 27)));
        assertTrue(Instance.LAST.is(LocalDate.of(2025, 2, 27)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2025, 2, 28)));
        assertTrue(Instance.LAST.is(LocalDate.of(2025, 2, 28)));
    }

    @Test
    void test_is_leapYear() {
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 2, 22)));
        assertFalse(Instance.LAST.is(LocalDate.of(2024, 2, 22)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 2, 23)));
        assertTrue(Instance.LAST.is(LocalDate.of(2024, 2, 23)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 2, 24)));
        assertTrue(Instance.LAST.is(LocalDate.of(2024, 2, 24)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 2, 25)));
        assertTrue(Instance.LAST.is(LocalDate.of(2024, 2, 25)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 2, 26)));
        assertTrue(Instance.LAST.is(LocalDate.of(2024, 2, 26)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 2, 27)));
        assertTrue(Instance.LAST.is(LocalDate.of(2024, 2, 27)));
        assertTrue(Instance.FOURTH.is(LocalDate.of(2024, 2, 28)));
        assertTrue(Instance.LAST.is(LocalDate.of(2024, 2, 28)));
        assertFalse(Instance.FOURTH.is(LocalDate.of(2024, 2, 29)));
        assertTrue(Instance.LAST.is(LocalDate.of(2024, 2, 29)));
    }
}