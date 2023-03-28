package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends Schema {

    public StringSchema() {
    }

    @Override
    public final StringSchema required() {
        this.isRequired = true;
        addCheck(x -> x instanceof String && !x.equals(""));
        return this;
    }

    @Override
    public final boolean isValidType(Object object) {
        return object instanceof String;

    }

    public final StringSchema contains(String substring) {
        if (!substring.equals("")) {
            Predicate<String> contains = x -> x.contains(substring);
            addCheck(contains);
        }
        return this;
    }

}
