package enum2;

public enum MovieCategor {

    KOMEDIA, DRAMAT, HORROR;

    private String genre;

    MovieCategor(String genre) {
        this.genre = genre;
    }

    MovieCategor() {

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
