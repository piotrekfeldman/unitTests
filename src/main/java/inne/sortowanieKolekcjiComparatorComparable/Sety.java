package inne.sortowanieKolekcjiComparatorComparable;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Sety {

    @Test
    void checkingFunctionalityOfLinkedHashSet(){

        Set<String> set = new LinkedHashSet<>();
        set.add("piotr");
        set.add("alfons");
        set.add("marta");
        set.add("ewelina");
        set.add("srom");


        for (String s : set) {
            System.out.println(s);
        }
    }

    @Test
    void checkingFunctionalityOfTreeSet(){

        Set<String> set = new TreeSet<>();
        set.add("piotr");
        set.add("alfons");
        set.add("marta");
        set.add("ewelina");
        set.add("srom");
        set.add("srom");
        set.add("srom");


        for (String s : set) {
            System.out.println(s);
        }
    }



    @Test
    void checkingFunctionalityOfHashSet(){

        Set<String> set = new HashSet<>();
        set.add("piotr");
        set.add("alfons");
        set.add("marta");
        set.add("ewelina");
        set.add("srom");
        set.add("srom");
        set.add("srom");


        for (String s : set) {
            System.out.println(s);
        }
    }



}
