package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends Schema {
    public MapSchema() {
    }

    @Override
    public final boolean isValidType(Object object) {
        return object instanceof Map;
    }

    @Override
    public final MapSchema required() {
        this.isRequired = true;
        addCheck(x -> x instanceof Map);
        return this;
    }

    public final MapSchema sizeof(int size) {
        Predicate<Map> range = x -> x.size() == size;
        addCheck(range);
        return this;
    }

    public final MapSchema shape(Map<String, Schema> schemas) {
        Predicate<Map> shape = value -> shapeCheck(schemas, value);
        addCheck(shape);
        return this;
    }

    private boolean shapeCheck(Map<String, Schema> schemas, Map<String, Object> mapToCheck) {

        for (Map.Entry<String, Schema> entry : schemas.entrySet()) {

            String key = entry.getKey();
            Schema check = entry.getValue();
            Object toCheck = mapToCheck.get(key);

            if (!mapToCheck.containsKey(key) || !check.isValid(toCheck)) {
                return false;
            }

        }
        return true;
    }
}
