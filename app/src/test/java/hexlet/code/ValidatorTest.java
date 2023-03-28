package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ValidatorTest {
    private static Validator v;

    @BeforeAll
    private static void setUp() {
        v = new Validator();
    }

    @Test
    void stringIsValid() {

        StringSchema schema = v.string();

        Assertions.assertTrue(schema.isValid(""));
        Assertions.assertTrue(schema.isValid(null));

        schema.required();

        Assertions.assertTrue(schema.isValid("what does the fox say"));
        Assertions.assertTrue(schema.isValid("hexlet"));

        Assertions.assertFalse(schema.isValid(null));
        Assertions.assertFalse(schema.isValid(5));
        Assertions.assertFalse(schema.isValid(""));

        Assertions.assertTrue(schema.contains("wh").isValid("what does the fox say"));
        Assertions.assertTrue(schema.contains("what").isValid("what does the fox say"));

        Assertions.assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        Assertions.assertFalse(schema.isValid("what does the fox say"));

    }

    @Test
    void numberIsValid() {
        Assertions.assertTrue(true);

        NumberSchema schema = v.number();

// Пока не вызван метод required(), null считается валидным
        Assertions.assertTrue(schema.isValid(null)); // true
        Assertions.assertTrue(schema.positive().isValid(null)); // true

        schema.required();

        Assertions.assertFalse(schema.isValid(null)); // false
        Assertions.assertTrue(schema.isValid(10)); // true
        Assertions.assertFalse(schema.isValid("5")); // false
        Assertions.assertFalse(schema.isValid(-10)); // false
//  Ноль - не положительное число
        Assertions.assertFalse(schema.isValid(0)); // false

        Assertions.assertFalse(schema.range(5, 10).isValid(11)); //false

        Assertions.assertTrue(schema.isValid(5)); // true
        Assertions.assertTrue(schema.isValid(10)); // true
        Assertions.assertFalse(schema.isValid(4)); // false
        Assertions.assertFalse(schema.isValid(11)); // false
    }
}