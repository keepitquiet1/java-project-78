package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private List<Predicate> checkList = new ArrayList<>();
    protected boolean isRequired = false;

    abstract BaseSchema required();

    public final boolean isValid(Object schema) {

        if (!isRequired && !isValidType(schema)) {
            return true;
        } else if (isRequired && !isValidType(schema)) {
            return false;
        } else {
            return this.checkList.stream().allMatch(check -> check.test(schema));
        }
    }

    abstract boolean isValidType(Object object);

    public void addCheck(Predicate check) {
        checkList.add(check);
    }
}
