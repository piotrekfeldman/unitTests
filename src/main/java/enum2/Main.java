package enum2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Movies> movies = Arrays.asList(
                new Movies("Poranek Kojota", MovieCategor.KOMEDIA),
                new Movies("Dzień świra", MovieCategor.DRAMAT),
                new Movies("Piła", MovieCategor.HORROR)
        );


        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj gatunek filmu: ");
        String nextLine = scanner.nextLine();

        for (Movies movie : movies) {
            if(movie.getMovieCategor().name().contentEquals(nextLine)){

                System.out.println(movie.getTitle());
            }

        }
    }
}
