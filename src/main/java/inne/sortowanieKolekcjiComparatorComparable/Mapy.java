package inne.sortowanieKolekcjiComparatorComparable;

import org.junit.jupiter.api.Test;

import javax.print.DocFlavor;
import java.util.*;



public class Mapy {

    @Test
    void checkingIfHashMapWorksProperly() {

        Map<Integer, String> mapka = new HashMap<>();
        mapka.put(2, "złypiotr");
        mapka.put(3, "alfonsyna");
        mapka.put(1, "kunegunda");
        mapka.put(4, "bażant");
        mapka.put(4, "bażant");
        mapka.put(5, "bażant");

        for (Map.Entry<Integer, String> m :
                mapka.entrySet()) {

            int key = m.getKey();
            String value = m.getValue();

            System.out.println("Lp :" + key + "/ imię :" + value);
        }

        System.out.println("************************");

        Set set = mapka.entrySet();
        Iterator it = set.iterator();

        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();

            System.out.println(m.getValue());

        }


    }

    @Test
    void checkingIfHashMapWorksProperly2() {

        Map<String, Integer> mapka = new HashMap<>();
        mapka.put("złypiotr", 1);
        mapka.put("alfonsyna",2);
        mapka.put("kunegunda",3);
        mapka.put("bażant",4);
        mapka.put("bażant",5);
        mapka.put("bażant", 6 );

        for (Map.Entry<String, Integer> m :
                mapka.entrySet()) {

            String key = m.getKey();
             int value = m.getValue();

            System.out.println("Lp :" + key + "/ imię :" + value);
        }

        System.out.println("************************");

        Set set = mapka.entrySet();
        Iterator it = set.iterator();

        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();

            System.out.println(m.getValue());

        }


    }


    @Test
    void checkingIfTreeMapWorkingProperly() {



        Map<Integer, String> mapka = new TreeMap<Integer, String>();

        mapka.put(2, "złypiotr");
        mapka.put(3, "alfonsyna");
        mapka.put(1, "kunegunda");
        mapka.put(4, "bażant");
        mapka.put(4, "bażant");
        mapka.put(5, "bażant");


        for (Map.Entry<Integer, String> m :
                mapka.entrySet()) {

            int key = m.getKey();
            String value = m.getValue();

            System.out.println("Lp :" + key + "/ imię :" + value);
        }

        List<Map.Entry<Integer, String>> list = new LinkedList<Map.Entry<Integer, String>>(mapka.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
            @Override
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return -1 * o1.getKey().compareTo(o2.getKey());
            }
        });

        System.out.println(list);

    }

    @Test
    void checkingIfLinkedHashMapWorkingProperly() {


        Map<Integer, String> mapka = new LinkedHashMap<>();
        mapka.put(2, "złypiotr");
        mapka.put(3, "alfonsyna");
        mapka.put(1, "kunegunda");
        mapka.put(4, "bażant");
        mapka.put(4, "bażant");
        mapka.put(5, "bażant");


        for (Map.Entry<Integer, String> m :
                mapka.entrySet()) {

            int key = m.getKey();
            String value = m.getValue();

            System.out.println("Lp :" + key + "/ imię :" + value);
        }
    }
}
