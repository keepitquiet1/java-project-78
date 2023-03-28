package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
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

    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        Predicate<Map> shape = value -> shapeCheck(schemas, value);
        addCheck(shape);
        return this;
    }

    private boolean shapeCheck(Map<String, BaseSchema> schemas, Map<String, Object> mapToCheck) {

        for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {

            String key = entry.getKey();
            BaseSchema check = entry.getValue();
            Object toCheck = mapToCheck.get(key);

            if (!mapToCheck.containsKey(key) || !check.isValid(toCheck)) {
                return false;
            }

        }
        return true;
    }
}
