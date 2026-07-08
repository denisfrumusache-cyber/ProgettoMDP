package it.unicam.cs.mpgc.rpg129693.Quirks;

import it.unicam.cs.mpgc.rpg129693.classiAstratte.Personaggio;
import it.unicam.cs.mpgc.rpg129693.classiAstratte.Quirk;

public class Gecko extends Quirk {
    public Gecko() {
        super(
                "Gecko",
                10,
                "Aumenta la difesa e rigenera leggermente la salute grazie alle capacità da lucertola."
        );
    }


    @Override
    public void eseguiAzione(Personaggio utilizzatore, Personaggio bersaglio) {
        utilizzatore.attaccoBase(bersaglio);
        System.out.println( utilizzatore.getAlias() +" Colpisce con un fendente "+ bersaglio.getAlias() );
        System.out.println(utilizzatore.getAlias() + " si sta rigenerando ed aumenta le sue difese");
        utilizzatore.difenditi();
        recuperaHp(utilizzatore);

    }

    private static void recuperaHp(Personaggio utilizzatore){
        utilizzatore.setHpAttuali(Math.min(utilizzatore.getHpMax(), utilizzatore.getHpAttuali()+ 25));
    }
}

