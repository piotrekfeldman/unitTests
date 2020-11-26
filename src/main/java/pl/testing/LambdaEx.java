package pl.testing;
import io.cucumber.java.ca.Cal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class LambdaEx {


    public static void main(String[] args) {

        LambdaEx main = new LambdaEx();
        main.run();
    }

    private void run() {
        List<String> names = new ArrayList<>();
        names.add("Genowefuncia");
        names.add("Kunegundzia");
        names.add("jacuś");
        names.add("placuś");
        names = filter(names, d -> d.endsWith("a"));
        modifyAndDisplay(names, mod ->  mod + new Random().nextInt(11)*10);


    }

    private List<String> filter(List<String> names, Filter filter) {

        List<String> modifiedNames = new ArrayList<>();
        for (String name : names) {

            if (filter.filter(name)) {
                modifiedNames.add(name);
            }
        }
        return modifiedNames;
    }

    private void modifyAndDisplay(List<String> names, Modifier modifier) {
        List<String> modifiedNames = new ArrayList<>();
        List<String> modifiedNames2 = new ArrayList<>();

        for (String name : names) {


            String modifiedName = modify(name, modifier);

            modifiedNames.add(modifiedName);
        }

        for (String name1 : names) {

            String modifiedName2 = modify(name1, d -> name1);
            modifiedNames2.add(modifiedName2);
        }
        System.out.println(modifiedNames);
        System.out.println(modifiedNames2);


        names.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        names.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });


        names.sort((o1, o2) -> Integer.compare(o1.length(), o2.length()));

        System.out.println(names);

    }


    private String modify(String string, Modifier modifier) {

        return modifier.modify(string.toUpperCase().substring(2));
    }


}
