package inne.sortowanieKolekcjiComparatorComparable;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {


    @Override
    public int compare(Person o1, Person o2) {
        int result = o1.getName().compareTo(o2.getName());
        if(result == 0){
            result = o1.getSurname().compareTo(o2.getSurname());
            if(result == 0){
                return o1.getAge() - o2.getAge();
            }
        }
        return result;
    }
}
