package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

abstract public class Schema {

    private Object schemaClass;
    private List<Predicate> checkList = new ArrayList<>();
    private boolean isRequired = false;

    public final Schema required() {
        this.isRequired = true;
        return this;
    }

    public final boolean isValid(Object schema) {

        if (!isRequired) {
            return true;
        } else if (isRequired && !isValidType(schema)) {
            return false;
        } else {
            return checkList.stream().allMatch(check -> check.test(schema));
        }
    }

    abstract boolean isValidType(Object object);

    public void setSchemaClass(Object schemaClass) {
        this.schemaClass = schemaClass;
    }
    public void addCheck(Predicate check){
        checkList.add(check);
    }
}