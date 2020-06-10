import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class VeivalgTerreng extends Terreng {
    private VeivalgSted start;
    private VeivalgSted neste;
    private VeivalgSted hoyre;
    private VeivalgSted venstre;
    private VeivalgSted rettFrem;

    @Override
    public VeivalgSted hentStart() {
        index = random.nextInt(veivalgTerreng.size());
        VeivalgSted start = veivalgTerreng.get(index);
        veivalgBesokt.add(start);
        return start;
    }

    @Override
    public VeivalgSted hentNesteTerreng() {
        index = random.nextInt(veivalgTerreng.size());
        if (! veivalgBesokt.contains(veivalgTerreng.get(index))) {
            hoyre =  veivalgTerreng.get(index);
            index = random.nextInt(veivalgTerreng.size());

            if (! veivalgBesokt.contains(veivalgTerreng.get(index))) {
                venstre = veivalgTerreng.get(index);
                index = random.nextInt(veivalgTerreng.size());
            }

            if (! veivalgBesokt.contains(veivalgTerreng.get(index))) {
                rettFrem = veivalgTerreng.get(index);
            }
            if (hoyre != null && venstre != null && rettFrem != null) {
                settAlleUtgangReferanser(hoyre, venstre, rettFrem);
            }

            if (spiller.getRobot() != null) {
                if (hoyre != null && venstre != null && rettFrem != null) {
                    neste = velgNesteAutomatisk(hoyre, venstre, rettFrem);
                }
            } else {
                neste = velgNeste(hoyre, venstre, rettFrem);
            }

            leggTilIUtganger(hoyre, venstre, rettFrem);
            veivalgBesokt.add(neste);
            return neste;

        }
        return null;
    }

    public VeivalgSted velgNeste(VeivalgSted hoyre, VeivalgSted venstre, VeivalgSted rettFrem) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tast 'h' for aa velge utgangen til hoyre: " + hoyre.hentData());
        System.out.println("Tast 'v' for aa velge utgangen til venstre: " + venstre.hentData());
        System.out.println("Tast 'r' for aa velge utgangen rett frem: " + rettFrem.hentData());

        String svar = scanner.next();

        if (svar.equals("h")) {
            System.out.println("\nDu gaar til hoyre");
            return hoyre;
        }

        if (svar.equals("v")) {
            System.out.println("\nDu gaar til venstre");
            return venstre;
        }

        if (svar.equals("r")) {
            System.out.println("\nDu gaar rettFrem");
            return rettFrem;
        }
        return null;
    }

    public void settAlleUtgangReferanser(VeivalgSted hoyre, VeivalgSted venstre, VeivalgSted rettFrem) {
        hoyre.getHoyre(hoyre);
        venstre.getVenstre(venstre);
        rettFrem.getRettFrem(rettFrem);
    }

    public VeivalgSted velgNesteAutomatisk(VeivalgSted hoyre, VeivalgSted venstre, VeivalgSted rettFrem) {
        int randomTall = random.nextInt(3);
        System.out.println("Roboten har funnet en utgang til hoyre: " + hoyre.hentData());
        System.out.println("Roboten har funnet en utgang til venstre: " + venstre.hentData());
        System.out.println("Roboten har funnet en utgang rett frem: " + rettFrem.hentData());



        if (randomTall == 0) {
            System.out.println("\nRoboten gaar til hoyre");
            return hoyre;
        }

        if (randomTall == 1) {
            System.out.println("\nRoboten gaar til venstre");
            return venstre;
        }

        if (randomTall == 2) {
            System.out.println("\nRoboten gaar rettFrem");
            return rettFrem;
        }
        return null;
    }

    public VeivalgSted[] leggTilIUtganger(VeivalgSted hoyre, VeivalgSted venstre, VeivalgSted rettFrem) {
        neste.getUtganger()[0] = hoyre;
        neste.getUtganger()[1] = venstre;
        neste.getUtganger()[2] = rettFrem;
        return neste.getUtganger();

    }
}
