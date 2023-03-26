package hexlet.code;

public class Validator {
    public Validator() {
    }
    public <T> boolean isValid(T input){

        return input!=null && input.getClass().equals(String.class);
    }
}
