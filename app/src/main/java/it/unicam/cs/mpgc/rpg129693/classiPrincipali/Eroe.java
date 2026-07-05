package it.unicam.cs.mpgc.rpg129693.classiPrincipali;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class Eroe extends Personaggio {
    private int livello;
    private int esperienza;


    public Eroe(String id, String nome, String alias, int hpMax, int staminaMax, int potenza, int velocita, int tecnica, Quirk quirk) {
        super(id, nome, alias, hpMax, staminaMax, potenza, velocita, tecnica, quirk);
        this.livello = 1;
        this.esperienza = 0;
    }

    public int getLivello() {
        return this.livello;
    }

    public void setLivello(int livello) {
        if (livello <= 0) {
            throw new IllegalArgumentException("Il livello deve essere maggiore di 0");
        }
        this.livello = livello;
    }

    public int getEsperienza() {
        return this.esperienza;
    }

    public void setEsperienza(int esperienza) {
        if (esperienza < 0) {
            throw new IllegalArgumentException("L'esperienza non può essere negativa");
        }
        this.esperienza = esperienza;
    }

    @Override
    public void attaccoSpeciale(Personaggio bersaglio) {
        if (bersaglio == null)
            throw new IllegalArgumentException
                    ("E' stato passato un bersaglio null");
        this.getQuirk().eseguiAzione(this,bersaglio);
    }

    public void guadagnaEsperienza(){
        
    }

    @Override
    public void iniziaTurno() {

    }
}
