package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.Schema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ValidatorTest {
    private static Validator v;
    private static NumberSchema numberSchema;
    private static StringSchema stringSchema;

    @BeforeAll
    static void setUp() {
        v = new Validator();
        numberSchema = v.number();
        stringSchema = v.string();
    }

    @Test
    void stringIsValid() {

        Assertions.assertTrue(stringSchema.isValid(""));
        Assertions.assertTrue(stringSchema.isValid(null));

        stringSchema.required();

        Assertions.assertTrue(stringSchema.isValid("what does the fox say"));
        Assertions.assertTrue(stringSchema.isValid("hexlet"));

        Assertions.assertFalse(stringSchema.isValid(null));
        Assertions.assertFalse(stringSchema.isValid(5));
        Assertions.assertFalse(stringSchema.isValid(""));

        Assertions.assertTrue(stringSchema.contains("wh").isValid("what does the fox say"));
        Assertions.assertTrue(stringSchema.contains("what").isValid("what does the fox say"));

        Assertions.assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));
        Assertions.assertFalse(stringSchema.isValid("what does the fox say"));

    }

    @Test
    void numberIsValid() {
        Assertions.assertTrue(true);


// Пока не вызван метод required(), null считается валидным
        Assertions.assertTrue(numberSchema.isValid(null)); // true
        Assertions.assertTrue(numberSchema.positive().isValid(null)); // true

        numberSchema.required();

        Assertions.assertFalse(numberSchema.isValid(null)); // false
        Assertions.assertTrue(numberSchema.isValid(10)); // true
        Assertions.assertFalse(numberSchema.isValid("5")); // false
        Assertions.assertFalse(numberSchema.isValid(-10)); // false
//  Ноль - не положительное число
        Assertions.assertFalse(numberSchema.isValid(0)); // false

        Assertions.assertFalse(numberSchema.range(5, 10).isValid(11)); //false

        Assertions.assertTrue(numberSchema.isValid(5)); // true
        Assertions.assertTrue(numberSchema.isValid(10)); // true
        Assertions.assertFalse(numberSchema.isValid(4)); // false
        Assertions.assertFalse(numberSchema.isValid(11)); // false
    }

    @Test
    public void testValidatorMap() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Assertions.assertTrue(schema.isValid(null));
        Assertions.assertTrue(schema.isValid("abc"));
        Assertions.assertTrue(schema.isValid(5));

        schema.required(); //требуется тип данных Map
        Assertions.assertTrue(schema.isValid(new HashMap<>()));
        Assertions.assertFalse(schema.isValid("wh"));
        Assertions.assertFalse(schema.isValid(10));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        Assertions.assertTrue(schema.isValid(data));

        schema.sizeof(2); //количество пар ключ-значений в объекте Map должно быть равно заданному
        Assertions.assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        Assertions.assertTrue(schema.isValid(data));

    }

}