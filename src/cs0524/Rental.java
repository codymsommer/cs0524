package cs0524;

import cs0524.tool.Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

public class Rental {
    private final Tool tool;
    private final LocalDate checkoutDate;
    private final int duration;
    private final int discount;

    public Rental(String toolCode, String dateString, int numberOfDays, int discount) {
        this(Configuration.singleton().getTool(toolCode), LocalDate.parse(dateString), numberOfDays, discount);
    }

    public Rental(Tool tool, LocalDate checkoutDate, int numberOfDays, int discount) {
        this.tool = tool;
        this.checkoutDate = checkoutDate;
        if (numberOfDays < 1) {
            throw new IllegalArgumentException("A tool cannot be rented for less than 1 day");
        }
        duration = numberOfDays;
        if (discount < 0) {
            throw new IllegalArgumentException("Discount must not be negative");
        } else if (discount > 100) {
            throw new IllegalArgumentException("Discount must not be greater than 100%");
        }
        this.discount = discount;
    }

    public Tool getTool() {
        return tool;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public int getDuration() {
        return duration;
    }

    public int getDiscount() {
        return discount;
    }

    public LocalDate getDueDate() {
        return checkoutDate.plusDays(duration);
    }

    public List<LocalDate> getRentalDays() {
        List<LocalDate> rentalDays = new LinkedList<>();
        LocalDate date = checkoutDate;
        for (int i = 0; i < duration; i++) {
            date = date.plusDays(1);
            rentalDays.add(date);
        }
        return rentalDays;
    }

    public List<LocalDate> getChargeDays() {
        return getRentalDays().stream()
                .filter(d -> tool.getRate(d).compareTo(BigDecimal.ZERO) > 0)
                .toList();
    }

    public BigDecimal getRate() {
        return tool.getRate(getChargeDays().getFirst());
    }

    public BigDecimal calculateSubTotal() {
        return roundedSum(getRates());
    }

    public BigDecimal calculateDiscount() {
        BigDecimal percentage = BigDecimal.valueOf(getDiscount()).scaleByPowerOfTen(-2);
        return roundedSum(getRates().map(r -> r.multiply(percentage)));
    }

    public BigDecimal calculateFinalTotal() {
        return calculateSubTotal().subtract(calculateDiscount());
    }

    Stream<BigDecimal> getRates() {
        return getChargeDays().stream().map(tool::getRate);
    }

    static BigDecimal roundedSum(Stream<BigDecimal> amounts) {
        return roundHalfUp(amounts.reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    static BigDecimal roundHalfUp(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public void printRentalAgreement() {
        System.out.println("Tool code: " + getTool().getCode());
        System.out.println("Tool type: " + getTool().getType().getName());
        System.out.println("Tool brand: " + getTool().getBrand());

        System.out.println("Rental days: " + getDuration());
        System.out.println("Check out date: " + getCheckoutDate());
        System.out.println("Due date: " + getDueDate());

        try {
            System.out.println("Daily rental charge: " + format(getRate()));
        } catch (NoSuchElementException e) {
            System.out.println("Daily rental charge: $0.00 (each rental day is free of charge)");
        }
        System.out.println("Charge days: " + getChargeDays().size());
        System.out.println("Pre-discount charge: " + format(calculateSubTotal()));

        System.out.println("Discount percentage: " + getDiscount() + '%');
        System.out.println("Discount amount: " + format(calculateDiscount()));
        System.out.println("Final charge: " + format(calculateFinalTotal()));
    }

    static String format(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Rental rental
                && tool.equals(rental.tool) && checkoutDate.equals(rental.checkoutDate)
                && duration == rental.duration && discount == rental.discount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tool, checkoutDate, duration, discount);
    }
}
