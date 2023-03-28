package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends Schema {

    public NumberSchema() {
    }

    @Override
    public final NumberSchema required() {
        this.isRequired = true;
        addCheck(x -> x instanceof Integer);
        return this;
    }

    @Override
    public final boolean isValidType(Object object) {
        return object instanceof Integer;
    }

    public NumberSchema positive() {
        Predicate<Integer> positive = a -> a > 0;
        addCheck(positive);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Predicate<Integer> range = x -> (x >= min) && (x <= max);
        addCheck(range);
        return this;
    }
}
