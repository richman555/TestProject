package richtest.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        TestService formula = new TestService() {
            @Override
            public double calculate(int a) {
            	double x = sqrt(a * 100);
            	System.out.println(x);
                return x;
            }
        };

        formula.calculate(100);     // 100.0
        formula.sqrt(16);           // 4.0
        
        
        
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
//        
//        Collections.sort(names, (String a, String b) -> {
//            return b.compareTo(a);
//        });
        
        
        //Collections.sort(names, (String a, String b) -> b.compareTo(a));
        
        //Collections.sort(names, (a, b) -> b.compareTo(a));
        
        System.out.println(names);
        
        
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123
        
        Converter<String, Integer> converter1 = Integer::valueOf;
        Integer converted2 = converter1.convert("123");
        System.out.println(converted2);   // 123
        
        
        Something something = new Something();
        Converter<String, String> converter3 = something::startsWith;
        String converted3 = converter3.convert("Java");
        System.out.println(converted3);    // "J"
        
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.toString());
        
        final int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        String x = stringConverter.convert(2);     // 3
        System.out.println(x);
        
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        
        
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        String rich = backToString.apply("12345");     // "123"
        System.out.println(rich);
        
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));
        
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        int comp = comparator.compare(p1, p2);             // > 0
        int comprev = comparator.reversed().compare(p1, p2);  // < 0
        
        System.out.println((String.valueOf(comp)));
        System.out.println(String.valueOf(comprev));
        
        //Streams
        
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        
        stringCollection
        .stream()
        .sorted()
        .filter((s) -> s.startsWith("a"))
        .forEach(System.out::println);
        
        stringCollection
        .stream()
        .map(String::toUpperCase)
        .sorted((a, b) -> a.compareTo(b))
        .forEach(System.out::println);

    // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"

    }
}
