### Hexlet tests and linter status:
[![Actions Status](https://github.com/keepitquiet1/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/keepitquiet1/java-project-78/actions)
[![Code Climate](https://codeclimate.com/github/keepitquiet1/java-project-78.png)](https://codeclimate.com/github/keepitquiet1/java-project-78)
<a href="https://codeclimate.com/github/keepitquiet1/java-project-78/test_coverage"><img src="https://api.codeclimate.com/v1/badges/74cb704b2d34f300957b/test_coverage" /></a>

# Data Validator
Library to chech data
## Example:
` Validator v = new Validator(); `
### String check"
```
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false
```
### Number check:
```
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true
```

### Map check with structure:

```
Map<String, Schema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human1); // false
```
