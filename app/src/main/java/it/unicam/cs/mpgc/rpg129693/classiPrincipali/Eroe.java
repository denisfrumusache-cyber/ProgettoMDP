package it.unicam.cs.mpgc.rpg129693.classiPrincipali;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class Eroe extends Personaggio {
    private static final int XP_SOGLIA_INIZIALE = 100;
    private static final int INCREMENTO_SOGLIA_LIVELLO = 100;
    private static final int AUMENTO_HP_MAX = 10;
    private static final int AUMENTO_STAMINA_MAX = 5;
    private static final int AUMENTO_POTENZA = 2;
    private static final int AUMENTO_TECNICA = 1;
    private static final int AUMENTO_VELOCITA = 1;

    private int livello;
    private int esperienza;
    private int sogliaLivello;

    public Eroe(String id, String nome, String alias, int hpMax, int staminaMax, int potenza, int velocita, int tecnica, Quirk quirk) {
        super(id, nome, alias, hpMax, staminaMax, potenza, velocita, tecnica, quirk);
        this.livello = 1;
        this.esperienza = 0;
        this.sogliaLivello = XP_SOGLIA_INIZIALE;
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

    public int getSogliaLivello() {
        return this.sogliaLivello;
    }

    public void setSogliaLivello(int sogliaLivello) {
        if (sogliaLivello <= 0) {
            throw new IllegalArgumentException("La soglia livello deve essere maggiore di 0");
        }
        this.sogliaLivello = sogliaLivello;
    }

    public void guadagnaEsperienza(int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("L'esperienza guadagnata non può essere negativa");
        }
        this.esperienza += exp;
        verificaSalitaDiLivello();
    }

    private void verificaSalitaDiLivello() {
        while (esperienza >= sogliaLivello) {
            esperienza -= sogliaLivello;
            saliDiLivello();
        }
    }

    private void saliDiLivello() {
        livello++;
        incrementaStatistiche();
        aggiornaSoglia();
    }

    private void incrementaStatistiche() {
        setHpMax(getHpMax() + AUMENTO_HP_MAX);
        setHpAttuali(getHpMax());
        setStaminaMax(getStaminaMax() + AUMENTO_STAMINA_MAX);
        setStaminaAttuale(getStaminaMax());
        setPotenza(getPotenza() + AUMENTO_POTENZA);
        setTecnica(getTecnica() + AUMENTO_TECNICA);
        setVelocita(getVelocita() + AUMENTO_VELOCITA);
    }

    private void aggiornaSoglia() {
        sogliaLivello += INCREMENTO_SOGLIA_LIVELLO;
    }
}
