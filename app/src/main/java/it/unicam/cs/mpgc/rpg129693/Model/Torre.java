package it.unicam.cs.mpgc.rpg129693.Model;

import java.util.HashMap;
import java.util.Map;

public class Torre {
    private Map<Integer, Villain> nemiciPiani;
    private int livelloCorrente;
    private int livelloMassimo;

    public Torre() {
        this.nemiciPiani = new HashMap<>();
        this.livelloCorrente = 1;
        this.livelloMassimo = 1;
    }

    public void aggiungiNemici(Villain nemico) {
        if (nemico == null) {
            throw new IllegalArgumentException("E' stato passato un nemico null al metodo aggiungiNemici!");
        }
        if (this.nemiciPiani.containsKey(nemico.getLivelloTorre())) {
            throw new IllegalArgumentException("In questo livello è gia presente un villain!");
        }
        this.nemiciPiani.put(nemico.getLivelloTorre(), nemico);
        this.livelloMassimo = Math.max(this.livelloMassimo, nemico.getLivelloTorre());
    }

    public Villain getNemicoCorrente() {
        return this.nemiciPiani.get(this.livelloCorrente);
    }

    public void avanzaLivello() {
        this.livelloCorrente++;
    }

    public boolean isTorreFinita() {
        return this.livelloCorrente > this.livelloMassimo;
    }

    public int getLivelloCorrente() {
        return this.livelloCorrente;
    }

    public void resetTorre() {
        this.livelloCorrente = 1;
    }

    public Villain getNemicoAlPiano(int piano) {
        if (piano <= 0) {
            throw new IllegalArgumentException("E' stato passato un piano che non esiste");
        }
        return this.nemiciPiani.get(piano);
    }

    public void setLivelloCorrente(int livelloCorrente) {
        if (livelloCorrente <= 0) {
            throw new IllegalArgumentException("Il livello della torre deve essere maggiore di 0");
        }
        this.livelloCorrente = livelloCorrente;
    }
}
