import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Terreng {
    private ArrayList<Sted> terreng;
    private ArrayList<Sted> besokt;
    protected ArrayList<VeivalgSted> veivalgTerreng;
    protected ArrayList<VeivalgSted> veivalgBesokt;
    protected int index;
    private Sted start;
    private Sted neste;
    private int stoerrelse;
    protected Random random = new Random();
    protected Spiller spiller;

    public Terreng() {
        terreng = new ArrayList<Sted>();
        besokt = new ArrayList<Sted>();
        veivalgTerreng = new ArrayList<VeivalgSted>();
        veivalgBesokt = new ArrayList<VeivalgSted>();
        stoerrelse = 0;
        lesFraFil("steder.txt");
    }
    //metode for aa lese fra fil
    //opretter Sted objekter
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
            String[] data = linje.split("\n");
            int teller = 0;

            //oppretter Sted
            //legger til i lista
            Sted sted = new Sted(data[teller]);
            terreng.add(sted);
            VeivalgSted veivalgSted = new VeivalgSted(data[teller]);
            veivalgTerreng.add(veivalgSted);

            teller++;
        }
    }

    public Sted hentStart() {
        index = random.nextInt(terreng.size());
        Sted start = terreng.get(index);
        besokt.add(start);
        return start;
    }

    public Sted hentNesteTerreng() {
        index = random.nextInt(terreng.size());
        if (! besokt.contains(terreng.get(index))) {
            neste = terreng.get(index);
            settValgtUtgangReferanse(neste);
            besokt.add(neste);
            return neste;
        }
        return null;
    }

    public void settValgtUtgangReferanse(Sted utgang) {
        neste.getUtgang(neste);
    }


    public void getSpiller(Spiller spiller) {
        this.spiller = spiller;
    }
}
