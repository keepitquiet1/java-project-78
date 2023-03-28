package hexlet.code;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StringSchema {
    private boolean required = false;
    private List<String> condition = new ArrayList<>();

    public StringSchema() {
    }

    public <T> boolean isValid(T data) {
        if (!this.required) {
            return true;
        }
        if (data == null) {
            return false;
        }
        if (!data.getClass().equals(String.class)) {
            return false;
        }
        String string = data.toString();
        if (string.isEmpty()) {
            return false;
        }

        for (String substring : condition) {
            if (!string.contains(substring)) {
                return false;
            }
        }
        return true;
    }

    public void required() {
        if (!this.required) this.required = true;
    }

    public StringSchema contains(String substring) {
        this.condition.add(substring);
        return this;
    }

}
