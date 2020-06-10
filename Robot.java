import java.util.Random;

class Robot implements Brukergrensesnitt {
    private Random random = new Random();
    private Spiller spiller;

    public void getSpiller(Spiller spiller) {
        this.spiller = spiller;
    }

    public void giStatus(String status) {
        System.out.println("Status er: " + status);
    }

    public void giFormueOgRyggsekk() {
        System.out.println("\nFormuen din er: " + spiller.getFormue());
        System.out.println("Ryggesekken din inneholder: "); spiller.printSekk();
    }

    public int beOmKommando(String spoersmaal, String[] alternativer) {
        try {
            Thread.sleep(1000);
            int valg = random.nextInt(2);
            return valg;
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return 9;
    }

    public void utforKommando(String spoersmaal, String[] alternativer) {
        System.out.println("----------------------------------");
        int svar = beOmKommando(spoersmaal, alternativer);
        if (svar == 0) {
            System.out.println("Roboten har valgt aa selge en gjenstand");
            spiller.selgGjenstandTilSted();

        }
        svar = beOmKommando(spoersmaal, alternativer);
        if (svar == 1) {
            System.out.println("Roboten har valgt aa ta en gjenstand");
            spiller.taEnGjenstandFraSted();

        }
        System.out.println("Roboten leter etter en utgang");
        spiller.finnUtvei();
        return;
    }
}
