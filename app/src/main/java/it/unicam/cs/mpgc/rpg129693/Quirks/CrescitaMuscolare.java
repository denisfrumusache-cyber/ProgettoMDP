package it.unicam.cs.mpgc.rpg129693.Quirks;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class CrescitaMuscolare extends Quirk {
    public CrescitaMuscolare(){
        super(
                "Crescita muscolare",
                25,
                "Permette di accrescere e manipolare le proprie fibre muscolari, facendole persino sporgere " +
                        "dalla pelle. Questo strato di muscoli potenziati funge da corazza e amplifica a livelli spaventosi " +
                        "la forza d'impatto dei colpi fisici a corto raggio."
        );

    }

    @Override
    public void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio) {
        System.out.println(utilizzatore.getAlias() + " potenzia le sue fibre muscolari al massimo!");
        utilizzatore.difenditi();
        bersaglio.riceviDanno(utilizzatore,utilizzatore.getPotenza() * 3);
        System.out.println( utilizzatore.getAlias() + " sferra un colpo potentissimo!");
    }

}
