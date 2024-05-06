import cs0524.Configuration;
import cs0524.Rental;
import cs0524.day.holiday.AbsoluteHoliday;
import cs0524.day.holiday.Instance;
import cs0524.day.holiday.RelativeHoliday;
import cs0524.tool.Tool;
import cs0524.tool.ToolType;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        defaultConfiguration();

        while (true) {
            consoleApplication();
        }
    }

    public static void defaultConfiguration() {
        Configuration config = Configuration.singleton();
        config.clear();
        configureHolidays();
        configureToolTypes();
        configureTools();
    }

    public static void configureHolidays() {
        Configuration config = Configuration.singleton();
        config.addHoliday(new AbsoluteHoliday("New Year's Day", 1, Month.JANUARY));
        config.addHoliday(new RelativeHoliday("Presidents' Day", Instance.THIRD, DayOfWeek.MONDAY, Month.FEBRUARY));
        config.addHoliday(new RelativeHoliday("Memorial Day", Instance.LAST, DayOfWeek.MONDAY, Month.MAY));
        config.addHoliday(new AbsoluteHoliday("Independence Day", 4, Month.JULY));
        config.addHoliday(new RelativeHoliday("Labor Day", Instance.FIRST, DayOfWeek.MONDAY, Month.SEPTEMBER));
        config.addHoliday(new RelativeHoliday("Columbus Day", Instance.SECOND, DayOfWeek.MONDAY, Month.OCTOBER));
        config.addHoliday(new RelativeHoliday("Thanksgiving", Instance.FOURTH, DayOfWeek.THURSDAY, Month.NOVEMBER));
        config.addHoliday(new AbsoluteHoliday("Christmas Day", 25, Month.DECEMBER));
    }

    public static void configureToolTypes() {
        Configuration config = Configuration.singleton();
        config.addToolType(new ToolType("Ladder", "1.99", false, false, true));
        config.addToolType(new ToolType("Chainsaw", "1.49", false, true, false));
        config.addToolType(new ToolType("Jackhammer", "2.99", false, true, true));
        config.addToolType(new ToolType("Miter Saw", new BigDecimal("1.99"), new BigDecimal("2.99"), new BigDecimal("0.99")));
        config.addToolType(new ToolType("Trencher", new BigDecimal("49.99"), new BigDecimal("34.99"), new BigDecimal("0.00")));
        config.addToolType(new ToolType("Rototiller", new BigDecimal("149.99"), new BigDecimal("199.99"), new BigDecimal("99.99")));
    }

    public static void configureTools() {
        Configuration config = Configuration.singleton();
        config.addTool(new Tool("CHNS", "Chainsaw", "Stihl"));
        config.addTool(new Tool("LADW", "Ladder", "Werner"));
        config.addTool(new Tool("JAKD", "Jackhammer", "DeWalt"));
        config.addTool(new Tool("JAKR", "Jackhammer", "Ridgid"));
        config.addTool(new Tool("MTRM", "Miter Saw", "Milwaukee"));
        config.addTool(new Tool("TRNC", "Trencher", "Brave"));
        config.addTool(new Tool("ROTO", "Rototiller", "Husqvarna"));
    }

    public static void consoleApplication() {
        Configuration config = Configuration.singleton();

        Scanner in = new Scanner(System.in);
        Tool tool = null;
        while (tool == null) {
            System.out.println("Please select a Tool: " + config.tools().stream().map(Tool::getCode).toList());
            try {
                tool = config.getTool(in.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("You have selected " + tool);
        System.out.println();

        LocalDate checkoutDate = null;
        while (checkoutDate == null) {
            System.out.println("Please enter the check out date: (YYYY-MM-DD)");
            try {
                checkoutDate = LocalDate.parse(in.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("You have selected " + checkoutDate.getMonth().name() + ' ' + checkoutDate.getDayOfMonth() + ',' + checkoutDate.getYear());
        System.out.println();

        int duration = 0;
        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.println("Please enter the number of days: (excluding the checkout day)");
            try {
                duration = in.nextInt();
                dueDate = new Rental(tool, checkoutDate, duration, 0).getDueDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("The due date will be " + dueDate.getMonth().name() + ' ' + dueDate.getDayOfMonth() + ", " + dueDate.getYear());
        System.out.println();

        Rental rental = null;
        while (rental == null) {
            System.out.println("Please enter the discount amount: (enter 0 for no discount)");
            try {
                rental = new Rental(tool, checkoutDate, duration, in.nextInt());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println();
        System.out.println("Here is your rental agreement:");
        rental.printRentalAgreement();
        System.out.println();
        System.out.println("Have a nice day!");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}