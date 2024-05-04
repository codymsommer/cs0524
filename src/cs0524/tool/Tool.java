package cs0524.tool;

import cs0524.Configuration;

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
}
