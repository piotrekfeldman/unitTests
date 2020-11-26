package strumienie;

public class Mieszkanie {

    private String miasto;
    private String dzielnica;
    private String ulica;
    private int liczbaPokoi;
    private float powierzchnia;
    private boolean piwnica;
    private int cena;

    public Mieszkanie(String miasto, String dzielnica, String ulica, int liczbaPokoi, float powierzchnia, boolean piwnica, int cena) {
        this.miasto = miasto;
        this.dzielnica = dzielnica;
        this.ulica = ulica;
        this.liczbaPokoi = liczbaPokoi;
        this.powierzchnia = powierzchnia;
        this.piwnica = piwnica;
        this.cena = cena;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getDzielnica() {
        return dzielnica;
    }

    public void setDzielnica(String dzielnica) {
        this.dzielnica = dzielnica;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getLiczbaPokoi() {
        return liczbaPokoi;
    }

    public void setLiczbaPokoi(int liczbaPokoi) {
        this.liczbaPokoi = liczbaPokoi;
    }

    public float getPowierzchnia() {
        return powierzchnia;
    }

    public void setPowierzchnia(float powierzchnia) {
        this.powierzchnia = powierzchnia;
    }

    public boolean isPiwnica() {
        return piwnica;
    }

    public void setPiwnica(boolean piwnica) {
        this.piwnica = piwnica;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
