import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {

        // =====================================================
        // 1. Optional.of()
        // Creates Optional with NON-NULL value.
        // Throws NullPointerException if value is null.
        // =====================================================
        Optional<String> name1 = Optional.of("Om");
        System.out.println("of(): " + name1.get());

        // =====================================================
        // 2. Optional.ofNullable()
        // Creates Optional that can hold null or non-null value.
        // Returns Optional.empty() if value is null.
        // =====================================================
        String value = null;
        Optional<String> name2 = Optional.ofNullable(value);
        System.out.println("ofNullable(): " + name2);

        // =====================================================
        // 3. Optional.empty()
        // Creates an empty Optional object.
        // =====================================================
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("empty(): " + emptyOptional);

        // =====================================================
        // 4. isPresent()
        // Checks whether value exists inside Optional.
        // Returns true/false.
        // =====================================================
        if (name1.isPresent()) {
            System.out.println("isPresent(): Value exists");
        }

        // =====================================================
        // 5. ifPresent()
        // Executes code only if value exists.
        // =====================================================
        name1.ifPresent(n -> System.out.println("ifPresent(): " + n));

        // =====================================================
        // 6. get()
        // Returns value from Optional.
        // Dangerous if Optional is empty.
        // Throws NoSuchElementException.
        // =====================================================
        System.out.println("get(): " + name1.get());

        // =====================================================
        // 7. orElse()
        // Returns actual value if present,
        // otherwise returns default value.
        // =====================================================
        String result1 = name2.orElse("Default Name");

        System.out.println("orElse(): " + result1);

        // =====================================================
        // 8. orElseGet()
        // Similar to orElse(),
        // but default value is generated lazily using Supplier.
        // Default method runs ONLY if value absent.
        // =====================================================
        String result2 = name2.orElseGet(() -> getDefaultName());

        System.out.println("orElseGet(): " + result2);

        // =====================================================
        // 9. orElseThrow()
        // Throws custom exception if value absent.
        // =====================================================
        try {

            String result3 = name2.orElseThrow(() -> new RuntimeException("Value not found"));

            System.out.println(result3);

        } catch (Exception e) {

            System.out.println(
                    "orElseThrow(): " + e.getMessage());
        }

        // =====================================================
        // 10. map()
        // Transforms/changes Optional value.
        // Used for object conversion/modification.
        // =====================================================
        Optional<String> upperCase = name1.map(String::toUpperCase);

        System.out.println("map(): " + upperCase.get());

        // =====================================================
        // 11. filter()
        // Applies condition on Optional value.
        // Returns empty Optional if condition fails.
        // =====================================================
        Optional<String> filtered = name1.filter(n -> n.startsWith("O"));

        System.out.println("filter(): " + filtered);

        // =====================================================
        // 12. flatMap()
        // Similar to map(),
        // but avoids nested Optional<Optional<T>>.
        // =====================================================
        Optional<String> flatMapped = name1.flatMap(n -> Optional.of(n.toLowerCase()));

        System.out.println("flatMap(): " + flatMapped);

        // =====================================================
        // 13. isEmpty() (Java 11+)
        // Checks whether Optional is empty.
        // =====================================================
        System.out.println(
                "isEmpty(): " + emptyOptional.isEmpty());

        // =====================================================
        // 14. Chaining Example
        // Real-world Optional chaining.
        // Avoids multiple null checks.
        // =====================================================
        User user = new User("Om Patil");

        String username = Optional.ofNullable(user)
                .map(User::getName)
                .filter(n -> n.length() > 3)
                .map(String::toUpperCase)
                .orElse("Guest");

        System.out.println(
                "Chaining Example: " + username);
    }

    // =====================================================
    // Default value generator method
    // =====================================================
    public static String getDefaultName() {

        System.out.println("getDefaultName() called");

        return "Generated Default";
    }
}

class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}