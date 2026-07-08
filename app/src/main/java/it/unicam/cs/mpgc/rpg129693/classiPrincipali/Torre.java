package it.unicam.cs.mpgc.rpg129693.classiPrincipali;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;

import java.util.HashMap;
import java.util.Map;
@SuppressWarnings("all")
public class Torre{
    private Map<Integer, Villain> nemiciPiani;
    private int livelloCorrente;

    public Torre(){
        this.nemiciPiani = new HashMap<>();
        this.livelloCorrente = 1;
    }

    public void aggiungiNemici(Villain nemico){
        if (nemico == null){
            throw new IllegalArgumentException
                    ("E' stato passato un nemico null al metodo aggiungiNemici!");

        }
        int prossimoLivello = this.nemiciPiani.size() + 1;
        this.nemiciPiani.put(prossimoLivello,nemico);

    }

    public Villain getNemicoCorrente(){
        return this.nemiciPiani.get(this.livelloCorrente);
    }

    public void avanzaLivello(){
        this.livelloCorrente ++;
    }

    public boolean isTorreFinita(){
        return !this.nemiciPiani.containsKey(this.livelloCorrente);
    }

    public int getLivelloCorrente() {
        return this.livelloCorrente;
    }

    public void resetTorre(){
        this.livelloCorrente = 1;
    }

    public Villain getNemicoAlPiano(int piano){
        if (piano <= 0 ){
            throw new IllegalArgumentException
                    ("E' stato passato un piano che non esiste");
        }
        return this.nemiciPiani.get(piano);
    }






}
