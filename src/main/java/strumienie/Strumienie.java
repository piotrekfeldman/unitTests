package strumienie;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Strumienie {

@Test
 void Miscellanous() {

    List<String> names = Arrays.asList("Dorota", "Paweł","Piotr", "Kasia");

    List<String> l1= names.stream().filter(s-> s.endsWith("a")).collect(Collectors.toList());
    System.out.println(l1);

    List<User> userList = names.stream().filter(d-> d.contains("ot")).map(string -> new User(string, new Random().nextInt(10)+5)).
            sorted(Comparator.comparing(User::getAge).reversed())
            .collect(Collectors.toList());

    System.out.println(userList);

    //filter- wiadomo filtruje
    //map - przypisuje daną wartość do np, klasy user
    //collect- kolekcjonuje do wybranej klasy
}
}

class User {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
