class VeivalgSted extends Sted {
    private VeivalgSted hoyre;
    private VeivalgSted venstre;
    private VeivalgSted rettFrem;
    private VeivalgSted[] utganger = new VeivalgSted[3];


    public VeivalgSted(String beskrivelse) {
        super(beskrivelse);
    }

    public VeivalgSted[] getUtganger() {
        return utganger;
    }

    public void getHoyre(VeivalgSted hoyre) {
        this.hoyre = hoyre;
    }

    public void getVenstre(VeivalgSted venstre) {
        this.venstre = venstre;
    }

    public void getRettFrem(VeivalgSted rettFrem) {
        this.rettFrem = rettFrem;
    }

    public void printUtganger() {
        for(int i = 0; i < 3; i++) {
            utganger[i].printSted();
        }
    }

}
