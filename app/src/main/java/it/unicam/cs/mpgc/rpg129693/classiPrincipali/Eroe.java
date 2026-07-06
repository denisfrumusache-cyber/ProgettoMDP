package it.unicam.cs.mpgc.rpg129693.classiPrincipali;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class Eroe extends Personaggio {
    private int livello;
    private int esperienza;
    private int sogliaLivello;


    public Eroe(String id, String nome, String alias, int hpMax, int staminaMax, int potenza, int velocita, int tecnica, Quirk quirk) {
        super(id, nome, alias, hpMax, staminaMax, potenza, velocita, tecnica, quirk);
        this.livello = 1;
        this.esperienza = 0;
        this.sogliaLivello = 100;
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


    public void guadagnaEsperienza(int exp){
        if (exp < 0){
            throw new IllegalArgumentException
                    ("L'esperienza guadagnata non può essere negativa");
        }
        this.esperienza += exp;
        while(this.esperienza >= sogliaLivello){
            this.esperienza -= sogliaLivello;
            this.saliDiLivello();

        }

    }

    public void saliDiLivello(){
        this.livello++;
        this.setHpMax(this.getHpMax() + 10);
        this.setHpAttuali(this.getHpMax());
        this.setStaminaMax(this.getStaminaMax() + 5);
        this.setStaminaAttuale(this.getStaminaMax());
        this.setPotenza(this.getPotenza() + 2);
        this.setTecnica(this.getTecnica() + 1);
        this.setVelocita(this.getVelocita() + 1);
        this.sogliaLivello += 100;
    }

}
