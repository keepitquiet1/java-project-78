package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema extends Schema {
    private boolean required = false;
    private List<String> condition = new ArrayList<>();

    public StringSchema() {
        setSchemaClass(String.class);
        addCheck(x-> x instanceof String && !x.equals(""));
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
