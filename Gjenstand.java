class Gjenstand {
    private String navn = "";
    private double verdi = 0;

    public Gjenstand(String navn, double verdi) {
        this.navn = navn;
        this.verdi = verdi;
    }

    //getters
    public String getNavn() {
        return navn;
    }
    
    public double getVerdi() {
        return verdi;
    }

    public double settNyVerdi(double pris) {
        return verdi = pris;

    }

    public String hentGjenstand() {
        return (getNavn() + ", verdi: " + getVerdi());
    }
}
