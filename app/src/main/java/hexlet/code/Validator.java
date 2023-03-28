package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    public String string;

    public Validator() {
    }

    public <T> boolean isValid(T input) {

        return input != null && input.getClass().equals(String.class);
    }

    public boolean contains(String substring) {
        String string = "string";
        return string.contains(substring);
    }

    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }
}
