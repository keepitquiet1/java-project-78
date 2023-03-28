package hexlet.code;

import hexlet.code.schemas.BaseSchema;
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

        Assertions.assertTrue(schema.isValid("what"));
        Assertions.assertTrue(schema.minLength(7).isValid("what does the fox say"));
        Assertions.assertFalse(schema.isValid("what"));

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

    @Test
    public void testValidatorShape() {

        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().required().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        Assertions.assertTrue(schema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Valya");
        human2.put("age", -5);
        Assertions.assertFalse(schema.isValid(human2)); // false


        Validator v2 = new Validator();
        MapSchema schema2 = v2.map();

        Map<String, BaseSchema> schemas2 = new HashMap<>();
        schemas2.put("name", v2.string().required());
        schemas2.put("age", v2.number().required().range(1, 5));
        schema2.shape(schemas2);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", 100);
        Assertions.assertFalse(schema2.isValid(human3)); // False

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Kolya");
        human4.put("age", 3);
        Assertions.assertTrue(schema2.isValid(human4)); // true


        Validator v3 = new Validator();
        MapSchema schema3 = v3.map();

        Map<String, BaseSchema> schemas3 = new HashMap<>();
        schemas3.put("name", v3.string().required().contains("oly"));
        schemas3.put("age", v3.number().required());
        schema3.shape(schemas3);

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", "Anya");
        human5.put("quantity", "100");
        Assertions.assertFalse(schema3.isValid(human5)); // False

        Map<String, Object> human6 = new HashMap<>();
        human6.put("name", "Kolya");
        human6.put("age", 3);
        Assertions.assertTrue(schema3.isValid(human6)); // true


        Validator v4 = new Validator();
        MapSchema schema4 = v4.map();

        Map<String, BaseSchema> schemas4 = new HashMap<>();
        schemas4.put("name", v4.string().required().contains("oly"));
        schemas4.put("age", v4.number().required());
        schema4.shape(schemas4);

        Map<String, Object> human7 = new HashMap<>();
        human7.put("name", "Anya");
        human7.put("quantity", "100");
        Assertions.assertFalse(schema4.isValid(human7)); // False

        Map<String, Object> human8 = new HashMap<>();
        human8.put("name", "Kolya");
        human8.put("age", 3);
        Assertions.assertTrue(schema4.isValid(human8)); // true
    }

}
