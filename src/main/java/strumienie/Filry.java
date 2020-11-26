package strumienie;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Filry {

    public static void main(String [] args)
    {

        List<Mieszkanie> mList= Arrays.asList(
                new Mieszkanie("Wrocław","Psie Pole","Litewska",3,66.22f,
                        true,  350000),
                new Mieszkanie("Wrocław","Krzyki","Powstańców Slaskich",2,66.22f,
                        true,  450000),
                new Mieszkanie("Warszawa","Mokotów","jakas",3,55.55f,
                        true,500000));



        mList.stream()
                .filter(m -> m.getLiczbaPokoi() >= 2)
                .filter(mieszkanie -> mieszkanie.getCena() > 300000)
                .filter(mieszkanie -> mieszkanie.getPowierzchnia() >= 50)
                .map(mieszkanie -> mieszkanie.getMiasto()+" "+mieszkanie.getUlica()+" "+mieszkanie.getLiczbaPokoi()+" "+mieszkanie.getCena()+ " " +mieszkanie.getPowierzchnia())
                .forEach(System.out::println);


    Predicate<Mieszkanie> mNazwa = n-> n.isPiwnica() == true;
  //  Predicate<Mieszkanie> mNazwa2 = m-> m.getLiczbaPokoi() >=2;

   /* List<Mieszkanie> lista= new ArrayList<>();

    lista=mList.stream()
            .filter(mNazwa)
            .collect(Collectors.toList());

        System.out.println(lista);
*/

    }
}
