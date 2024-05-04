package cs0524.tool;

import cs0524.Configuration;

import java.time.LocalDate;
import java.util.Objects;

public class Tool {
    private final String code;
    private final ToolType type;
    private final String brand;

    public Tool(String code, String type, String brand) {
        this(code, Configuration.singleton().getToolType(type), brand);
    }

    public Tool(String code, ToolType type, String brand) {
        this.code = code;
        this.type = type;
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public ToolType getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public double getRate(LocalDate date) {
        return getType().getRate(date);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Tool tool
                && code.equals(tool.code) && type.equals(tool.type) && brand.equals(tool.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type, brand);
    }
}
