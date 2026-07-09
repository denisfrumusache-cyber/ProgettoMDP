package it.unicam.cs.mpgc.rpg129693.classiPrincipali;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;

public class GestoreBattaglia {
    private final Personaggio eroe;
    private final Villain villain;
    private boolean turnoEroe;

    public GestoreBattaglia(Personaggio eroe, Villain villain) {
        if (eroe == null || villain == null) {
            throw new IllegalArgumentException("Eroe e Villain non possono essere null.");
        }
        this.eroe = eroe;
        this.villain = villain;
        
        // Chi è più veloce inizia il primo turno
        this.turnoEroe = eroe.getVelocita() >= villain.getVelocita();
        
        // Inizializza il turno del primo combattente
        if (turnoEroe) {
            eroe.iniziaTurno();
        } else {
            villain.iniziaTurno();
        }
    }


    //Esegue una singola azione scelta per l'eroe. 1 = Attacco Base, 2 = Difesa, 3 = Attacco speciale

    public boolean eseguiAzioneEroe(int sceltaAzione) {
        if (!turnoEroe || isFinita()) {
            return false;
        }

        switch (sceltaAzione) {
            case 1:
                eroe.attaccoBase(villain);
                break;
            case 2:
                eroe.difenditi();
                break;
            case 3:
                boolean successoQuirk = eroe.attaccoSpeciale(villain);
                if (!successoQuirk) {
                    return false; // Stamina insufficiente: l'azione fallisce e non consuma il turno
                }
                break;
            default:
                return false;
        }

        // Il turno dell'eroe si è concluso con successo. Passiamo al villain.
        this.turnoEroe = false;
        if (villain.eVivo()) {
            villain.iniziaTurno();
        }
        return true;
    }

    /**
     * Esegue il turno automatico del villain usando la sua intelligenza artificiale interna.
     */
    public void eseguiAzioneVillain() {
        if (turnoEroe || isFinita()) {
            return;
        }
        villain.decidiMossaDaEseguire(eroe);

        // Il turno del nemico si è concluso. Passiamo all'eroe.
        this.turnoEroe = true;
        if (eroe.eVivo()) {
            eroe.iniziaTurno();
        }
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
