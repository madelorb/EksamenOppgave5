class Sted {
    protected Skattkiste skattkiste;
    protected String data;
    protected Sted valgtUtgang;

    public Sted(String beskrivelse) {
        data = beskrivelse;
        skattkiste = new Skattkiste();
    }

    public void getUtgang(Sted utgang) {
        valgtUtgang = utgang;
    }

    public Skattkiste hentSkattkiste() {
        return skattkiste;
    }

    public String hentData() {
        return data;
    }

    public Gjenstand hentEnGjenstandFraKiste() {
        if (skattkiste.hentListe().size() != 0) {
            return (skattkiste.taGjenstandFraKiste(this));
        } else {
            System.out.println("Skattekista er tom");
        }
        return null;
    }

    public void fjernGjenstand() {
        skattkiste.fjernGjenstandFraKiste();
    }

    public Gjenstand selgGjenstandTilSted(Skattkiste ryggsekk) {
        Gjenstand fraRyggsekk = skattkiste.selgFraRyggsekk(ryggsekk);
        skattkiste.hentListe().add(fraRyggsekk);
        return fraRyggsekk;
    }

    public void leggTilIStedKiste(Gjenstand gjenstand) {
        this.hentSkattkiste().leggTilGjensandIKiste(gjenstand, this);
    }

    public void printSted() {
        System.out.println("\n**********\n" + data.toString() +  "\nDu finner en skattekiste som inneholder: ");
        skattkiste.printSkattkiste();
        System.out.println("\n**********");


    }
}
