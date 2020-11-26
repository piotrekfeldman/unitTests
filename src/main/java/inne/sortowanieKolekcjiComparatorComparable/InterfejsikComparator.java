package inne.sortowanieKolekcjiComparatorComparable;

import org.junit.jupiter.api.Test;

import java.util.*;

public class InterfejsikComparator {

    @Test
    void howToDealWithComparatorBasedOnTheSimpleExample() {

        List<String> lista = new ArrayList<>();
        lista.add("Dorota");
        lista.add("Kasia");
        lista.add("Asia");
        lista.add("Pusia");
        lista.add("Cycusia");
        lista.add("Roxi");

        Collections.sort(lista);

        for (String s : lista) {
            System.out.println(s);
        }

        lista.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // -1 * < słuzy sortowaniu odwrotnemu
                return -1 * o1.compareTo(o2);
            }
        });

        for (String s : lista) {
            System.out.println(s);
        }
    }

    @Test
    void howToDealWithComparatorBasedOnTheClassPersonAndList() {


        // oprócz Arraylist możemy również sortować , treesety, treemapy

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Dorota", "Sromota", 39));
        personList.add(new Person("Asica", "Dziewica", 60));
        personList.add(new Person("Asica", "Dziewica", 30));
        personList.add(new Person("Asica", "Dziewica", 80));
        personList.add(new Person("Asica", "Nowak", 60));
        personList.add(new Person("Cycusia", "Pączusia", 39));
        personList.add(new Person("Roxi", "Proxy", 39));

  //    Collections.sort(personList);
       // System.out.println(personList);

    /*    for(Person p : personList){
            System.out.println(p);
        }*/

        Collections.sort(personList, new PersonComparator());
        System.out.println(personList);
        System.out.println("************");

        PersonComparator personComparator= new PersonComparator();
        Comparator<Person> reversed =personComparator.reversed();
        Collections.sort(personList, reversed);



        System.out.println(personList);

    }

    @Test
    void howToDealWithComparatorBasedOnTheClassPersonAndArrays() {

        Person [] people = new Person[]{
                new Person("Dorota", "Sromota", 39),
        new Person("Asica", "Dziewica", 60),
        new Person("Asica", "Dziewica", 30),
        new Person("Asica", "Dziewica", 80),
        new Person("Asica", "Nowak", 60),
        new Person("Cycusia", "Pączusia", 39),
        new Person("Roxi", "Proxy", 39),
        };

        Arrays.sort(people, Comparator.comparing(Person::getSurname).reversed());

        System.out.println(Arrays.toString(people));


    }
}
