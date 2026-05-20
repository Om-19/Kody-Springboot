Optional is used to represent presence or absence of value safely and reduce NullPointerException using functional-style operations.

Optional class is not serializable in java


| Method          | Purpose                 |
| --------------- | ----------------------- |
| `of()`          | Value must not be null  |
| `ofNullable()`  | Accepts null            |
| `empty()`       | Empty optional          |
| `isPresent()`   | Check value exists      |
| `get()`         | Get value               |
| `orElse()`      | Default value           |
| `orElseGet()`   | Lazy default            |
| `orElseThrow()` | Throw exception         |
| `map()`         | Transform value         |
| `flatMap()`     | Avoid nested Optional   |
| `filter()`      | Apply condition         |
| `ifPresent()`   | Execute if value exists |


=======================================================================
1.
Optional.of(null); // throws NPE
Optional.ofNullable(null); // Optional.empty

orElse(value)   Always evaluates value.

orElseGet(() -> value)  Lazy evaluation.

optional.get() is considered risky beacuse if null it throws NoSuchElementException

2.
Usually Optional is not used in entity.
Because:
    Serialization issues
    JPA/Hibernate problems
    Memory overhead

Best practice:  Use Optional mainly for return types.

3.
Difference between map() and flatMap()?

map()       Wraps returned value into Optional.

flatMap()   Avoids nested Optional.

Example:
Optional<Optional<String>>   vs  Optional<String>

4.
Internally in Optional.empty()?   Returns singleton empty Optional object.

5.
Optional.of(null);    Throws NPE.
Correct
Optional.ofNullable(value);