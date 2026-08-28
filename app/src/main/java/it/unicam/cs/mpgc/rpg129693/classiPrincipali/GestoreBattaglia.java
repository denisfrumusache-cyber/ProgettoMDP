package it.unicam.cs.mpgc.rpg129693.classiPrincipali;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;

public class GestoreBattaglia {
    private final Eroe eroe;
    private final Villain villain;
    private boolean turnoEroe;

    public GestoreBattaglia(Eroe eroe, Villain villain) {
        if (eroe == null || villain == null) {
            throw new IllegalArgumentException("Eroe e Villain non possono essere null.");
        }
        this.eroe = eroe;
        if(this.eroe.getHpAttuali() != this.eroe.getHpMax()
                || this.eroe.getStaminaAttuale() != this.eroe.getStaminaMax()){
            this.eroe.recuperaVitaCompleta();
            this.eroe.recuperaStaminaCompleta();
        }
        this.villain = villain;
        this.turnoEroe = eroe.getVelocita() >= villain.getVelocita();
        if (turnoEroe) {
            eroe.iniziaTurno();
        } else {
            villain.iniziaTurno();
        }
    }


    public boolean eseguiAzioneEroe(int sceltaAzione) {
        if (!turnoEroe || isFinita()) {
            return false;
        }
        boolean azioneRiuscita = eseguiSceltaEroe(sceltaAzione);
        if (azioneRiuscita) {
            passaTurnoAlVillain();
        }
        return azioneRiuscita;
    }

    private boolean eseguiSceltaEroe(int sceltaAzione) {
        switch (sceltaAzione) {
            case 1: eroe.attaccoBase(villain);    return true;
            case 2: eroe.difenditi();             return true;
            case 3: return eroe.attaccoSpeciale(villain);
            default: return false;
        }
    }

    private void passaTurnoAlVillain() {
        turnoEroe = false;
        if (villain.eVivo()) villain.iniziaTurno();
    }

    public void eseguiAzioneVillain() {
        if (turnoEroe || isFinita()) {
            return;
        }
        villain.eseguiTurno(eroe);
        passaTurnoAllEroe();
    }

    private void passaTurnoAllEroe() {
        turnoEroe = true;
        if (eroe.eVivo()) eroe.iniziaTurno();
    }

    public boolean isFinita() {
        return !eroe.eVivo() || !villain.eVivo();
    }

    public Personaggio getEroe() {
        return eroe;
    }

    public Villain getVillain() {
        return villain;
    }

    public boolean isTurnoEroe() {
        return turnoEroe;
    }
}
