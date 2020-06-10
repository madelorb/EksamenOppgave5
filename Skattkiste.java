import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Skattkiste {
    private Random random = new Random();
    private ArrayList<Gjenstand> alleGjenstander;
    private ArrayList<Gjenstand> skattkiste;
    private int maxStorrelse = 4;
    private double pris;
    private int hentGjenstandIndex;

    public Skattkiste() {
        alleGjenstander = new ArrayList<Gjenstand>();
        lesFraFil("gjenstander.txt");
        skattkiste = new ArrayList<Gjenstand>();
        fyllSkattkiste();    //baade kister og ryggsekk skal starte med gjenstander
    }

    public ArrayList<Gjenstand> hentListe() {
        return skattkiste;
    }

    public Gjenstand hentGjenstand(int posisjon) {
        return alleGjenstander.get(posisjon);
    }

    //metode for aa lese fra fil
    //opretter Gjenstand objekter
    public void lesFraFil(String fil) {
        File filnavn = new File(fil);
        Scanner lesFil = null;

        try {
            lesFil = new Scanner(filnavn);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }

        //leser gjennom filen
        while (lesFil.hasNextLine()) {
            String linje = lesFil.nextLine();
            String[] data = linje.split(" ");
            String navn = data[0];
            int verdi = Integer.parseInt(data[1]);

            //oppretter Gjenstand
            //legger til i lista
            Gjenstand gjenstand = new Gjenstand(navn, verdi);
            alleGjenstander.add(gjenstand);
        }
    }

    //en kiste kan bli fyllt med mellom 1 og 4 elementer
    //antar at kistene kan han inneholde flere like elementer (magiske kiste)
    public void fyllSkattkiste() {
        int antallSkatter = random.nextInt(maxStorrelse);
        while (skattkiste.size() <= antallSkatter) {
            int index = random.nextInt(alleGjenstander.size());
            skattkiste.add(hentGjenstand(index));
        }
    }

    public Gjenstand taGjenstandFraKiste(Sted sted) {
        hentGjenstandIndex = random.nextInt(skattkiste.size());
        Gjenstand gjenstand = sted.hentSkattkiste().hentListe().get(hentGjenstandIndex);
        return gjenstand;
    }

    public void fjernGjenstandFraKiste() {
        skattkiste.remove(hentGjenstandIndex);
    }

    public double settPris(Gjenstand gjenstand) {
        pris = gjenstand.getVerdi();
        int sjanse = random.nextInt(2);
        if (sjanse == 0) {
            return pris*1.30;
        } else {
            return pris-(pris*0.30);
        }
    }

    public boolean leggTilGjenstandISeKK(Gjenstand gjenstand, Sted sted) {
        if (sted.hentSkattkiste().hentListe().size() > 0) {
            if (skattkiste.size()+1 <= maxStorrelse) {
                skattkiste.add(gjenstand);
                gjenstand.settNyVerdi(settPris(gjenstand));
                System.out.println("Du har lagt " + gjenstand.hentGjenstand() + " til i sekken din. \n");
                return true;
            } else {
                System.out.println("Ryggsekken er full, selg noe for aa faa plass til mer.");
                return false;
            }
        }
        return false;
    }

    public void leggTilGjensandIKiste(Gjenstand gjenstand, Sted sted) {
        sted.hentSkattkiste().hentListe().add(gjenstand);
    }

    public void printSkattkiste() {
        int teller = 1;

        if (skattkiste.size() != 0) {
            for (Gjenstand gjenstand: skattkiste) {
                String ting = gjenstand.hentGjenstand();
                System.out.println("Gjenstand " + teller + ": " + ting);
                teller++;

            }
        } else {
            System.out.println("Aa nei. Skattekista er tom!");
        }
    }
    public double selgGjenstand(Gjenstand gjenstand) {
        return gjenstand.getVerdi();
    }

    public Gjenstand selgFraRyggsekk(Skattkiste ryggsekk) {
        if (ryggsekk.hentListe().size() != 0) {
            int index = random.nextInt(ryggsekk.hentListe().size());
            Gjenstand fraSekk = ryggsekk.hentListe().get(index);
            System.out.println("Du har solgt gjenstanden: " + fraSekk.hentGjenstand());
            ryggsekk.hentListe().remove(index);
            return fraSekk;
        } else {
            System.out.println("Ryggsekken er tom");
            return null;
        }
    }
}
