package enum2;

public class Movies {

    private String title;
    private MovieCategor movieCategor;



    public Movies(String title, MovieCategor movieCategor) {
        this.title = title;
        this.movieCategor = movieCategor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieCategor getMovieCategor() {
        return movieCategor;
    }

    public void setMovieCategor(MovieCategor movieCategor) {
        this.movieCategor = movieCategor;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "title='" + title + '\'' +
                ", movieCategor=" + movieCategor +
                '}';
    }
}
