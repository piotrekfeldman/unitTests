package inne.sortowanieKolekcjiComparatorComparable;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestClassSorting {




    @Test
    void collectionsTesting() {


        Users user1 = new Users();
        user1.setUsername("piotr");
        user1.setPassword("password");

        Users user2 = new Users();
        user2.setUsername("alladyn");
        user2.setPassword("assword");


        List<Users> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);




        assertThat(list.get(0).getUsername()).isEqualTo("alladyn");
        assertThat(list.get(1).getUsername()).isEqualTo("piotr");

        //System.out.println(list);

        list.toString();


        for (Users t:list) {
            t.toString();
            System.out.println(t);
        }
    }

    @Test
    void collectionsTesting2() {

        String str1 = "Aiotrrrrr";
        String str2 = "marta";

        List<String> list = new ArrayList<String>();
        list.add(str1);
        list.add(str2);


        System.out.println("*********");
        List<String> newList = list.stream().filter(p->p.endsWith("a")).collect(Collectors.toList());
        System.out.println(newList);
        System.out.println("*********");




        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        list.sort((o1, o2) -> o1.compareToIgnoreCase(o2));

       /* Collections.reverseOrder(list, new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }}
        )};*/

/*

     assertThat(list.get(0)).isEqualTo(str1);
     assertThat(list.get(1)).isEqualTo(str2);

     Collections.sort(list, new Comparator<String>() {

         @Override
         public int compar1e(String o1, String o2) {
             return o1.compareToIgnoreCase(o2);
         }

         @Override
         public int compare(String o1, String o2) {
             return Integer.compare(o1.length(), o2.length());
         }
     });*/
        for (String t: list) {
            System.out.println(list);
        }


        assertThat(list.get(0).length()).isGreaterThan(str2.length());


        for (String t: list) {
            System.out.println(list);
        }

    }
}
