import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pract {
    public static void main(String[] args) {

        // Count frequency of characters in a string
        String input = "hello world";

        Map<Character, Long> counts = input.chars() // 1. Create an IntStream
                .mapToObj(c -> (char) c) // 2. Convert to Stream<Character>
                .collect(Collectors.groupingBy( // 3. Group by character
                        Function.identity(), // Key is the character itself
                        Collectors.counting() // Value is the count of occurrences
                ));

        System.out.println(counts);

        // Find the first non-repeated character in a string using Streams.
        String s = "bdhsyeus";

        Character cc = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new, // 4. Maintain insertion order
                        Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println(cc);

    }
}
