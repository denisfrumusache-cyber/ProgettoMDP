package it.unicam.cs.mpgc.rpg129693.Quirks;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class Esplosione extends Quirk {
    private int livelloSudore;

    public Esplosione() {
        super(
            "Explosion",
            15,
            "Consente di trasudare sudore simile a nitroglicerina dai palmi delle mani e farlo detonare. " +
            "Con il proseguire dello scontro, l'aumento della sudorazione incrementa progressivamente " +
            "la temperatura corporea e la potenza distruttiva di ogni successiva detonazione."
        );
        this.livelloSudore = 0;
    }

    @Override
    public void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio) {
        livelloSudore++;
        int danno = calcolaDannoEsplosione(utilizzatore);
        System.out.println(utilizzatore.getAlias() + " rilascia un'esplosione!");
        bersaglio.riceviDanno(utilizzatore, danno);
    }

    private int calcolaDannoEsplosione(Personaggio utilizzatore) {
        return (utilizzatore.getPotenza() * 2) + (livelloSudore * 15);
    }
}
