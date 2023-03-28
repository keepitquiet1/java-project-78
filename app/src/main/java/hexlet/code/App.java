package hexlet.code;

public class App {
    public static void main(String[] args) {
        Validator v = new Validator();

        StringSchema schema = v.string();

        var v1 = schema.isValid(5);
        var v2 = schema.isValid(null);
        System.out.println("before: " + v1 + " " + v2);

        schema.required();

        v1 = schema.isValid(5);
        v2 = schema.isValid(null);
        System.out.println("after: " + v1 + " " + v2);

        System.out.println("1 " + schema.contains("wh").isValid("what does the fox say"));
        System.out.println("2 " + schema.contains("what").isValid("what does the fox say"));
        System.out.println("3 " + schema.contains("whatthe").isValid("what does the fox say"));

        System.out.println("final " + schema.isValid("what does the fox say"));

    }
}
