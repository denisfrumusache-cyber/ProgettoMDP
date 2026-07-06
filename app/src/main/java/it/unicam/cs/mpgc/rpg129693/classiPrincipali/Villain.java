package it.unicam.cs.mpgc.rpg129693.classiPrincipali;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class Villain extends Personaggio {
    private int esperienzaRilasciata;

    public Villain(String id, String nome, String alias, int hpMax, int staminaMax, int potenza, int velocita, int tecnica, Quirk quirk, int esperienzaRilasciata) {
        super(id, nome, alias, hpMax, staminaMax, potenza, velocita, tecnica, quirk);
        if (esperienzaRilasciata < 0) {
            throw new IllegalArgumentException("L'esperienza rilasciata non può essere negativa");
        }
        this.esperienzaRilasciata = esperienzaRilasciata;
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

    public void decidiMossaDaEseguire(Personaggio bersaglio){
        int costoQuirk = this.getQuirk().getCostoStamina();
        if (this.getStaminaAttuale() >= costoQuirk){
            this.attaccoSpeciale(bersaglio);
            System.out.println( this.getAlias() + " usa il suo quirk!");
        }else{
            if (this.getHpAttuali() < (this.getHpMax() * 0.25)){
                this.difenditi();
                System.out.println(this.getAlias() + " si difende!");

            }else{
                this.attaccoBase(bersaglio);
                System.out.println( this.getAlias() + " esegue un attacco fisico!");
            }
        }


    }


}
