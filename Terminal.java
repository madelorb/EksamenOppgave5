import java.util.Scanner;

class Terminal implements Brukergrensesnitt {
    private Scanner input;
    private Spiller spiller;
    private String status;

    public Terminal(Scanner scanner){
        input = scanner;
    }

    public void getSpiller(Spiller spiller) {
        this.spiller = spiller;
    }

    public void giStatus(String status) {
        this.status = status;
    }

    public void giFormueOgRyggsekk() {
        System.out.println("\nFormuen din er: " + spiller.getFormue());
        System.out.println("Ryggesekken din inneholder: "); spiller.printSekk();
        System.out.println("");

    }

    public int beOmKommando(String spoersmaal, String[] alternativer) {
        System.out.println("----------------------------------" + spoersmaal);
        int svar = Integer.parseInt(input.next());
        return svar;

    }

    //for at det ikke skal bli urettferdig, fjerner jeg spillermenyen
    //alle spillere faar akkurat samme trekk
    public void utforKommando(String spoersmaal, String[] alternativer) {
        int svar;
            System.out.println("Vil du selge en gjenstand?");
            svar = beOmKommando(spoersmaal, alternativer);
            if (svar == 1) {
                spiller.selgGjenstandTilSted();
            }

            System.out.println("\nVil du ta med en gjenstand?");
            svar = beOmKommando(spoersmaal, alternativer);
            if (svar == 1) {
                spiller.taEnGjenstandFraSted();
            }

            System.out.println("\nFinner en utgang");
                spiller.finnUtvei();
                return;
    }
}
