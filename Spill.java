import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

class Spill {
    public static void main(String[] args) {
        System.out.println("\n***Velkommen til Spillet!***\n");

        int antallTrekk = 3;
        int teller = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hvor mange spillere skal spille?");
        int antallSpillere = Integer.parseInt(scanner.next());

        System.out.println("Tast 0 - for aa avslutte");
        System.out.println("Tast 1 - for aa spille med enkelt terreng");
        System.out.println("Tast 2 - for aa spille med terreng med veivalg");
        System.out.println("Tast 3 - for aa la en robot spille med enkelt terreng");
        System.out.println("Tast 4 - for aa la en robot spille med terreng med veivalg");
        String svar = scanner.next();

        while (! svar.equals("0")) {
            if (svar.equals("1")) {
                teller = 0;
                Terminal terminal = new Terminal(scanner);
                Spiller[] spillerListe = new Spiller[antallSpillere];

                for (int i=0; i < antallSpillere; i++) {
                    Terreng terreng = new Terreng();    //alle faar hvert sitt terreng
                    Spiller spiller = new Spiller(terreng.hentStart(), terreng, terminal, "spiller " + (i+1));
                    spillerListe[i] = spiller;
                }

                while (teller < antallTrekk) {
                    for (int i = 0; i < spillerListe.length; i++) {
                        spillerListe[i].nyttTrekk();
                    }
                    teller++;
                }


                System.out.println("\n\n~~~~~GRATULERER~~~~~\nDere har funnet utgangen!");
                double highscore = 0;
                Arrays.sort(spillerListe, Collections.reverseOrder());

                for (int i = 0; i < spillerListe.length; i++) {
                    spillerListe[i].avsluttSpill();
                }

                return;
            }

            if (svar.equals("2")) {
                teller = 0;
                Terminal terminal = new Terminal(scanner);
                VeivalgSpiller[] veivalgSpillerListe = new VeivalgSpiller[antallSpillere];

                for (int i=0; i < antallSpillere; i++) {
                    VeivalgTerreng veivalgTerreng = new VeivalgTerreng();
                    VeivalgSpiller veivalgSpiller = new VeivalgSpiller(veivalgTerreng.hentStart(), veivalgTerreng, terminal, "spiller " + (i+1));
                    veivalgSpillerListe[i] = veivalgSpiller;
                }

                while (teller < antallTrekk) {
                    for (int i = 0; i < veivalgSpillerListe.length; i++) {
                        veivalgSpillerListe[i].nyttTrekk();
                    }
                    teller++;
                }

                System.out.println("\n\n~~~~~GRATULERER~~~~~\nDere har funnet utgangen!");
                double highscore = 0;
                Arrays.sort(veivalgSpillerListe, Collections.reverseOrder());

                for (int i = 0; i < veivalgSpillerListe.length; i++) {
                    veivalgSpillerListe[i].avsluttSpill();
                }

                return;
            }

            if (svar.equals("3")) {
                teller = 0;
                Robot robot = new Robot();
                Spiller[] spillerListe = new Spiller[antallSpillere];

                for (int i=0; i < antallSpillere; i++) {
                    Terreng terreng = new Terreng();
                    Spiller spiller = new Spiller(terreng.hentStart(), terreng, robot, "robot " + (i+1));
                    spillerListe[i] = spiller;
                }

                while (teller < antallTrekk) {
                    for (int i = 0; i < spillerListe.length; i++) {
                        spillerListe[i].nyttTrekk();
                    }
                    teller++;
                }

                System.out.println("\n\n~~~~~GRATULERER~~~~~\nDere har funnet utgangen!");
                double highscore = 0;
                Arrays.sort(spillerListe, Collections.reverseOrder());

                for (int i = 0; i < spillerListe.length; i++) {
                    spillerListe[i].avsluttSpill();
                }

                return;
            }


            if (svar.equals("4")) {
                teller = 0;
                Robot robot = new Robot();
                VeivalgSpiller[] veivalgSpillerListe = new VeivalgSpiller[antallSpillere];

                for (int i=0; i < antallSpillere; i++) {
                    VeivalgTerreng veivalgTerreng = new VeivalgTerreng();
                    VeivalgSpiller veivalgSpiller = new VeivalgSpiller(veivalgTerreng.hentStart(), veivalgTerreng, robot, "robot " + (i+1));
                    veivalgSpillerListe[i] = veivalgSpiller;
                }

                while (teller < antallTrekk) {
                    for (int i = 0; i < veivalgSpillerListe.length; i++) {
                        veivalgSpillerListe[i].nyttTrekk();
                    }
                    teller++;
                }

                System.out.println("\n\n~~~~~GRATULERER~~~~~\nDere har funnet utgangen!");
                double highscore = 0;
                Arrays.sort(veivalgSpillerListe, Collections.reverseOrder());

                for (int i = 0; i < veivalgSpillerListe.length; i++) {
                    veivalgSpillerListe[i].avsluttSpill();
                }

                return;
            }

            else {
                System.out.println("\nDu har tastet feil. Proev igjen\n");
                System.out.println("Tast 0 - for aa avslutte");
                System.out.println("Tast 1 - for aa starte spillet");
                System.out.println("Tast 2 - for aa la en robot spille");
                svar = scanner.next();
            }

        }
        System.out.println("Programmet ble avsluttet.");

    }
}
