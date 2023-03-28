package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ValidatorTest {


    @Test
    void stringIsValid() {
        Validator v = new Validator();
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
        Validator v = new Validator();
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
