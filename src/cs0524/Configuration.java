package cs0524;

import cs0524.day.holiday.Holiday;
import cs0524.tool.Tool;
import cs0524.tool.ToolType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Configuration {
    private static Configuration config;

    public static Configuration singleton() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

    private final Set<Holiday> holidays = new HashSet<>();
    private final Map<String, ToolType> toolTypes = new HashMap<>();
    private final Set<Tool> tools = new HashSet<>();

    public void load() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void save() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void clear() {
        holidays.clear();
        toolTypes.clear();
        tools.clear();
    }

    public void addHoliday(Holiday holiday) {
        holidays.add(holiday);
    }

    public Set<Holiday> getHolidays() {
        return new HashSet<>(holidays);
    }

    public void addToolType(ToolType toolType) {
        toolTypes.put(toolType.getName(), toolType);
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }

    public ToolType getToolType(String name) {
        return toolTypes.get(name);
    }
}
