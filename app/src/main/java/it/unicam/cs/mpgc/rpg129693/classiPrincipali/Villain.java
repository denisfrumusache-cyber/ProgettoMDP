package it.unicam.cs.mpgc.rpg129693.classiPrincipali;

import it.unicam.cs.mpgc.rpg129693.Utils.loggerBattaglia;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class Villain extends Personaggio {
    private int esperienzaRilasciata;
    private int livelloTorre;

    public Villain(String id, String nome, String alias, int hpMax, int staminaMax, int potenza, int velocita, int tecnica, Quirk quirk, int esperienzaRilasciata, int livelloTorre) {
        super(id, nome, alias, hpMax, staminaMax, potenza, velocita, tecnica, quirk);
        if (esperienzaRilasciata < 0) {
            throw new IllegalArgumentException("L'esperienza rilasciata non può essere negativa");
        }
        if (livelloTorre <= 0) {
            throw new IllegalArgumentException("Il livello torre non può essere minore di 1");
        }
        this.esperienzaRilasciata = esperienzaRilasciata;
        this.livelloTorre = livelloTorre;
    }

    public int getEsperienzaRilasciata() {
        return this.esperienzaRilasciata;
    }

    public void setEsperienzaRilasciata(int esperienzaRilasciata) {
        if (esperienzaRilasciata < 0) {
            throw new IllegalArgumentException("L'esperienza rilasciata non può essere negativa");
        }
        this.esperienzaRilasciata = esperienzaRilasciata;
    }

    public int getLivelloTorre() {
        return this.livelloTorre;
    }

    public void eseguiTurno(Personaggio bersaglio) {
        int mossa = scegliMossa();
        eseguiMossa(mossa, bersaglio);
    }

    private int scegliMossa() {
        if (haStaminaPerQuirk()) return 3;
        if (hpCritici())         return 2;
        return 1;
    }

    private boolean haStaminaPerQuirk() {
        return getStaminaAttuale() >= getQuirk().getCostoStamina();
    }

    private boolean hpCritici() {
        return getHpAttuali() < getHpMax() * 0.25;
    }

    private void eseguiMossa(int mossa, Personaggio bersaglio) {
        switch (mossa) {
            case 3 -> { attaccoSpeciale(bersaglio);  }
            case 2 -> { difenditi();              loggerBattaglia.scrivi( this.getAlias() + " decide di difendersi ");            }
            default -> { attaccoBase(bersaglio);  loggerBattaglia.scrivi( this.getAlias() + " sferra un attacco fisico!");
            }
        }
    }
}
