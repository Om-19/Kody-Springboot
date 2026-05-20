import java.util.Optional;

public class Demo {

    // Optional Function
    public static Optional<String> getName() {
        String name = "Java";
        return Optional.ofNullable(name);
    }

    public static void main(String args[]) {

        System.out.println("Optional Demo");

        String name = null;
        String name2 = "Java";

        Optional<String> optionalName = Optional.ofNullable(name);
        Optional<String> optionalName2 = Optional.ofNullable(name2);

        // optionalName.ifPresent(System::println);

        System.out.println("Optional Name: " + optionalName.isPresent());
        System.out.println("Optional Name2: " + optionalName2.get());

        // orElse return exact value or default value if not present
        System.out.println("Optional Name orElse: " + optionalName.orElse("   No such Object Exists"));

        System.out.println(getName().orElse("No name found"));

    }
}