import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class Spiller implements Comparable<Spiller> {
    protected Skattkiste ryggsekk;
    protected double formue;
    protected Sted start;
    protected Sted sted;
    protected Terreng terreng;
    protected Terminal terminal;
    protected Robot robot;
    protected Random random = new Random();
    private String navn;

    public Spiller(Sted startsted, Terreng terreng, Terminal terminal, String navn) {
        start = startsted;
        sted = start;
        this.terreng = terreng;
        this.terminal = terminal;
        this.navn = navn;
        ryggsekk = new Skattkiste();
        giSpillerReferanseTerreng(this);
    }

    public Spiller(Sted startsted, Terreng terreng, Robot robot, String navn) {
        start = startsted;
        sted = start;
        this.terreng = terreng;
        this.robot = robot;
        this.navn = navn;
        ryggsekk = new Skattkiste();
        giSpillerReferanseTerreng(this);
    }

    public String getNavn() {
        return navn;
    }

    public Robot getRobot() {
        return robot;
    }

    public double getFormue() {
        return formue;
    }

    public void giSpillerReferanseTerminal(Spiller spiller) {
        terminal.getSpiller(this);
    }

    public void giSpillerReferanseRobot(Spiller spiller) {
        robot.getSpiller(this);
    }

    public void giSpillerReferanseTerreng(Spiller spiller) {
        terreng.getSpiller(this);
    }

    public void taEnGjenstandFraSted() {
        if (sted.hentSkattkiste() != null) {
            Gjenstand gjenstand = sted.hentEnGjenstandFraKiste();
            boolean lagtTil = ryggsekk.leggTilGjenstandISeKK(gjenstand, sted);

            if (lagtTil) {
                sted.fjernGjenstand();
            } else {
                return;
            }
        } else System.out.println("Skattekista er tom!");
    }

    public void nyttTrekk() {
        String spoersmaal = "\nTast '1' for ja og '0' for nei";
        String[] alternativer = {"Selg gjenstand til denne kisten", "Ta med gjenstand", "Let etter utgang", "Se i ryggsekken", "Se status"};
        String[] alternativerRobot = {"Selg gjenstand til denne kisten", "Ta med gjenstand", "Let etter utgang"};

        if (sted != null) {
            if (terminal != null) {
                giSpillerReferanseTerminal(this);
                System.out.println("\n\n\n\nDet er " + getNavn() + " sitt trekk");
                sted.printSted();
                terminal.utforKommando(spoersmaal, alternativer);
            } else{
                giSpillerReferanseRobot(this);
                System.out.println("Det er " + getNavn() + " sitt trekk");
                sted.printSted();
                robot.utforKommando(spoersmaal, alternativerRobot);
            }
        } else return;
    }

    public void printSekk() {
        if (ryggsekk.hentListe().size() != 0) {
            ryggsekk.printSkattkiste();
        } else System.out.println("Ryggsekken din er tom");
    }

    public String seSted() {
        sted.printSted();
        return sted.hentData();
    }

    public String seStart() {
        start.printSted();
        return start.hentData();
    }

    public void selgGjenstandTilSted() {
        if (ryggsekk.hentListe().size() != 0) {
            Gjenstand gjenstand = sted.selgGjenstandTilSted(ryggsekk);
            formue += ryggsekk.selgGjenstand(gjenstand);
            }
        else System.out.println("Ryggsekken din er tom");
    }

    public void finnUtvei() {
        sted = terreng.hentNesteTerreng();
        if (sted != null) {
            seSted();
        }
    }

    public void avsluttSpill() {
        System.out.printf(("\nFormuen til " + this.getNavn() + " er: %.2f %n"), this.getFormue());
        System.out.println("Ryggesekken til " + this.getNavn() + " inneholder: ");
        this.printSekk();
    }

    @Override
    public int compareTo(Spiller annen) {
        if (this.formue < annen.formue) return -1;  //denne spillers formue mindre enn annen spillers formue
        else if (this.formue > annen.formue) return 1;
        else return 0;
    }

}
